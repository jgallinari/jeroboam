(ns com.jeroboam.api.core
  "SOAP server core."
  (:gen-class) ; To generate core.class, otherwise "java -jar" command will fail.
  (:require [cheshire.core :as json]
            [clojure.string :as string]
            [com.stuartsierra.component :as component]
            [environ.core :as environ]
            [io.pedestal.log :as log]
            [com.jeroboam.api
             [api :as api]
             [server :as server]])
  (:import jakarta.xml.ws.Endpoint))

(defonce i-am-ws (atom nil))

;; (def ws->impl {"Basic" #(BasicService.)})
(def ws->impl {:Basic  #(com.jeroboam.api.BasicImpl.)
               :Simple #(com.jeroboam.api.SimpleImpl.)})

(defn endpoint-addr
  ""
  #_(E.g.
     (endpoint-addr "Basic" "http://api.jeroboam.com/")
     "http://api.jeroboam.com/Basic"
     )
  [ws target-ns]
  (cond-> target-ns
    (not (string/ends-with? target-ns "/")) (str "/")
    true                                    (str ws)))

(defn publisher-fn
  "Returns a function to create and publish an endpoint for the specified web service."
  [ws-key target-ns]
  #_(E.g.
     (publisher-fn :Basic "http://api.jeroboam.com/")
     {:Basic
      {:addr      "http://api.jeroboam.com/Basic",
       :publisher ...}}
     )
  (let [ws-name (name ws-key)
        addr    (endpoint-addr ws-name target-ns)
        impl    (ws->impl ws-key)]
    {ws-key {:addr addr :publisher (fn [] (Endpoint/publish addr (impl)))}}))

(defn system
  "Component system map definition"
  [publisher]
  (component/system-map
   :server (server/new-server publisher)))

(defn start
  "Starts a unique instance of the SOAP server."
  [publisher]
  (log/debug :in 'start :publisher publisher)
  (->> publisher
       system
       component/start
       (reset! i-am-ws)))

(defn stop
  "Stops the SOAP server."
  []
  (swap! i-am-ws component/stop))

(defn env->publisher
  ""
  #_(E.g.
     (env->publisher {:endpoint "{\"Basic\":\"http://localhost:8080/\",\"Simple\":\"http://localhost:8080/\"}"})
     {:Basic
      {:addr "http://localhost:8080/Basic",
       :publisher ...},
      :Simple
      {:addr "http://localhost:8080/Simple",
       :publisher ...}}
     )
  [env]
  (-> env
      :endpoint
      (json/parse-string true) ; e.g. {:Basic "http://localhost:8080/", :Simple "http://localhost:8080/"}
      (as-> ep-map (into {} (map (fn [[k v]] (publisher-fn k v)) ep-map)))))

(defn -main
  "Main function."
  [& args]
  (-> environ/env
      env->publisher
      start)
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop)))

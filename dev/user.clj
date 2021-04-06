(ns user
  "Tools for interactive development with the REPL."
  (:require [clojure.tools.namespace.repl :as repl]
            [com.stuartsierra.component :as component]
            [environ.core :as environ]
            [com.jeroboam.api.core :as api.core]))

(defonce i-am-user (atom nil))

(defn start
  ""
  []
  (if @i-am-user                        ; idempotence
    :already-started
    (let [system (-> environ/env
                     api.core/env->publisher
                     api.core/system)]
      (->> system
           component/start
           (reset! i-am-user))
      :started)))

(defn stop
  ""
  []
  (if @i-am-user
    (do
      (swap! i-am-user component/stop)
      (reset! i-am-user nil)
      :stopped)
    :already-stopped))

(defn restart
  ""
  []
  (stop)
  (repl/refresh :after 'user/start))

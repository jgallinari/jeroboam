(ns com.jeroboam.api.server
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.log :as log]))

(defrecord JaxWsServer [publisher endpoint]
  component/Lifecycle
  (start [this]
    (log/debug :in 'JaxWsServer/start :publisher publisher :endpoint endpoint)
    (if endpoint                        ; idempotence
      this
      (reduce-kv (fn [m ws-key {:keys [addr publisher]}]
                   (log/info :in 'JaxWsServer/start :msg (str "Starting server " ws-key " on " addr))
                   ;; NB: This executes the publisher.
                   ;; NB: If :endpoint exists then update, otherwise assoc.
                   (update m :endpoint (fnil conj {}) {ws-key {:addr addr :published (publisher)}}))
                 this publisher)))
      ;; (let [endpoint (into {} (for [[ws-key {:keys [addr publisher]}] publisher] [ws-key {:addr addr :published (publisher)}]))])
      ;; (let [[[ws-key {:keys [addr publisher]}]] (seq publisher)]
      ;;   (log/info :in 'JaxWsServer/start :msg (str "Starting server " ws-key " on " addr))
      ;;   (assoc this :endpoint {ws-key {:addr addr :published (publisher)}}))))
  (stop [this]
    (log/debug :in 'JaxWsServer/stop :this this :publisher publisher :endpoint endpoint)
    (if endpoint                        ; idempotence
      (do
        (doseq [[ws-key {:keys [addr published]}] endpoint]
          (log/info :in 'JaxWsServer/stop :msg (str "Stopping server " ws-key " on " addr))
          (.stop published))
        (assoc this :endpoint nil))
      ;; (let [[[ws-key {:keys [addr published]}]] (seq endpoint)]
      ;;   (log/info :in 'JaxWsServer/stop :msg (str "Stopping server " ws-key " on " addr))
      ;;   (.stop published)
      ;;   (assoc this :endpoint nil))
      this)))

(defn new-server
  "Creates a new WS server."
  [publisher]
  (map->JaxWsServer {:publisher publisher}))

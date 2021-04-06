(ns com.jeroboam.api.api
  (:require [io.pedestal.log :as log]))

(defn login
  [user-login user-pwd]
  (log/debug :in 'login :user-login user-login)
  {"requestId" 123 "sessionAuthId" "ABC"})

(defn getAddonList
  [session-auth-id co-id]
  (log/debug :in 'getAddonList :session-auth-id session-auth-id :co-id co-id)
  {"requestId" 456 "addonList" [{"addonId" "ID1" "addonName" "name1" "statusId" "status1"}
                                {"addonId" "ID2" "addonName" "name2" "statusId" "status2"}]})

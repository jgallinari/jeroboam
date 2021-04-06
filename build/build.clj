(ns build
  (:require [badigeon.javac :as j]))

(defn javac []
  (println "Compiling Java")
  (j/javac "src" {:compile-path     "classes"
                  ;; Additional options used by the javac command
                  :compiler-options ["-cp" "classes"]})
  (println "Compilation Completed"))

(defn -main []
  (javac))

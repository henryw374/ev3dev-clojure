(ns com.widdindustries.ev3.main
  (:require [clojure.core.server :as server])
  (:gen-class))


(defn -main [& _args]
  (println "starting")
  (server/start-server {:port 7091
                        :address "0.0.0.0"
                        :name "foo"
                        :accept 'clojure.core.server/repl
                        :server-daemon false
                        }))
;(.join (Thread/currentThread))
  


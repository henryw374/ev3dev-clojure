(ns com.widdindustries.ev3.run)

(def keep-running (atom true))

(defn run [f]
  (reset! keep-running true)
  (future
    (while @keep-running
      (f))))

(defn stop []
  (reset! keep-running false))

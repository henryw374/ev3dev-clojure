(ns com.widdindustries.ev3.run)

(def keep-running (atom false))

(defn run [f]
  (let [[was-running _] (swap-vals! keep-running (constantly true))]
    (if was-running
      (println "was already running!")
      (future
        (while @keep-running
          (f))))))

(defn stop []
  (reset! keep-running false))

(defn try-3-times [f]
  (loop [remaining 3]
    (let [result (try (f)
                      (catch Throwable t
                        (if (zero? remaining)
                          (throw t)
                          t)))]
      (if-not (instance? Throwable result)
        result 
        (recur (dec remaining))))))

(ns com.widdindustries.play
  (:import (ev3dev.actuators.lego.motors EV3LargeRegulatedMotor)
           (ev3dev.sensors.ev3 EV3IRSensor EV3TouchSensor)
           (lejos.hardware.port MotorPort SensorPort)
           [lejos.utility Delay]))

(def motor-right (EV3LargeRegulatedMotor. MotorPort/B))
(def motor-left (EV3LargeRegulatedMotor. MotorPort/C))
(def motors [motor-left motor-right])
(def touch-sensor (EV3TouchSensor. SensorPort/S1))
(def ir-sensor (EV3IRSensor. SensorPort/S4))
(def ir-sampler (.getDistanceMode ir-sensor))

(defn get-ir-distance []
  float [] sample = new float[sp.sampleSize()];
  sp.fetchSample(sample, 0);
  distance = (int)sample[0];
  )

(defn straight-forward [motors]
  (doseq [motor motors]
    (EV3LargeRegulatedMotor/.forward motor)))

(defn straight-backward [motors]
  (doseq [motor motors]
    (EV3LargeRegulatedMotor/.backward motor)))

(defn stop [motors]
  (doseq [motor motors]
    (EV3LargeRegulatedMotor/.stop motor)))

(comment
  (stop motors)
  (straight-forward motors)

  ; bumper car https://github.com/ev3dev-lang-java/examples/blob/master/ev3dev-lang-java/src/main/java/ev3dev/misc/BumperCar.java
  (do (def keep-running (atom true))
      (future
        (let [forward? (atom true)]
          (while @keep-running
            (let [direction @forward?]
              (when (EV3TouchSensor/.isPressed touch-sensor)
                (swap! forward? not))
              (if @forward?
                (straight-forward motors)
                (straight-backward motors))
              (when (not= direction @forward?)
                (Delay/msDelay 1000)))))))
  (do (reset! keep-running false)
      (stop motors))

  (EV3LargeRegulatedMotor/.setSpeed motor-a 800)
  (EV3LargeRegulatedMotor/.forward motor-a)
  (EV3TouchSensor/.isPressed touch-sensor)
  
  ; what's the difference?
  (EV3LargeRegulatedMotor/.stop motor-a)
  (EV3LargeRegulatedMotor/.brake motor-a)
  
  
  )

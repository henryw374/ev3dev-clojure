(ns com.widdindustries.play
  (:require [com.widdindustries.ev3.run :as run])
  (:import (ev3dev.actuators.lego.motors EV3LargeRegulatedMotor)
           (ev3dev.sensors.ev3 EV3IRSensor EV3TouchSensor)
           (lejos.hardware.port MotorPort SensorPort)
           (lejos.robotics SampleProvider)
           [lejos.utility Delay]))

(defonce motor-right (run/try-3-times #(EV3LargeRegulatedMotor. MotorPort/B)))
(defonce motor-left (run/try-3-times #(EV3LargeRegulatedMotor. MotorPort/C)))

(def motors [motor-left motor-right])
(defonce touch-sensor (EV3TouchSensor. SensorPort/S1))
(defonce ir-sensor (EV3IRSensor. SensorPort/S4))
(def distance-sampler (.getDistanceMode ir-sensor))

(defn get-ir-distance []
  (let [sample (float-array (SampleProvider/.sampleSize distance-sampler))]
    (SampleProvider/.fetchSample distance-sampler sample 0)
    (aget sample 0)))

(comment
  (get-ir-distance)
  )

(defn straight-backward [motors]
  (doseq [motor motors]
    (EV3LargeRegulatedMotor/.forward motor)))

(defn straight-forward [motors]
  (doseq [motor motors]
    (EV3LargeRegulatedMotor/.backward motor)))

(defn stop [motors]
  (doseq [motor motors]
    (EV3LargeRegulatedMotor/.stop motor)))

(defonce shutdown
  (.addShutdownHook (Runtime/getRuntime)
    (Thread. (fn []
               (stop motors)))))

(defn stop-all []
  (run/stop)
  (stop motors))

(def full-turn-delay 2500)

(defn turn [delay]
  (do
    (EV3LargeRegulatedMotor/.forward motor-right)
    (EV3LargeRegulatedMotor/.backward motor-left)
    (Delay/msDelay delay)
    (stop motors)))

(defn u-turn []
  (turn (/ full-turn-delay 2)))

(comment
  (turn (/ full-turn-delay 4))
  (EV3TouchSensor/.isPressed touch-sensor)
  
  (stop motors)
  (straight-forward motors)
  (straight-backward motors)
  (+ 2 2)
  ; bumper car https://github.com/ev3dev-lang-java/examples/blob/master/ev3dev-lang-java/src/main/java/ev3dev/misc/BumperCar.java
  (run/run
    #(if (< (get-ir-distance) 40)
       (u-turn)
       (straight-forward motors)))
  
  (stop-all)
  
  
  (EV3LargeRegulatedMotor/.setSpeed motor-right 800)
  (EV3LargeRegulatedMotor/.getMaxSpeed motor-right )
  (EV3TouchSensor/.isPressed touch-sensor)
  
  
  
  )

(ns com.widdindustries.play
  (:import (ev3dev.actuators.lego.motors EV3LargeRegulatedMotor)
           (ev3dev.sensors.ev3 EV3TouchSensor)
           (lejos.hardware.port MotorPort SensorPort)))

(defonce motor-right (EV3LargeRegulatedMotor. MotorPort/B))
(defonce motor-left (EV3LargeRegulatedMotor. MotorPort/C))
(def motors [motor-left motor-right])
(defonce touch-sensor (EV3TouchSensor. SensorPort/S1))

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
  
  (def keep-running (atom true))
  (while @keep-running
    (if (EV3TouchSensor/.isPressed touch-sensor)
      (straight-forward motors)
      (straight-backward motors))
    )
  (do (reset! keep-running false)
      (stop motors))

  (EV3LargeRegulatedMotor/.setSpeed motor-a 800)
  (EV3LargeRegulatedMotor/.forward motor-a)
  (EV3TouchSensor/.isPressed touch-sensor)
  
  ; what's the difference?
  (EV3LargeRegulatedMotor/.stop motor-a)
  (EV3LargeRegulatedMotor/.brake motor-a)
  
  
  )

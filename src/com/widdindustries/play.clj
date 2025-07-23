(ns com.widdindustries.play
  (:import (ev3dev.actuators.lego.motors EV3LargeRegulatedMotor)
           (lejos.hardware.port MotorPort)))

(def motor-a (EV3LargeRegulatedMotor. MotorPort/A))


(comment 
  (EV3LargeRegulatedMotor/.setSpeed motor-a 800)
  (EV3LargeRegulatedMotor/.forward motor-a)
  
  ; what's the difference?
  (EV3LargeRegulatedMotor/.stop motor-a)
  (EV3LargeRegulatedMotor/.brake motor-a)
  
  
  )

(ns com.widdindustries.play
  (:import (ev3dev.actuators.lego.motors EV3LargeRegulatedMotor)
           (lejos.hardware.port MotorPort)))

(defonce motor-a (EV3LargeRegulatedMotor. MotorPort/A))


(comment 
  (EV3LargeRegulatedMotor/.setSpeed motor-a 200)
  (EV3LargeRegulatedMotor/.forward motor-a)
  
  (EV3LargeRegulatedMotor/.stop motor-a)
  ;(EV3LargeRegulatedMotor/. motor-a)
  
  )

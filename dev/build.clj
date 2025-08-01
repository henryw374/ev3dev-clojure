(ns build
  (:require [clojure.tools.build.api :as b]))

(def lib 'my/lib1)

(def class-dir "target/classes")
(def uber-file (format "target/%s-standalone.jar" (name lib)))

;; delay to defer side effects (artifact downloads)
(def basis (delay (b/create-basis {:project "deps.edn"})))

(defn clean [_]
  (b/delete {:path "target"}))

(defn uber [_]
  (clean nil)
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (b/compile-clj {:basis @basis
                  :ns-compile '[com.widdindustries.ev3.main]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis @basis
           :main 'com.widdindustries.ev3.main}))
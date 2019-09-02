(ns leiningen.new.utils
  (:require [clojure.string :as str])
  (:import java.io.Writer))

;; When using `pr`, output quoted forms as 'foo, and not as (quote foo)
(defmethod clojure.core/print-method clojure.lang.ISeq [o ^Writer w]
  (#'clojure.core/print-meta o w)
  (if (= (first o) 'quote)
    (do
      (.write w "'")
      (print-method (second o) w))
    (#'clojure.core/print-sequential "(" #'clojure.core/pr-on " " ")" o w)))

(defn wrap-indent [wrap n list]
  (fn []
    (->> list
         (map #(str "\n" (apply str (repeat n " ")) (wrap %)))
         (str/join ""))))

(defn dep-list [n list]
  (wrap-indent #(str "[" % "]") n list))

(defn indent [n list]
  (wrap-indent identity n list))

(defn indent-next [n list]
  (str (first list)
       ((indent n (next list)))))

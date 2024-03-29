(defproject gen-cli/lein-template "0.1.2"
  :description "Leiningen template with mixed Clojure/Java support."
  :url "https://github.com/agilecreativity/gen-cli"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[clj-jgit "0.8.10"]
                 [org.slf4j/slf4j-nop "1.7.28"]]
  :plugins [[lein-cljfmt "0.6.4"]]
  :eval-in-leiningen true
  :repositories [["jitpack" "https://jitpack.io"]])

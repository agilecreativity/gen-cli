(defproject {{name}} "0.1.0"
  :description "{{name}} command line utility"
  :url "http://github.com/agilecreativity/{{name}}"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljfmt "0.6.1"]
            [jonase/eastwood "0.3.5"]
            [lein-auto "0.1.3"]
            [lein-cloverage "1.0.13"]
            [lein-binplus "0.6.5"]
            [alembic "0.3.2"]]
  :bin {:name "{{name}}"
        :bin-path "~/bin"
        :bootclasspath false}
  {{#java?}}
  ;; print this message when we use Java
  {{/java?}}
  :source-paths ["src/main/clojure"]
  :java-source-paths ["src/main/java"]
  :test-paths ["src/test/clojure" "src/test/java"]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clj-jgit "0.8.10"]
                 [net.b12n/swiza-commons "0.1.2"]
                 [akvo/fs "20180904-152732.6dad3934"]]
  :main {{name}}.core
  :profiles {:dev {:global-vars {*warn-on-reflection* true
                                 *assert* true}}
             :uberjar {:aot :all
                       :native-image {:opts ["--verbose"
                                             "-Dclojure.compiler.direct-linking=true"]}}
             :1.9  {:dependencies [[org.clojure/clojure "1.9.0"]]}
             :1.10 {:dependencies [[org.clojure/clojure "1.10.1"]]}}

  :aliases {"lint" ["do" ["cljfmt" "check"] ["eastwood"]]
            "test-all" ["with-profile" "default:+1.8:+1.9:+1.10" "test"]
            "lint-and-test-all" ["do" ["lint"] ["test-all"]]
            "test"   ["run" "-m" "circleci.test/dir" :project/test-paths]
            "tests"  ["run" "-m" "circleci.test"]
            "retest" ["run" "-m" "circleci.test.retest"]})

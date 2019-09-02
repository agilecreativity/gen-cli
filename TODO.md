## Idea for extension

- [ ] Add more example of `org.clojure/tools.cli`
- [ ] Allow the user to add more dependencies as options
- [ ] Update document to show how to use `lein cljfmt check` and `lein cljfmt fix`
- [ ] See example from https://github.com/agilecreativity/reagent-project--reagent-template
- [ ] Turn the options on or off like in the following project template

```clojure
   {{#test-hook?}}
   :test
   {:source-paths ["src/cljs" "src/cljc" "test/cljs"]
   :compiler {:main {{project-ns}}.doo-runner
              :asset-path "/js/out"
              :output-to "target/test.js"
              :output-dir "target/cljstest/public/js/out"
              :optimizations :whitespace
              :pretty-print true}}{{/test-hook?}}
   {{#spec-hook?}}
   :test {:source-paths ["src/cljs" "src/cljc" "spec/cljs"]
          :compiler {:output-to "target/test.js"
                     :optimizations :whitespace
                     :pretty-print true}}{{/spec-hook?}}
 ```

 Or use the conditional in the code like so

```clojure
 ;; See: https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut/src/clj/chestnut/application.clj
(defn app-system [config]
  (component/system-map
   :routes     (new-endpoint home-routes)
   :middleware (new-middleware {:middleware (:middleware config)})
   :handler    (-> {{#compojure?}}(new-handler){{/compojure?}}{{#bidi?}}(new-handler :router :bidi){{/bidi?}}
                   (component/using [:routes :middleware]))
   :http       (-> (new-web-server (:http-port config))
                   (component/using [:handler]))
   :server-info (server-info (:http-port config))))
```

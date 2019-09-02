(ns leiningen.new.gen-cli
  (:require
   [clj-jgit.porcelain :refer [load-repo
                               git-add
                               git-init
                               git-commit] :as cjg]
   [leiningen.new.templates :refer [renderer
                                    name-to-path
                                    ->files
                                    project-name
                                    render-text
                                    sanitize-ns
                                    year
                                    group-name ;; (group-name "com.xxx")
                                    slurp-resource]]
   [leiningen.core.main :as main]
   [clojure.string :as str]
   [leiningen.new.utils :refer [wrap-indent
                                dep-list
                                indent
                                indent-next]]))

;; Optional options
(def valid-options
  ["java"])

;; Build functions from list of valid-options
;; e.g.
;; (defn java? [opts]
;;  (some #{"+java" "--java"} opts))
(doseq [opt valid-options]
  (eval
   `(defn ~(symbol (str opt "?")) [opts#]
      (some #{~(str "--" opt) ~(str "+" opt)} opts#))))

(def render (renderer "gen_cli"))

(defn project-clj-deps [opts]
  (cond-> []
    (java? opts) (conj '[akvo/fs "20180904-152732.6dad3934"]
                       '[junit/junit "4.12"])))

(defn files-to-render [opts]
  (cond-> ["project.clj"
           "dot-gitignore"
           "README.org"
           "LICENSE"
           "CHANGELOG.md"
           "src/main/resources/dot-gitkeep"
           "src/main/clojure/gen_cli/core.clj"
           "src/test/clojure/gen_cli/core_test.clj"]
    (java? opts) (conj "src/test/resources/dot-gitkeep"
                       "src/main/java/gen_cli/Demo.java"
                       "src/test/java/gen_cli/DemoTest.java")))

;; For inspiration see
;; https://github.com/plexus/chestnut/blob/master/src/leiningen/new/chestnut.clj

(defn template-data [name opts]
  {:full-name name
   :name (project-name name)
   :project-ns (sanitize-ns name)
   :sanitized (name-to-path name)
   :project-clj-deps (indent 17 (map pr-str (project-clj-deps opts)))
   :year (year)
   :java? (java? opts)})

(defn format-files-args
  "Returns a list of pairs (vectors).
  The first element is the file name to render,
  the second is the file contents."
  [{:keys [name] :as data} opts]
  (letfn [(render-file
            [file]
            [;; Note: render dot-<filename> to .<file-name>
             (-> file
                 (str/replace "gen_cli" "{{sanitized}}")
                 (str/replace "dot-" "."))
             (render file data)])]
    (map render-file (files-to-render opts))))

(defn gen-cli
  [name & opts]
  (let [dash-opts (map (partial str "--") valid-options)
        plus-opts (map (partial str "+") valid-options)
        data {:name name
              :sanitized (name-to-path name)}]
    ;; If we pass some ops then it must be valid
    (doseq [opt opts]
      (if (not (some #{opt} (concat dash-opts plus-opts)))
        (apply main/abort (str "Invalid option: ") opt ". Valid options are" plus-opts)))

    (main/info (format "Generating fresh lein new gen-cli %s" name))
    (main/info (format "Please see %s/README.org for more information." name))
    (let [data (template-data name opts)
          files (format-files-args data opts)]
      (apply ->files data files))

    ;; TODO: extract to private function?
    (cjg/git-init name)
    (let [repo (cjg/load-repo name)]
      (cjg/git-add repo ".")
      (cjg/git-commit repo (str "lein new gen-cli " name " " (str/join " " opts))))))

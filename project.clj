(defproject multimodal-re-frame "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"
                  :exclusions [com.google.javascript/closure-compiler-unshaded
                               org.clojure/google-closure-library]]
                 [thheller/shadow-cljs "2.8.52"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.9"]
                 [secretary "1.2.3"]
                 [day8.re-frame/undo "0.3.2"]
                 [day8.re-frame/http-fx "0.1.6"]
                 [kibu/pushy "0.3.8"]
                 [bidi "2.1.6"]
                 [cljs-ajax "0.8.0"]
                 [cljsjs/react-leaflet "2.0.1-0"]
                 [io.jesi/clojure-polyline "0.4.1"]]

  :plugins [
            [lein-less "1.7.5"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs"]

  :test-paths   ["test/cljs"]

  :clean-targets ^{:protect false} ["src/js/compiled" "target"
                                    "test/js"]


  :less {:source-paths ["less"]
         :target-path  "src/css"}

  :aliases {"dev"  ["with-profile" "dev" "run" "-m" "shadow.cljs.devtools.cli" "watch" "app"]
            "prod" ["with-profile" "prod" "run" "-m" "shadow.cljs.devtools.cli" "release" "app"]
            "build" ["with-profile" "prod" "run" "-m" "shadow.cljs.devtools.cli" "compile" "app"]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.10"]
                   [cider/piggieback "0.4.1"]
                   [day8.re-frame/re-frame-10x "0.4.2"]
                   [cider/cider-nrepl "0.22.2"]
                   [day8.re-frame/tracing "0.5.3"]]}

   :prod { :dependencies [[day8.re-frame/re-frame-10x "0.4.2"]
                          [day8.re-frame/tracing "0.5.3"]
                           ]}
   })

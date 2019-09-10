(ns multimodal-re-frame.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [run-plotter.events :as plotterevents]
    [run-plotter.views.base :refer [base-view]]
    [run-plotter.routes :as plotterroutes]
    [multimodal-re-frame.events :as events]
    [multimodal-re-frame.routes :as routes]
    [multimodal-re-frame.views :as views]
    [multimodal-re-frame.app :as app]
    [multimodal-re-frame.config :as config]))


(defn dev-setup []
  (when config/debug?
        (println "dev mode")))

(defn app-element []
  (let [App (aget js/window "App")]
    [:> App]))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [App (aget js/window "App")]
    (reagent/render [app/app]
                    (.getElementById js/document "app2"))))

(defn init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/dispatch-sync [::plotterevents/initialize-db])
  (dev-setup)
  (mount-root))

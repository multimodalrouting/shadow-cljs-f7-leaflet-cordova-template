(ns multimodal-re-frame.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [multimodal-re-frame.events :as events]
    [multimodal-re-frame.routes :as routes]
    [multimodal-re-frame.views :as views]
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
    (reagent/render [app-element]
                    (.getElementById js/document "app2"))))

(defn init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))

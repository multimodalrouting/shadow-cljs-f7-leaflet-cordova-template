(ns multimodal-re-frame.views
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [multimodal-re-frame.subs :as subs]))


;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 (str "Hello from " @name ". This is the Home Page.")]

     [:div
      (let [List     (aget js/window "F7" "List")
            ListItem (aget js/window "F7" "ListItem")]
        [:> List
         [:> ListItem {:link "/popup"}
          "go to About Page"]])]]))


;; about


(defn about-panel [props]
  [:div
   [:h1 "This is the About Page named" (:title props) "."]

   [:div
    (let [Link (aget js/window "F7" "Link")]
      [:> Link {:link "/"}
       "go to Home Page"])]])

(def react-about-panel (reagent/reactify-component about-panel))

(defn AboutPanel []
  (reagent/create-element react-about-panel))

;; main

(defn- panels [panel-name]
  (case panel-name
        :home-panel  [home-panel]
        :about-panel [about-panel]
        [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))

(def react-main-panel (reagent/reactify-component main-panel))

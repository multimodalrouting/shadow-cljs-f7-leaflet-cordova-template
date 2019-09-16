(ns multimodal-re-frame.app
  (:require
    ["framework7-react" :as F7]
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [multimodal-re-frame.views :as views]
    [multimodal-re-frame.subs :as subs]))

(def f7-params
  {
   :id    "io.github.multimodalrouting"
   :name  "WheelyWonka"
   :theme "auto"
   :router false
   :pushState true
   :ajaxLinks "a.ajax"
   :fastClicks false
   })

(defn appRender []
  (let [Framework7    (aget js/window "F7App")
        App    (aget js/window "F7" "App")
        Panel  (aget js/window "F7" "Panel")
        View   (aget js/window "F7" "View")
        Page   (aget js/window "F7" "Page")
        Navbar (aget js/window "F7" "Navbar")
        Block  (aget js/window "F7" "Block")
        List   (aget js/window "F7" "List")
        ListItem (aget js/window "F7" "ListItem")]
    (if(.hasOwnProperty Framework7 "instance")
      (js-delete Framework7 "instance")
      )
    [:>
     App
     {:params f7-params}
     [:>
      Panel
      {:left true :cover true :themeDark false}
      [:>
       View
       [:>
        Page
        [views/routing-page]
        ]]]
     [views/basic-page]
     ]
    )
  )

(defn appWrapper []
  (with-meta identity
  {:component-did-mount
   (fn [_]
     (.$f7ready
       (fn [f7]  (if (aget f7 "device" "cordova")
                   ((aget js/window "cordovaApp"))
                   (println "not in a cordova context")
                   )))
     :reagent-render (fn [] (appRender))
     )}))

(defn app []
  (fn [] (appRender)))


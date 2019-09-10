(ns multimodal-re-frame.app
  (:require
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

(defn app []
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

    ))

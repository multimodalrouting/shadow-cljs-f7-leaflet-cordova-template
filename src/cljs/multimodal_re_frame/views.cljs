(ns multimodal-re-frame.views
  (:require
    [multimodal-re-frame.framework7.react
     :refer
     [navbar
      navleft
      link
      navtitle
      navtitlelarge
      link
      navright
      blocktitle
      block
      row
      f7list
      listitem
      button
      segmented
      page
      icon
      input
      listinput]]
    [multimodal-re-frame.framework7.fontawesome :refer [fontawesomeicon]]
    [reagent.core :as reagent]
    [run-plotter.views.base :refer [base-view]]
    [run-plotter.views.edit-route :refer [edit-route-panel]]
    [run-plotter.views.saved-routes :refer [saved-routes-panel]]
    [re-frame.core :as re-frame]
    [run-plotter.subs :as plotter-subs]
    [multimodal-re-frame.subs :as subs]))


;; routing-page

(defn routing-page []
  [:>
   (page)
   [:>
    (block)
    [:>
     (segmented)
     [:> (button) {:iconFa "directions"}]
     [:> (button) {:iconFa "train"}]
     [:> (button) {:iconFa "wheelchair"}]
     [:> (button) {:iconFa "route" :sortableToggle ".sortable"}]]]
   [:>
    (block)
    [:>
     (f7list)
     {:noHairlinesMd   true
      :sortable        true
      :on-sortableSort (fn [e]
                         (let [[from to] [e.detail.from e.detail.to]]
                           (js/setTimeout
                            #(re-frame/dispatch [:route-order-change from to]) 1000)))}
     (let [waypoints (re-frame/subscribe [::plotter-subs/waypointTexts])]
       (for [place @waypoints]
         ^{:key (:id (:key place))}
         [:>
          (listinput)
          {:type        "text"
           :className   "list-input"
           :clearButton true
           :value       (:text place)
           :placeholder "type start point or click on map"}
          [:div
           {:class "waypoint-route-icons" :slot "inner-start"}
           [:i {:icon ["fa" "dot-circle"] :class "fa fa-dot-circle fa-xs waypoint-icon icon" :size "xs"}]
           [:> (fontawesomeicon) {:icon ["fac" "directions"] :className "waypoint-connector icon" :size "1x"}]]]))
     [:>
      (listinput)
      {:type        "text"
       :className   "list-input"
       :clearButton true
       :placeholder "type destination point or click on map"}
      [:div
       {:class "waypoint-route-icons" :slot "inner-start"}
       [:i {:class "fa fa-s fa-map-marker-alt waypoint-icon icon"}]]]
     ]]])


;; home


(defn home-page []
  [:>
   (page)
   [:>
    (navbar)
    {:sliding false, :large true}
    [:>
     (navleft)
     [:>
      (link)
      {:iconIos    "f7:menu",
       :iconAurora "f7:menu",
       :iconMd     "material:menu",
       :panelOpen  "left"}]]
    [:> (navtitle) {:sliding true} "WheelyWonka"]
    [:>
     (navright)
     [:>
      (link)
      {:iconIos    "f7:menu",
       :iconAurora "f7:menu",
       :iconMd     "material:menu",
       :panelOpen  "right"}]]
    [:> (navtitlelarge) "WheelyWonka"]]
   [:> (blocktitle) "Panels"]
   [saved-routes-panel]
   [:>
    (block)
    {:strong true}
    [:>
     (row)
     [:> (button) {:fill true, :raised true, :panelopen "left"} "Left Panel"]
     [:> (button) {:fill true, :raised true, :panelopen "right"} "Right Panel"]]]
   [:>
    (f7list)
    [:>
     (listitem)
     {:title "About App",
      :link  "#/homepanel"}]
    [:>
     (listitem)
     {:title "Dynamic (Component) Route",
      :link  "/dynamic-route/blog/45/post/125/?foo=bar#about"}]
    [:>
     (listitem)
     {:title "Default Route (404)", :link "/load-something-that-doesnt-exist/"}]
    [:>
     (listitem)
     {:title "Request Data & Load", :link "/request-and-load/user/123456/"}]]])


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
        :home-page         [home-panel]
        :edit-route-panel  [edit-route-panel]
        :saved-routes-page [saved-routes-panel]
        :home-panel        [home-panel]
        :about-panel       [about-panel]
        [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))

(defn basic-page []
  [:>
   (page)
   [:>
    (navbar)
    {:sliding false, :large true}
    [:>
     (navleft)
     [:>
      (link)
      {:iconIos    "f7:menu",
       :iconAurora "f7:menu",
       :iconMd     "material:menu",
       :panelOpen  "left"}]]
    [:> (navtitle) {:sliding true} "WheelyWonka"]
    [:>
     (navright)
     [:>
      (link)
      {:iconIos    "f7:menu",
       :iconAurora "f7:menu",
       :iconMd     "material:menu",
       :panelOpen  "right"}]]]
   [main-panel]])

(def react-main-panel (reagent/reactify-component main-panel))

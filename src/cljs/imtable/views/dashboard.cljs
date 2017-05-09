(ns imtable.views.dashboard
  (:require [re-frame.core :as re-frame :refer [dispatch subscribe]]))

(defn main [loc]
  (let [results (subscribe [:results loc])]
    (fn []
      [:table
       [:tr
        [:td {:on-click (fn [] (dispatch [:doit loc]))} "test"]]])))

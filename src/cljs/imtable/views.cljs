(ns imtable.views
  (:require [re-frame.core :as re-frame :refer [dispatch]]
            [imtable.views.dashboard :as dashboard]))

(defn main-panel [loc]
  (let [name (re-frame/subscribe [:name])]
    (fn [loc]
      [dashboard/main loc])))

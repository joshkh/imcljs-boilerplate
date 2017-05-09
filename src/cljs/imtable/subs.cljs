(ns imtable.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame :refer [reg-sub]]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))

(reg-sub
  :results
  (fn [db [_ loc]]
    (get-in db (concat loc [:response :results]))))
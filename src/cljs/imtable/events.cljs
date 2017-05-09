(ns imtable.events
  (:require [re-frame.core :as re-frame :refer [reg-event-db reg-event-fx reg-fx]]
            [imtable.db :as db]
            [imtable.interceptors :refer [sandbox]]
            [imtable.effects]
            [imcljs.fetch :as fetch]))


(def test-service {:root "beta.flymine.org/beta"
                   :model {:name "genomic"}})

(def test-query {:from "Gene"
                 :select ["symbol"]
                 :where [{:path "Gene.symbol"
                          :op "="
                          :value "ab*"}]})

(re-frame/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(reg-event-db
  :store-results
  (sandbox)
  (fn [db [_ loc results]]
    (assoc db :response results)))

(reg-event-fx
  :doit
  (sandbox)
  (fn [{db :db} [_ loc]]
    {:db (assoc db :running true)
     :io {:on-success [:store-results loc]
          :on-failure [:deny-results loc]
          :chan (fetch/records test-service test-query {:size 10})}}))

(defn left-pad-replace
  "Replace values into a collection padding left when necessary"
  [index col values & [padding]]
  (concat (take index (concat col (repeat padding)))
          values
          (drop (+ index (count values)) col)))

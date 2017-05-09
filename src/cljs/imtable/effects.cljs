(ns imtable.effects
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [re-frame.core :refer [dispatch reg-fx]]
            [cljs.core.async :refer [<!]]))

(reg-fx
  :io
  (fn [{:keys [on-success on-failure chan]}]
    (go
      (let [{:keys [statusCode] :as response} (<! chan)]
        (if (and statusCode (>= statusCode 400))
          (some-> on-failure dispatch)
          (some-> on-success (conj response) dispatch))))))


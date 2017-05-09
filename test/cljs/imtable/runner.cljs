(ns imtable.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [imtable.core-test]))

(doo-tests 'imtable.core-test)

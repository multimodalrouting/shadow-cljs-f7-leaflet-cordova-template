(ns multimodal-re-frame.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [multimodal-re-frame.core-test]))

(doo-tests 'multimodal-re-frame.core-test)

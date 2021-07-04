(ns p1613
  (:import (java.io BufferedReader StringReader))
   (:require [clojure.string :as str]))
(use 'clojure.test)

(def inf 9000)

(defn next-visit [from rels]
  (map
    #(nth % 1)
    (filter #(= from (nth % 0)) rels)))

(defn reaches? [from to rels]
  (cond
    (= to []) false
    (= from to) true
    :else (reduce
      (fn [reached next-from]
        (or reached (reaches? next-from to rels)))
        false
      (next-visit from rels))))

(defn answer [a b rels]
  (cond
    (reaches? b a rels) 1
    (reaches? a b rels) -1
    :else 0))

(defn solve [rels qs]
  (map #(answer (first %) (second %) rels) qs))

(deftest history-test
  (is (=
  ;;problem
  (solve '((1 2) (1 3) (2 3) (3 4) (2 4))
  '((1 5) (2 4) (3 1)))
  ;;answer
  '(0 -1 1))))

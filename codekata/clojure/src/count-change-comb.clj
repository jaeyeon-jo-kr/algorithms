(ns count-change-comb)



(defn list-change
  [money [h & tail :as coins] sols]
  (cond
    (nil? h) []
    (= money h) (cons (cons h sols)
                      (list-change money tail sols))
    (< money h) (list-change money tail sols)
    :else (concat
           (list-change (- money h) tail (cons h sols))
           (list-change (- money h) coins (cons h sols))
           (list-change money tail sols))))

(defn remove-duplicate
  [coll]
  (distinct coll))
  

(defn count-change
  [money coins]
  (println money)
  (list-change money coins []))
             
    

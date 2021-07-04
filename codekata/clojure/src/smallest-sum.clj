(ns smallest-sum)

(defn all-same?
  [[n :as coll]]
  (every? #(= n %) coll))

(defn gcd [a b]
  (if (zero? b) a
      (gcd b (mod a b))))


(defn reduce-sum
  [[a b & tail :as coll]]
  (cond
    (all-same? coll) (reduce + coll)
    (not= a b)
    (let [n (gcd a b)]
      (reduce-sum
       (->> (reverse tail)
            (cons n)
            (cons n)
            reverse)))
    :else
    (reduce-sum
     (->> (reverse tail)
          (cons b)
          (cons a)
          reverse))))

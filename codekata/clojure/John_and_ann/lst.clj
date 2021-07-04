(ns lst)

(defn nxt-kata
  ([] [[1 0]])
  ([coll]
   (let [n (count coll)
        [at jt] (last coll)
        [an jn] (cond
                  (= at n)
                  (let [jn (- n (first (nth coll jt)))]
                    [(- n jn) jn])
                  (= jt n)
                  (let [an (- n (second (nth coll at)))]
                    [an (- n an)])
                  :else [(- n (second (nth coll at))) (- n (first (nth coll jt)))])]
     (conj coll [an jn]))))

(defn nxt-kata
  ([] [[1 0 0 1]])
  ([coll]
   (let [n (count coll)
         [an jn jan ajn] (last coll)]
     [(- n jan) (- n ajn) (second (nth coll (- n jan))) (first (nth coll (- n ajn)))])))

(defn day [n]
  (loop [cnt 1
         coll [[1 0]]]
    (if (= n cnt)
      coll
      (recur (inc cnt) (nxt-kata coll)))))

(defn ann [n]
  (->> (day n)
       (map first)))

(defn john [n]
  (->> (day n)
       (map second)))

(defn sum-john [n]
  (->> (john n)
       (apply +)))

(defn sum-ann [n]
  (->> (ann n)
       (apply +)))


(def is-covered?
  [w enemies-map p] (> w (enemies-count enemies-map p))
  [w enemies-map p1 p2](> w (+ (enemies-count enemies-map p1 p2))))


(def enemies-count
  [enemies-map p] (nth (nth enemies-map (nth p 0)) (nth p 1))
  [enemies-map p1 p2] (+ (enemies-count enemies-map p1) (enemies-count enemies-map p2)))


#(let [first-str (read-line)
       t (read-string first-str)
       second-str (read-line)
       second-values (map read-string (clojure.string/split second-str #" "))
       n (nth second-values 0)
       w (nth second-values 1)
       enemies-strs (for [i (range 0 n)] (read-line))
       enemies-map (map #(map read-string (clojure.string/split % #" ")) enemies-strs)])

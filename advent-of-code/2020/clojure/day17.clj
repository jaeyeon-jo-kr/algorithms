(ns day17)

(def cube-example {[0 0 0] 0})

(def input-1
"##...#.#
#..##..#
..#.####
.#..#...
########
######.#
.####..#
.###.#..")

(def input-2
  ".#.
..#
###")

(def active true)
(def inactive false)

(defn min-max
  [cube]
  (let [xs (map ffirst cube)
        ys (map #(second (first %)) cube)
        zs (map #(nth (first %) 2) cube)]
    [[(apply min xs) (apply max xs)]
     [(apply min ys) (apply max ys)]
     [(apply min zs) (apply max zs)]]))

(defn extend-space
  [cube]
  (let [[[xmin xmax] [ymin ymax] [zmin zmax]] (min-max cube)
        extra-space (for [x (range (dec xmin) (+ xmax 2))
                          y (range (dec ymin) (+ ymax 2))
                          z (range (dec zmin) (+ zmax 2))]
                      [[x y z] inactive])]
    (concat cube extra-space)))
      
(defn to-el
  [ch]
  (if (= ch \#)
    active
    inactive))

(defn gen-cube
  [input]
  (let [input (clojure.string/split-lines input)
        len (count input)
        mid (quot len 2)]
     (for [x (range len)
           y (range len)]
       [[(- x mid) (- y mid) 0]
        (-> (nth input y)
            (nth x)
            to-el)])))

(defn neighbors
  [piece cube]
  (let [[cx cy cz] (first piece)]
    (filter (fn [[[x y z]]]
              (and (<= -1 (- cx x) 1)
                   (<= -1 (- cy y) 1)
                   (<= -1 (- cz z) 1))) cube)))

(defn next-piece-status
  [[_ active-status :as piece] cube]
  (let [neighbors (neighbors piece cube)
        active-neighbors (filter (fn [[_ v]] v) neighbors)]
    (cond
      (and active-status (<= 2 (count active-neighbors) 3)) active
      active-status inactive
      (= (count active-neighbors) 3) active
      :else inactive)))

(defn next-status
  [cube]
  (let [cube (extend-space cube)]
    (map (fn [[pos :as piece]]
           [pos (next-piece-status piece cube)]) cube)))

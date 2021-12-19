;; ;;;
;; --- Day 5: Hydrothermal Venture ---
;; You come across a field of hydrothermal vents on the ocean floor! These vents constantly produce large, opaque clouds, so it would be best to avoid them if possible.

;; They tend to form in lines; the submarine helpfully produces a list of nearby lines of vents (your puzzle input) for you to review. For example:

;; 0,9 -> 5,9
;; 8,0 -> 0,8
;; 9,4 -> 3,4
;; 2,2 -> 2,1
;; 7,0 -> 7,4
;; 6,4 -> 2,0
;; 0,9 -> 2,9
;; 3,4 -> 1,4
;; 0,0 -> 8,8
;; 5,5 -> 8,2
;; Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are the coordinates of one end the line segment and x2,y2 are the coordinates of the other end. These line segments include the points at both ends. In other words:

;; An entry like 1,1 -> 1,3 covers points 1,1, 1,2, and 1,3.
;; An entry like 9,7 -> 7,7 covers points 9,7, 8,7, and 7,7.
;; For now, only consider horizontal and vertical lines: lines where either x1 = x2 or y1 = y2.

;; So, the horizontal and vertical lines from the above list would produce the following diagram:

;; .......1..

;; ..1....1..
;; ..1....1..
;; .......1..
;; .112111211
;; ..........
;; ..........
;; ..........
;; ..........
;; 222111....
;; In this diagram, the top left corner is 0,0 and the bottom right corner is 9,9. Each position is shown as the number of lines which cover that point or . if no line covers that point. The top-left pair of 1s, for example, comes from 2,2 -> 2,1; the very bottom row is formed by the overlapping lines 0,9 -> 5,9 and 0,9 -> 2,9.

;; To avoid the most dangerous areas, you need to determine the number of points where at least two lines overlap. In the above example, this is anywhere in the diagram with a 2 or larger - a total of 5 points.

;; Consider only horizontal and vertical lines. At how many points do at least two lines overlap?

;; To begin, get your puzzle input.

(ns day5 
  (:require [clojure.string :as string]
            [clojure.pprint :as pp]))

(def sample1
  "0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2")

(def problem1
 (slurp "C:\\Users\\enter\\Projects\\algorithms\\advent-of-code\\2021\\clojure\\day5.txt") )

(comment
  {:p1 [x y]
   :p2 [x y]
   :e {:a 1 :b 2 :c 3}})

(defn equals
  "ax + by + c = 0,
   (y2 -y1)x + (x1-x2)y + x2y1 - x1y2 = 0"
  [x1 y1 x2 y2]
   {:a (- y2 y1)
    :b (- x1 x2)
    :c (- (* x2 y1) (* x1 y2))})

(defn create-line
  [[x1 y1 x2 y2]]
  {:p1 {:x x1 :y y1}
   :p2 {:x x2 :y y2}
   :max {:x (max x1 x2) :y (max y1 y2)}
   :min {:x (min x1 x2) :y (min y1 y2)}
   :equals (equals x1 y1 x2 y2)})

(defn parse-line
  [line]
  (->> line
       (re-matches #"(\d+),(\d+) -> (\d+),(\d+)")
       (rest)
       (map create-line)
       read-string))

(defn parse-input
  [input]
  (->> (string/split-lines input)
       (map parse-line)))

(defn line-in?
  [[x y] {{:keys [a b c]} :equals}]
  (zero? (+ (* a x) (* b y) c)))

(defn cross?
  [{{b1 :b a1 :a} :equals}
   {{b2 :b a2 :a} :equals}]
  (zero? (+ (* b1 b2)
            (* a1 a2))))


(defn overlapped-vertical-line
 [{{l1xl :x l1yl :y} :max
   {l1xs :x l1ys :y} :min}
  {{l2xl :x l2yl :y} :max 
   {l2xs :x l2ys :y} :min}]
  (when 
   (and (= l1yl l1ys l2yl l2ys)
        (not (or (< l1xl l2xs)
                 (< l2xl l1xs))))
    (let [[nx0 nx1 nx2 nx3]
          (sort [l1xl l1xs l2xl l2xs])]
      {:overlapped
       [(create-line
         [nx1 l1yl nx2 l1yl])]
       :unoverlapped
       [(create-line
         [nx0 l1yl (dec nx1) l1yl])
        (create-line
         [(inc nx2) l1yl nx3 l1yl])]})))

(defn overlapped-horizontal-line
  [{{l1xl :x l1yl :y} :max
    {l1xs :x l1ys :y} :min}
   {{l2xl :x l2yl :y} :max
    {l2xs :x l2ys :y} :min}]
  (when
   (and (= l1xl l1xs l2xl l2xs)
        (not (or (< l1yl l2ys)
                 (< l2yl l1ys))))
    (let [[ny0 ny1 ny2 ny3]
          (sort [l1yl l1ys l2yl l2ys])]
      {:overlapped
       [(create-line
         [l1xl ny1 l1xs ny2])]
       :unoverlapped
       [(create-line
         [l1xl ny0 l1xl (dec ny1)])
        (create-line
         [l1xl (inc ny2) l1xl ny3])]})))

(defn overlap-points
  [[min-x1 max-x1] [min-x2 max-x2]]
  (let [[x0 x1 x2 x3] (sort [min-x1 min-x2 min-x2 max-x2])]
    (cond
      (= x0 x1 x2 x3)
      {:overlapped
       [[x0 x0]]
       :unoverlapped
       []}
      
      (= x0 x1 x2) 
      {:overlapped
       [[x0 x0]]
       :unoverlapped
       [[(inc x0) x3]]}
      
      (= x1 x2 x3) 
       {:overlapped
        [[x1 x1]]
        :unoverlapped
        [[x0 (dec x1)]]}

      (and
       (< x0 x1 x2 x3)
       (< (min max-x1 max-x2) (max min-x1 min-x2)))
      {:overlapped
       [[x1 x2]]
       :unoverlapped
       [[x0 (dec x1)]
        [(inc x2) x3]]}
      
      (and
       (< x0 x1 x2 x3)
       (< (max min-x1 min-x2) (min max-x1 max-x2)))
      {:overlapped
       []
       :unoverlapped
       [[x0 x1] [x2 x3]]}

      (and (< x0 x1 x3)
           (= x1 x2))
      {:overlapped
       [[x1 x2]]
       :unoverlapped
       [[x0 (dec x1)]
        [(inc x1) x3]]})))

(defn overlap-line
  [{{l1xl :x l1yl :y} :max
    {l1xs :x l1ys :y} :min}
   {{l2xl :x l2yl :y} :max
    {l2xs :x l2ys :y} :min}]
  (let [{overlap-x :overlapped
         unoverlap-x :unoverlapped} 
        (overlap-points [l1xs l1xl] [l2xs l2xl])

        {overlap-y :overlapped
         unoverlap-y :unoverlapped} 
        (overlap-points [l1ys l1yl] [l2ys l2yl])]
    
    
    
    )
 )



(comment 
  (def a1 (create-line [0 3 0 2]))
  (def a2 (create-line [0 2 0 8]))
  
  (overlapped a1 a2))

(defn count-point
  [[[x1 y1] [x2 y2]]]
  (* (inc (Math/abs (- x2 x1)))
     (inc (Math/abs (- y2 y1)))))

(defn solve [input-str]
  (->> (string/split-lines input-str)
       (map parse-line)
       (overlap-all)
       (map count-point)
       (reduce +)
       (* 0.5)
       int))



(comment
  (->> (string/split-lines sample1)
      (map parse-line))
      1
  (solve sample1)
  (solve problem1)
  
  (overlapped [[0 9] [5 9]] 
               [[0 9] [4 9]])
   (parse-input sample1))



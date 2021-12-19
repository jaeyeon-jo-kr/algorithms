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

(defn create-line
  [[x1 y1 x2 y2 :as pt]]
  (println pt)
  {:p1 {:x x1 :y y1}
   :p2 {:x x2 :y y2}
   :max {:x (max x1 x2) :y (max y1 y2)}
   :min {:x (min x1 x2) :y (min y1 y2)}
   :equals {:a (- y2 y1)
            :b (- x1 x2)
            :c (- (* x2 y1) (* x1 y2))}})
(defn calculate
  [{:keys [x y]} {{:keys [a b c]} :equals}]
  (+ (* a x) (* b y) c))

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

(defn straight-line-in?
  [p line]
  (zero? (calculate p line)))

(defn area-in?
  [{:keys [x y]} {{ys :y xs :x} :min
                  {yl :y xl :x} :max}]
  (and 
       (<= xs x xl)
       (<= ys y yl)))

(defn point-included?
  [p line]
  (and (straight-line-in? p line)
       (area-in? p line)))

(defn excluded-but-straight?
  [p line]
  (and (not (area-in? p line))
       (straight-line-in? p line)))

(defn line-included?
  [{{x1 :x y1 :y} :p1
    {x2 :x y2 :y} :p2} line]
  (and (point-included? [x1 y1] line)
       (point-included? [x2 y2] line)))

(defn line-intersected?
  [{p1 :p1 p2 :p2} line]
  (or (and (point-included? p1 line)
           (excluded-but-straight? p2 line))
      (and (point-included? p2 line)
           (excluded-but-straight? p1 line))))

(defn point-intersected?
  [{p1 :p1 p2 :p2} line]
  (not (or
        (< 0 (calculate p1 line) (calculate p2 line))
        (> 0 (calculate p1 line) (calculate p2 line)))))

(defn get-x 
  [y {{x :x} :p1
      {:keys [a b c]} :equals}]
  (if (not (zero? a))
    (-> (* b y)
        (+ c)
        (/ a)
        -)
    y))

(defn get-y
  [x {{y :y} :p1
      {:keys [a b c]} :equals}]
  (if (not (zero? b))
    (-> (* a x)
        (+ c)
        (/ b)
        -)
    y))

(defn intersection-point
  [{{x1 :x y1 :y} :p1
    {x2 :x y2 :y} :p2}
   {{x3 :x y3 :y} :p1
    {x4 :x y4 :y} :p2}]
  (let [denominator
        (- (* (- x1 x2)
              (- y3 y4))
           (* (- y1 y2)
              (- x3 x4)))]
    {:x (/ (- (* (- (* x1 y2) (* y1 x2))
                 (- x3 x4))
              (* (- x1 x2)
                 (- (* x3 x4)
                    (* y3 x4))))
           denominator)
     :y (/ (- (* (- (* x1 y2) (* y1 x2))
                 (- y3 y4))
              (* (- y1 y2)
                 (- (* x3 x4)
                    (* y3 x4))))
           denominator)}))

(defn exclude-point
  [{:keys [x y]} {{x1 :x y1 :y} :p1
                 {x2 :x y2 :y} :p2}]
  (let [x3 (cond
             (< x x1) (inc x)
             (< x1 x) (dec x)
             (= x1 x) nil)
        x4 (cond
             (< x x2) (inc x)
             (< x2 x) (dec x)
             (= x2 x) nil)
        y3 (cond
             (< y y1) (inc y)
             (< y1 y) (dec y)
             (= y1 y) nil)
        y4 (cond
             (< y y2) (inc y)
             (< y2 y) (dec y)
             (= y2 y) nil)]
    (->> [(when (and x3 y3)
            (create-line [x1 y1 x3 y3]))
          (when (and x4 y4)
            (create-line [x4 y4 x2 y2]))]
         (keep identity))))

(defn overlap-line
  [{{x1 :x y1 :y} :min
    {x2 :x y2 :y} :max    :as line1}
   {{x3 :x y3 :y} :min
    {x4 :x y4 :y} :max    :as _line2}]
  (println x4)
  (println line1)
  (cond
    (and
     (not= (min x1 x3) (max x1 x3) (min x2 x4) (max x2 x4))
     (<= (min x1 x3) (max x1 x3) (min x2 x4) (max x2 x4)))
    
    {:overlapped
     [(create-line
       [(max x1 x3)
        (get-y (max x1 x3) line1)
        (min x2 x4)
        (get-y (min x2 x4) line1)])]
     :unoverlapped
    (->>W
     [(when (not= x1 x3)
        (create-line
         [(min x1 x3)
          (get-y (min x1 x3) line1)
          (dec (max x1 x3))
          (get-y (dec (max x1 x3)) line1)]))
      (when (not= x2 x4)
        (create-line
         [(inc (min x2 x4))
          (get-y (inc (min x2 x4)) line1)
          (dec (max x2 x4))
          (get-y (dec (max x2 x4)) line1)]))]
     (keep identity))}

    (and 
     (not= (min y1 y3) (max y1 y3) (min y2 y4) (max y2 y4))
     (<= (min y1 y3)
         (max y1 y3)
         (min y2 y4)
         (max y2 y4)))
    {:overlapped
     [(create-line
       [(get-x (max y1 y3) line1)
        (max y1 y3)
        (get-x (min y2 y4) line1)
        (min y2 y4)])]
     :unoverlapped []}))

(comment 
  (def a1 (create-line [0 3 0 2]))
  (def a2 (create-line [0 2 0 8]))
  
  (overlap-line a1 a2)
  )

(defn count-point
  [[[x1 y1] [x2 y2]]]
  (* (inc (Math/abs (- x2 x1)))
     (inc (Math/abs (- y2 y1)))))

(defn solve [input-str]
  (->> (string/split-lines input-str)
       (map parse-line)
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



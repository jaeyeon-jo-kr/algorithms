;; --- Day 4: Giant Squid ---
;; You're already almost 1.5km (almost a mile) below the surface of the ocean, already so deep that you can't see any sunlight. What you can see, however, is a giant squid that has attached itself to the outside of your submarine.

;; Maybe it wants to play bingo?

;; Bingo is played on a set of boards each consisting of a 5x5 grid of numbers. Numbers are chosen at random, and the chosen number is marked on all boards on which it appears. (Numbers may not appear on all boards.) If all numbers in any row or any column of a board are marked, that board wins. (Diagonals don't count.)

;; The submarine has a bingo subsystem to help passengers (currently, you and the giant squid) pass the time. It automatically generates a random order in which to draw numbers and a random set of boards (your puzzle input). For example:

;; 7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

;; 22 13 17 11  0
;;  8  2 23  4 24
;; 21  9 14 16  7
;;  6 10  3 18  5
;;  1 12 20 15 19

;;  3 15  0  2 22
;;  9 18 13 17  5
;; 19  8  7 25 23
;; 20 11 10 24  4
;; 14 21 16 12  6

;; 14 21 17 24  4
;; 10 16 15  9 19
;; 18  8 23 26 20
;; 22 11 13  6  5
;;  2  0 12  3  7

;; After the first five numbers are drawn (7, 4, 9, 5, and 11), there are no winners, but the boards are marked as follows (shown here adjacent to each other to save space):

;; 22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
;;  8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
;; 21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
;;  6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
;;  1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
;; After the next six numbers are drawn (17, 23, 2, 0, 14, and 21), there are still no winners:

;; 22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
;;  8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
;; 21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
;;  6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
;;  1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
;; Finally, 24 is drawn:

;; 22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
;;  8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
;; 21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
;;  6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
;;  1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
;; At this point, the third board wins because it has at least one complete row or column of marked numbers (in this case, the entire top row is marked: 14 21 17 24 4).

;; The score of the winning board can now be calculated. Start by finding the sum of all unmarked numbers on that board; in this case, the sum is 188. Then, multiply that sum by the number that was just called when the board won, 24, to get the final score, 188 * 24 = 4512.

;; To guarantee victory against the giant squid, figure out which board will win first. What will your final score be if you choose that board?

(ns day4)

(def sample "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
             
22 13 17 11 0
8  2 23  4 24
21  9 14 16 7
6 10  3 18 5
1 12 20 15 19
             
3 15  0  2 22
9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6
             
14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
2  0 12  3  7")

(def day4-file 
  "C:\\Users\\enter\\Projects\\algorithms\\advent-of-code\\2021\\clojure\\day4.txt")

(def day4-input 
  (slurp day4-file))

(defn parse-input
  [input]
  (let [[numbers & boards] (clojure.string/split-lines input)
        numbers (->> (clojure.string/split numbers #",")
                     (map read-string))
        boards (->> boards
                    (remove #(re-matches #" *" %))
                    (map (comp
                          #(map read-string %)
                          #(clojure.string/split % #" +")
                          clojure.string/trim))
                    (partition-all 5))]
    {:numbers numbers
     :boards boards}))

(defn initialize-boards
  [boards]
  (let [init-row (fn [r] (map #(vector % false) r))
        init-board #(map init-row %)]
    (map init-board boards)))

(defn mark-boards
  [n boards]
  (let [mark-cell (fn [[cell mark]]
                   (if (= cell n)
                     [cell true]
                     [cell mark]))
        mark-row (fn [row] (map mark-cell row))
        mark-board (fn [board] (map mark-row board))]
    (map mark-board boards)))

(def bingo-row? 
  (fn [row] 
    (every? second row)))

(defn flip
  [board]
    (map (fn [n]
           (map #(nth % n) board)) (range 5)))

(defn bingo? 
  [board]
  
         (when (->> (concat (flip board) board)
                    (some bingo-row?))
           board))

(defn scan-winner
  [boards]
  (->> (filter bingo? boards)
       seq))


(defn except-board
  [boards winner-boards]
  (remove #(some 
            (fn[b] (= b %))
            winner-boards) 
          boards))

(defn first-win
  [winning-boards _]
  (seq winning-boards))

(defn last-win
  [winning-boards boards]
  (= winning-boards boards))

(defn find-solution
  [mark winning-cond]
 (fn step [numbers boards]
    (loop [[n & next-numbers] numbers
           boards boards]
      (let [boards (mark n boards)
            winning-boards (scan-winner boards)
            non-winning-boards (except-board boards winning-boards)]
        (cond 
          (winning-cond winning-boards boards)
          (do 
            (clojure.pprint/pprint winning-boards)
            [(first winning-boards) n]) 
          
          (nil? n) nil
          :else (recur next-numbers non-winning-boards))))))



(defn calculate
  [board n]
  (->> (apply concat board)
       (remove second)
       (map first)
       (reduce +)
       ((fn [n] (println n) n))
       (* n)))

(defn solve 
  [mark-boards scan-winner]
  (fn [input]
    (let [{:keys [boards numbers]} (parse-input input)
          boards (initialize-boards boards)
          [board winning-number]
          ((find-solution mark-boards scan-winner) numbers boards)]
      (clojure.pprint/pprint winning-number)
      (clojure.pprint/pprint board)
      (calculate board winning-number))))



(comment
  (let [{:keys [numbers boards]}
        (parse-input sample)
        boards (initialize-boards boards)]
    (mark-boards 10 boards))

  (scan-winner '((([22 true] [13 true] [17 true] [11 true] [0 false])
                  ([8 true] [2 false] [23 false] [4 false] [24 false])
                  ([21 true] [9 false] [14 false] [16 false] [7 false])
                  ([6 true] [10 true] [3 false] [18 false] [5 false])
                  ([1 true] [12 false] [20 false] [15 false] [19 false]))
                 (([3 true] [15 false] [0 false] [2 false] [22 false]) ([9 false] [18 false] [13 false] [17 false] [5 false]) ([19 false] [8 false] [7 false] [25 false] [23 false]) ([20 false] [11 false] [10 true] [24 false] [4 false]) ([14 false] [21 false] [16 false] [12 false] [6 false])) (([14 false] [21 false] [17 false] [24 false] [4 false]) ([10 true] [16 false] [15 false] [9 false] [19 false]) ([18 false] [8 false] [23 false] [26 false] [20 false]) ([22 false] [11 false] [13 false] [6 false] [5 false]) ([2 false] [0 false] [12 false] [3 false] [7 false]))))

  (apply concat '(([22 true] [13 true] [17 true] [11 true] [0 false])
                  ([8 true] [2 false] [23 false] [4 false] [24 false])
                  ([21 true] [9 false] [14 false] [16 false] [7 false])
                  ([6 true] [10 true] [3 false] [18 false] [5 false])
                  ([1 true] [12 false] [20 false] [15 false] [19 false])))
  ((solve mark-boards first-win) sample)
  ((solve mark-boards last-win) sample)

  (parse-input ())

  ((solve mark-boards first-win) day4-input)
  ((solve mark-boards last-win) day4-input)

  )


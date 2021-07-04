(ns sudoku-validator)


(def sample1 [[5 3 4 6 7 8 9 1 2]
              [6 7 2 1 9 5 3 4 8]
              [1 9 8 3 4 2 5 6 7]
              [8 5 9 7 6 1 4 2 3]
              [4 2 6 8 5 3 7 9 1]
              [7 1 3 9 2 4 8 5 6]
              [9 6 1 5 3 7 2 8 4]
              [2 8 7 4 1 9 6 3 5]
              [3 4 5 2 8 6 1 7 9]])
(def sample2 [[1 2 3 4 5 6 7 8 9]
              [2 3 1 5 6 4 8 9 7]
              [3 1 2 6 4 5 9 7 8]
              [4 5 6 7 8 9 1 2 3]
              [5 6 4 8 9 7 2 3 1]
              [6 4 5 9 7 8 3 1 2]
              [7 8 9 1 2 3 4 5 6]
              [8 9 7 2 3 1 5 6 4]
              [9 7 8 3 1 2 6 4 5]])

(def total (apply + (range 1 10)))

(defn not-duplicate? [col]
  (= col (distinct col)))

(defn valid-sum? [col]
  (= total (apply + col)))

(defn valid-line? [col]
  (and (not-duplicate? col) (valid-sum? col)))

(defn cross-to-right [cols]
  (map-indexed (fn [i col] (nth col i)) cols))

(defn cross-to-left [cols]
  (map-indexed (fn [i col] (nth col (- 8 i))) cols))

(defn pick-cols [cols]
  (fn [row-num]
    (map #(nth % row-num) cols)))

(defn row-seqs [cols]
  (->> (range 9)
       (map (pick-cols cols))))

(defn row-valid? [board]
  (->> board
       row-seqs
       (map valid-line?)
       (every? true?)))

(defn col-valid? [board]
  (->> board
       (map valid-line?)
       (every? true?)))

(defn valid-solution [board]
  (let [row-valid (row-valid? board)
        col-valid (col-valid? board)]
    (and col-valid row-valid)))
    
    

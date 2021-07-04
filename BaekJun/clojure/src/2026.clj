;;원장선생님께서는 1부터 N까지 번호가 붙은 N(K ≤ N ≤ 900)명의 학생들 중에서 K(1 ≤ K ≤ 62)명의 학생들을 소풍에 보내려고 한다.
;; 그런데 원장선생님께서는 중간에 싸움이 일어나면 안되므로 소풍을 갈 학생들이 모두 서로 친구 사이이기를 원한다.
;; 원장선생님께서는 이러한 일을 이번에 조교로 참가한 고은이에게 친구 관계에 대한 정보를 F(1 ≤ F ≤ 5,600)개를 주시며 K명을 선발하라고 부탁하였다.
;; 고은 조교를 도와 소풍을 가게 될 K명의 학생들을 결정하시오.

;;첫째 줄에 공백으로 분리된 세 정수 K, N, F가 주어진다.
;; 다음 F개의 줄에는 서로 친구 관계인 두 사람의 번호가 주어진다.
;; 친구 관계는 상호적인 관계이므로 2번 학생이 4번 학생을 좋아하면 4번 학생도 2번 학생을 좋아한다.

;;만약 K명의 친구 관계인 학생들이 존재하지 않는다면 -1을 출력한다.
;;그 외의 경우에는, K개의 줄에 학생들의 번호를 증가하는 순서로 한 줄에 한 개씩 출력한다.
;; 여러 경우가 존재한다면 첫 번째 학생의 번호가 제일 작은 것을 출력한다.
;; 첫 번째 학생의 번호가 같은 경우라면, 두 번째 학생의 번호가 작은 경우를 출력하고, 이와 같은 식으로 출력한다.

(ns back2026
    (:require clojure (
      [clojure.set as c-set]
      [string :as str])))

(defn main []
  (let
    [splits (fn [s] (clojure.string/split s #" "))
    line-to-ints (fn [line] (map (fn [s] (Integer/parseInt s)) (splits line)))
    read-n-lines (fn [n] (repeatedly n read-line))

    first-line (read-line)
    first-values (map read-string (splits first-line))
    k (nth first-values 0)
    n (nth first-values 1)
    f (nth first-value 2)

    relations (map line-to-ints (read-n-lines f))

    find(k n f relations)))

(defn max-count [coll]
  (max (map #(count %) coll)))

(defn extend [e set]
  (for [x set y set]
    (if (empty? (difference e x y))

(defn splits [k-set]
      (split-at (div (count 2) k-set) k-set))

(defn coupling [k-set e]
      let [coupled (splits k-set)]
      (for [x (nth coupled 0)
            y (nth coupled 1)
            :when? (not (empty? (difference e x y))) ]
           (c-set/union e x y)))

(defn find-solution [k n f relations]
  (loop
    [e (first relations)
    set #{}]
    (if
      (empty? e) (max-count set)
      (recur (second relations) (set (coupling set e))))))
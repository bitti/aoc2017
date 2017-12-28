(ns aoc2017.day22
  (:require [clojure.set :as set]))

(defn slurp-from-stdin []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       (map #(vec (map (fn [c] (= c \#)) %)))
       into-array))

(def ^:const turn-left
  {[ 0  1] [ 1  0]
   [ 1  0] [ 0 -1]
   [ 0 -1] [-1  0]
   [-1  0] [ 0  1]})

(def ^:const turn-right (set/map-invert turn-left))

(defn infection-map [infection-matrix]
  { :pre [(= (count infection-matrix) (count (first infection-matrix)))]} ; Assuming square matrix
  (let [size (count infection-matrix)
        start (- (int (/ size 2)))]
    (first (reduce (fn [[inf row] row-nodes]
                     [(set/union
                       inf
                       (first (reduce (fn [[inf col] node]
                                        (if node
                                          [(conj inf [col row]) (inc col)]
                                          [inf (inc col)]))
                                      [#{} start]
                                      row-nodes)))
                      (inc row)])
                   [#{} start]
                   infection-matrix))))

(defn advance [input burst]
  (let [infections (infection-map input)]
    (loop [step 0
           infections infections
           new-infections 0
           coord [0 0]
           dir [0 -1]]
      (cond
        (= step burst) new-infections

        (infections coord)
        (recur (inc step)
               (disj infections coord)
               new-infections
               (map + coord (turn-right dir))
               (turn-right dir))
        :else
        (recur (inc step)
               (conj infections coord)
               (inc new-infections)
               (map + coord (turn-left dir))
               (turn-left dir))))))

(def ^:const state-transitions
  {:clean    :weakened
   :weakened :infected
   :infected :flagged
   :flagged  :clean})

(def ^:const evolved-rules
  {:clean    turn-left
   :weakened identity
   :infected turn-right
   :flagged  (fn [[^long x ^long y]] [(- x) (- y)])})

;; Don't know why there is no transient version of update
(defn update!
  [m k f x]
  (assoc! m k (f (get m k) x)))

(defn evolved-virus [input burst]
  (let [infection-states (transient
        (reduce (fn [inf coord] (assoc inf coord :infected))
                {}
                (infection-map input)))]
    (loop [step 0
           infection-states infection-states
           new-infections 0
           [^long x ^long y :as coord] [0 0]
           dir [0 -1]]
      (if (= step burst)
        new-infections
        (let [state (get infection-states coord :clean)
              [^long dx ^long dy :as new-dir] ((evolved-rules state) dir)]
          (recur (inc step)
                 (update! infection-states coord state-transitions :weakened)
                 (if (= state :weakened) (inc new-infections) new-infections)
                 [(+ x dx) (+ y dy)]
                 new-dir))))))

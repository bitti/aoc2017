(ns aoc2017.day22 (:require [clojure.string :as s]))
(set! *unchecked-math* true)

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

(def ^:const turn-right (clojure.set/map-invert turn-left))

(defn infection-map [infection-matrix]
  { :pre [(= (count infection-matrix) (count (first infection-matrix)))]} ; Assuming square matrix
  (let [size (count infection-matrix)
        start (- (int (/ size 2)))]
    (first (reduce (fn [[inf row] row-nodes]
                     [(clojure.set/union
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

(def ^:const state-transitions
  {:clean    :weakened
   :weakened :infected
   :infected :flagged
   :flagged  :clean})

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
               (map + coord (turn-right dir)) ; Luckily a seq matches a vec in a set/map
               (turn-right dir))
        :else
        (recur (inc step)
               (conj infections coord)
               (inc new-infections)
               (map + coord (turn-left dir))
               (turn-left dir))))))

(defn evolved-virus [input burst]
  (let [infection-states (reduce (fn [inf coord] (assoc inf coord :infected)) {} (infection-map input))]
    (loop [step 0
           infection-states infection-states
           new-infections 0
           coord [0 0]
           dir [0 -1]]
      (if (= step burst)
        new-infections
        (let [step (inc step)
              new-state (update infection-states coord state-transitions :weakened)]
          (case (infection-states coord :clean)
            :clean
            (recur step new-state new-infections
                   (map + coord (turn-left dir))
                   (turn-left dir))
            
            :weakened
            (recur step new-state (inc new-infections)
                   (map + coord dir)
                   dir)
            
            :infected
            (recur step new-state new-infections
                   (map + coord (turn-right dir))
                   (turn-right dir))
            
            :flagged
            (recur step new-state new-infections
                   (map - coord dir)
                   (map - dir))))))))

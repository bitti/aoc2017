(ns aoc2017.day9)

(defn scores-from-stdin []
  (->>
   *in*
   slurp
   (reduce (fn [[[score level :as s] in-garbage [garbage-count ignore-next :as g]] char]
             (if in-garbage
               (if ignore-next
                 [s in-garbage [garbage-count false]]
                 (case char
                   \! [s true [garbage-count true]]
                   \> [s false g]
                   [s true [(inc garbage-count) false]]))
               (case char
                 \< [s true g]
                 \{ [[(+ score level) (inc level)] false g]
                 \} [[score (dec level)] false g]
                 [s false g])))
           [[0 1] false [0 false]])))

(defn score-groups-from-stdin []
  (let [[[score _] _ _] (scores-from-stdin)] score))

(defn count-garbage-from-stdin []
  (let [[_ _ [garbage-count _]] (scores-from-stdin)] garbage-count))

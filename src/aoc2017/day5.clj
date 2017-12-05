(ns aoc2017.day5)

(defn steps-to-jump-out [^long p ^long c ^ints v]
  (if (not (> (alength v) p -1))
    c
    (let [jump (aget v p)]
      (aset-int v p (inc jump))
      (recur (+ p jump) (inc c) v))))

(defn strange-steps-to-jump-out [^long p ^long c ^ints v]
  (if (not (> (alength v) p -1))
    c
    (let [jump (aget v p)]
      (aset-int v p ((if (>= jump 3) dec inc) jump))
      (recur (+ p jump) (inc c) v))))

(defn steps-to-jump-out-from-stdin []
  (->>
   *in*
   line-seq
   (map #(Integer/valueOf %))
   int-array
   (steps-to-jump-out 0 0)))

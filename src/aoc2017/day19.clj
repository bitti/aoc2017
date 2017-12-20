(ns aoc2017.day19)

(defn slurp-map-from-stdin []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       into-array))

(defn record-chars-on-path [input]
  (loop [x (clojure.string/index-of (aget input 0) \|)
         y 0
         xd 0
         yd 1
         letters []
         steps 0]
    (let [c (get-in input [y x])
          steps (inc steps)]
      (case c
        \space [(clojure.string/join letters) (dec steps)]
        ;; Assume we always change direction on a + and padding is
        ;; sufficient to avoid checking for edges
        \+ (if (= yd 0)
             (if (not= (get-in input [(dec y) x]) \space)
               (recur x (dec y) 0 -1 letters steps)
               (recur x (inc y) 0 1 letters steps))
             (if (not= (get-in input [y (dec x)]) \space)
               (recur (dec x) y -1 0 letters steps)
               (recur (inc x) y 1 0 letters steps)))
        (if (re-matches #"[A-Z]" (str c))
          (recur (+ x xd) (+ y yd) xd yd (conj letters c) steps)
          (recur (+ x xd) (+ y yd) xd yd letters steps))))))

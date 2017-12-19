(ns aoc2017.day14
  (:use aoc2017.day10))

(def popcount
  { \0 0 \1 1 \2 1 \3 2 \4 1 \5 2 \6 2 \7 3 \8 1 \9 2 \a 2 \b 3 \c 2 \d 3 \e 3 \f 4})

(defn used-squares [key]
  (->>
   (range 128)
   (map (fn [row] (str key "-" row)))
   (map (fn [input] (reduce (fn [sum c] (+ sum (popcount c)))
                            0
                            (full-hash-cycle 256 input))))
   (reduce +)))

(defn remove-group! [a x y]
  (let [row (nth a x false)]
    (when (and row (nth row y false))
      (aset a x y false)
      (remove-group! a (dec x) y)
      (remove-group! a (inc x) y)
      (remove-group! a x (dec y))
      (remove-group! a x (inc y)))))

(defn used-groups [key]
  (->>
   (range 128)
   (map (fn [row] (str key "-" row)))
   (map (fn [input] (->>
                     input
                     (full-hash-cycle 256)
                     ;; A bit of a hack, but I want to avoid bit twiddeling here
                     (#(BigInteger. % 16))
                     (#(format "%128s" (.toString % 2)))
                     (map #(= \1 %))
                     boolean-array)))
   into-array
   ((fn [a]
       (loop [x 0, y 0, groups 0]
         (cond
           (> x 127) (recur 0 (inc y) groups)
           (> y 127) groups
           (aget a x y) (do                        
                          (remove-group! a x y)
                          (recur (inc x) y (inc groups)))
           :else (recur (inc x) y groups)))))))

(ns aoc2017.day10)

(defn hash-string-cycle [size lengths]
  (let [b (int-array (range 0 size))]
    (reduce
     (fn [[pos skip-size] length]
       ;; Use a loop to reverse list of elements
       (loop [p 0]
         (when (< p (quot length 2))
           (let [upperpos (mod (+ pos length -1 (- p)) size)
                 oldval (get b upperpos)
                 lowerpos (mod (+ pos p) size)]
             (aset-int b upperpos (get b lowerpos))
             (aset-int b lowerpos oldval))
           (recur (inc p))))
       [(+ pos length skip-size) (inc skip-size)])
     [0 0] lengths)
    (vec b)))

(defn full-hash-cycle [size input]
  (->>
   input
   (map int)
   (#(concat % [17, 31, 73, 47, 23]))
   (repeat 64)
   flatten
   (hash-string-cycle size)
   (partition 16)
   (map #(format "%02x" (reduce bit-xor %)))
   clojure.string/join))

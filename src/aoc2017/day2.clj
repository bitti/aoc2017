(ns aoc2017.day2)

(defn toInt [s]
  (Integer/valueOf s))

(defn extract-divisibles [a]
  (for [x a
        y a :while (not (= x y))]
    (and (or (= (mod x y) 0) (= (mod y x) 0)) [x y])))

(defn spreadsheet-chksum []
  (let [rows (clojure.string/split (slurp *in*) #"\n")
        ints (map #(map toInt (clojure.string/split %1 #"[\n\t]")) rows)]
    (apply + (map #(- (apply max %) (apply min %)) ints))))

(defn spreadsheet-evenly-divisible-checksum []
  (let [rows (clojure.string/split (slurp *in*) #"\n")
        ints (map #(map toInt (clojure.string/split %1 #"[\n\t]")) rows)]
    (reduce (fn [s e] (+ s e))
            (map (fn [x]
                   (reduce
                    #(if %2 (let [[a b] %2] (if (> a b) (/ a b) (/ b a))) %1)
                    (extract-divisibles x)))
                 ints))))

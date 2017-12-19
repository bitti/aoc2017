(ns aoc2017.day15)

(defmacro mod-multiply [a b]
  `(Long/remainderUnsigned (unchecked-multiply ~a ~b) Integer/MAX_VALUE))

(defn generator [^long start ^long factor]
  (iterate (fn [^long prev-value] (mod-multiply prev-value factor))
           start))

(defn picky-generator [^long start ^long factor ^long mask]
  (iterate (fn [^long prev-value]
             (loop [new-value (mod-multiply prev-value factor)]
               (if (= (bit-and mask new-value) 0)
                 new-value
                 (recur (mod-multiply new-value factor)))))
           start))

(defmacro match-lower16? [a b]
  `(= (bit-and 0xffff ~a) (bit-and 0xffff ~b)))

;; Hardcoded loop but 10 times faster than using generators for generic-judge
(defn judge [^long starta ^long startb]
  (loop [ga starta
         gb startb
         step 0
         matches 0]
    (let [new-ga (mod-multiply ga 16807)
          new-gb (mod-multiply gb 48271)]
      (if (< step 40000000)
        (recur new-ga new-gb (inc step)
               (if (match-lower16? (long new-ga) (long new-gb)) (inc matches) matches))
        matches))))

(defn generic-judge [pairs generator-a generator-b]
  (->>
   (map vector generator-a generator-b)
   (take pairs)
   (filter (fn [[^long a ^long b]] (match-lower16? a b)))
   count))

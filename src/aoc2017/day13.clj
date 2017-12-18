(ns aoc2017.day13)
(set! *unchecked-math* :warn-on-boxed)
(set! *warn-on-reflection* true)

(defn parse-layer-ranges []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       doall
       (map (fn [line] (map #(Integer/valueOf ^String %) (clojure.string/split line #": "))))))

(defn caught? [^long depth ^long range]
  ;; We assume there is no range of 0 or 1
  (= 0 (unchecked-remainder-int depth (unchecked-multiply 2 (unchecked-subtract range 1)))))

(defn severity [layers]
  (reduce (fn [sum [depth range]]
            (+ sum (if (caught? depth range) (* depth range) 0))) 0 layers))

(defn min-delay [layers]
  (first
   (sequence
    (reduce (fn [f [^long depth ^long range]]
              (comp f (remove (fn [^long delay] (caught? (unchecked-add depth delay) range)))))
            identity layers)
    (clojure.core/range))))

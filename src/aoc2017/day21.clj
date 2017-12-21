(ns aoc2017.day21 (:require [clojure.string :as s]))
(set! *unchecked-math* true)

(def ^:const start-pattern ".#...####")

(defn slurp-from-stdin []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       (map #(rest (re-matches #"(.*) => (.*)" %)))
       (reduce (fn [h [k v]]
                 (let [k-rows (s/split k #"/")
                       k-reversed-rows (map s/reverse k-rows)
                       k-rotated (apply map str k-rows)
                       k-reversed-rotated (map s/reverse k-rotated)
                       v (s/replace v #"/" "")]
                   ;; Generate all flips and rotations of pattern
                   (assoc h
                          (s/join k-rows) v
                          (s/join (reverse k-rows)) v
                          (s/join k-reversed-rows) v
                          (s/join (reverse k-reversed-rows)) v
                          (s/join k-rotated) v
                          (s/join (reverse k-rotated)) v
                          (s/join k-reversed-rotated) v
                          (s/join (reverse k-reversed-rotated)) v
                          )))
               {})))

(defn get-subsquare [pattern x y s si]
  (let [ul (+ (* x si) (* y s si))]
    (s/join
     (for [yi (range si) xi (range si)]
       (get pattern (+ ul xi (unchecked-multiply yi s)))))))

(defn transform-to-pattern [patterns]
  {:pre [(first patterns)]}
  (let [si (count (first patterns))
        s (count patterns)
        siq (int (Math/sqrt si))
        sq (int (Math/sqrt s))
        pattern (char-array (* s si))]
    (doseq [[i sub-pattern] (map-indexed list patterns)]
      (let [x (unchecked-remainder-int i sq)
            y (unchecked-divide-int i sq)
            sub-pattern (char-array sub-pattern)]
        (doseq [^long yi (range siq)
                ^long xi (range siq)]
          (aset pattern
                (+ (* x siq) xi
                   (* y si sq)
                   (* sq siq yi))
                (aget sub-pattern (+ xi (* yi siq)))))))
    (s/join pattern)))

(defn find-matching-patterns [pattern rules ^long size ^long subsize]
  (for [y (range (/ size subsize))
        x (range (/ size subsize))]
    (rules (get-subsquare pattern x y size subsize))))

(defn transform-pattern [rules generations]
  (loop [pattern start-pattern
         size (int (Math/sqrt (count start-pattern)))
         generation 0]
    (cond
      (= generation generations) (count (re-seq #"#" pattern))
      (even? size)
      (recur
       (transform-to-pattern (find-matching-patterns pattern rules size 2))
       (+ (unchecked-int size) (unchecked-divide-int size 2))
       (inc generation))
      (= 0 (mod size 3))
      (recur
       (transform-to-pattern (find-matching-patterns pattern rules size 3))
       (+ (unchecked-int size) (unchecked-divide-int size 3))
       (inc generation)))))

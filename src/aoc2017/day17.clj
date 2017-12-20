(ns aoc2017.day17)

(defn spin [^long insertions ^long steps]
  (let [a (int-array (inc insertions))]
    (loop [i 1                                 ; Number of insertions
           p 0                                 ; Current position
           s (unchecked-remainder-int steps i) ; Steps to go
           ]
      (if (> s 0)
        (recur i (aget a p) (unchecked-dec s))
          (let [ni (unchecked-inc i)]
            (aset a i (aget a p))
            (aset a p i)
            (if (= i insertions)
              (vec a)
              (recur ni i (unchecked-remainder-int steps ni))))))))

(defn next-value [insertions steps]
  (get (spin insertions steps) insertions))

(defn next-value-after-0 [insertions steps]
  (get (spin insertions steps) 0))

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

(defn spin-0 [insertions steps]
  (loop [i 1                            ; Number of insertions
         p 0                            ; Current position
         a 0                            ; Last value after 0
         ]
    (if (> i insertions)
      a
      (if (< (+ p (* steps)) i)
        (let [leaps (unchecked-divide-int (- (dec  i) p) steps)]
          (recur (+ i leaps) (long (+ p leaps (* leaps steps))) a))
        (let [new-p (unchecked-remainder-int (+ p steps) i)]
          (recur (inc i) (inc new-p) (if (= new-p 0) i a)))))))

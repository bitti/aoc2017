(ns aoc2017.day24)

(defn slurp-from-stdin []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       (map (fn [line]
              (let [[t1 t2] (clojure.string/split line #"/")]
                [(Integer/valueOf t1) (Integer/valueOf t2)])))
       (reduce (fn [h [t1 t2]]
                 (assoc h
                        t1 (conj (h t1 #{}) t2)
                        t2 (conj (h t2 #{}) t1)))
               {})))

(defn find-strongest-bridge [input strength option]
  (apply max
         (+ strength option)
         (map (fn [next-option]
                (+ option (find-strongest-bridge
                           (assoc input
                                  option (disj (input option) next-option)
                                  next-option (disj (input next-option) option))
                           (+ strength option)
                           next-option)))
              (input option))))

(defn find-strongest-longest-bridge [input strength length option]
  (reduce (fn [[maxstrengh maxlength] [s l]]
            (cond
              (> l maxlength) [s l]
              (and (= l maxlength) (> s maxstrengh)) [s l]
              :else [maxstrengh maxlength]))
          [strength length]
          (map (fn [next-option]
                  (let [[m l]
                        (find-strongest-longest-bridge
                         (assoc input
                                option (disj (input option) next-option)
                                next-option (disj (input next-option) option))
                         (+ strength option)
                         (inc length)
                         next-option)]
                    [(+ next-option m) l]))
               (input option))))

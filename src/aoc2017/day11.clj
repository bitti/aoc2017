(ns aoc2017.day11)

(def moves
  {"s"  [1 -1]
   "sw" [0 -1]
   "nw" [-1 0]
   "n"  [-1 1]
   "ne" [0 1]
   "se" [1 0]})

(defn distance [[x y]]
  (if (> 0 (* x y))
    (+ (min (Math/abs x) (Math/abs y)) (Math/abs (+ x y)))
    (+ (Math/abs x) (Math/abs y))))

(defn distance-from-stdin []
  (->> *in*
       slurp
       clojure.string/trim-newline
       (#(clojure.string/split % #","))
       (reduce (fn [[x y] d]
                 (let [[xd yd] (moves d)]
                   [(+ x xd) (+ y yd)]))
               [0 0])
       distance))

(defn max-distance-from-stdin []
  (->> *in*
       slurp
       clojure.string/trim-newline
       (#(clojure.string/split % #","))
       (reduce (fn [[x y max-dist] d]
                 (let [[xd yd] (moves d)
                       nx (+ x xd)
                       ny (+ y yd)]
                   [nx ny (max max-dist (distance [nx ny]))]))
               [0 0 0])
       last))

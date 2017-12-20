(ns aoc2017.day20)

(defn slurp-swarm-from-stdin []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       (map (fn [line]
              (->> line
               (re-matches #"p=<(.+),(.+),(.+)>, v=<(.+),(.+),(.+)>, a=<(.+),(.+),(.+)>")
               rest
               (map #(Integer/valueOf (clojure.string/trim %)))
               (partition 3))))))

(defn nearest-to-zero []
  ;; We only need to figure out the lowest accelerating particle
  ;; (but if there's more than one it could get complicated)
  (->>
   (slurp-swarm-from-stdin)
   (map-indexed (fn [i [_ _ a]] [i (reduce #(+ %1 (Math/abs %2)) 0 a)]))
   (reduce (fn [[imin min] [i a]]
             (if (< a min)
               [i a]
               [imin min]))
           [-1 Integer/MAX_VALUE])
   first))

(defn remove-colliding-particles []
  (loop [particles (set (slurp-swarm-from-stdin))
         step 0]
    (if (> step 1000)                  ; Just an educated guess...
      (count particles)
      (recur (->>
              particles
              ;; Remove colliding particles
              (reduce (fn [[particles collisions] [p v a]]
                        (cond
                          (collisions p) [particles collisions]
                          (particles p) [(dissoc particles p) (conj collisions p)]
                          :else [(assoc particles p [v a]) collisions]))
                      [{} #{}])
              first
              ;; Update velocities and positions
              (map (fn [[p [v a]]]
                     (let [new-v (map + v a)
                           new-p (map + new-v p)]
                       [new-p new-v a]))))
             (inc step)))))

(ns aoc2017.day12
  (require [clojure.set :as set]))

(defn parse-connections []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       doall
       (map (fn [line] (clojure.string/split line #" <-> ")))
       (reduce (fn [connections [node pipe-str]]
                 (let [pipes (map #(Integer/valueOf %) (clojure.string/split pipe-str #", *"))]
                   (assoc connections (Integer/valueOf node) pipes)))
               {})))

(defn transitive-closure [group new-members connections]
  (let [new-members        
        (set/difference (set (flatten (map connections new-members))) group)]
    (if (empty? new-members)
      group
      (recur (set/union group new-members) new-members connections))))

(defn count-group-size [rep]
  (count (transitive-closure #{} #{rep} (parse-connections))))

(defn count-groups []
  (let [connections (parse-connections)]
    (loop [rest (set (keys connections))
           count 0]
      (if (empty? rest)
        count
        (recur (set/difference rest (transitive-closure #{} #{(first rest)} connections))
               (inc count))))))

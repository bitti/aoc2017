(ns aoc2017.core
  (:use [aoc2017.day1])
  (:gen-class))

(defn alert!
  ([message] (alert! message 1))
  ([message status] (binding [*out* *err*] (println message) (System/exit status))))

(defn -main
  "Give a day and I'll process both solutions for that day by the
  given standard input"
  [& args]
  (cond
    (= (count args) 0) (alert! "Please provide the day number")
    (not (<= 1 (-> args first Integer/valueOf) 25)) (alert! "Please provide a number between 1 and 25")
    :else
    (let [input (slurp *in*)]
      (case (first args)
        "1" (doall (map println [
                 "Solution for first part"
                  (with-in-str input (add-next-digit-if-equal))
                  "Solution for second part"
                  (with-in-str input (add-halfway-digit-if-equal))]))
        (println "Day" (first args) "not implemented yet")))))

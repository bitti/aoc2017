(ns aoc2017.day25)

(def ^:const replacements
  {#"Begin in state (.)." "(loop [ { :keys [steps t p state] } { :t (transient {}) :p 0 :state :%s"
   #"Perform.*after (.*) steps." ":steps %s}]
    (if (= steps 0) (reduce + (vals (persistent! t)))
    (recur (case state nil ("
   #"" ")"
   #"In state (.):" ":%s (case (long (t p 0))"
   #"If.*is (.):" "%s {"
   #"- Write.* (.)." ":t (assoc! t p %s) :steps (dec ^long steps)"
   #"- Move.*right." ":p (inc ^long p)"
   #"- Move.*left." ":p (dec ^long p)"
   #"- Continue.*state (.)." ":state :%s}"})

(defn slurp-from-stdin []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       (map
        (fn [line]
          (reduce (fn [_ [match repl]]
                    (let [matches (re-matches match (clojure.string/trim line))]
                      (if matches
                        (reduced (if (vector? matches) (format repl (last matches)) repl)))))
                  nil
                  replacements)))
       (clojure.string/join "\n")
       (#(str % ")))))"))
       load-string))

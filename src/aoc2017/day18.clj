(ns aoc2017.day18)

;; snd X | plays a sound with a frequency equal to the value of X.
;; set X | Y sets register X to the value of Y.
;; add X | Y increases register X by the value of Y.
;; mul X | Y sets register X to the result of multiplying the value
;;       | contained in register X by the value of Y.
;; mod X | Y sets register X to the remainder of dividing the value
;;       | contained in register X by the value of Y (that is, it sets X to
;;       | the result of X modulo Y).
;; rcv X | recovers the frequency of the last sound played, but only
;;       | when the value of X is not zero. (If it is zero, the command does
;;       | nothing.)
;; jgz X | Y jumps with an offset of the value of Y, but only if the
;;       | value of X is greater than zero. (An offset of 2 skips the next
;;       | instruction, an offset of -1 jumps to the previous instruction, and
;;       | so on.)

(defn instruction [op o1 o2]
  (let [attr (case (eval op)
               "snd" (fn [env v1 v2] ((:send-callback env) v1))
               "set" (fn [env v1 v2] [o1 v2])
               "add" (fn [env v1 v2] [o1 (+ v1 v2)])
               "mul" (fn [env v1 v2] [o1 (* v1 v2)])
               "mod" (fn [env v1 v2] [o1 (mod v1 v2)])
               "rcv" (fn [env v1 v2] ((:receive-callback env) o1 v1))
               "jgz" (fn [env v1 v2] (if (> v1 0) [:pc (+ (:pc env) v2)] [])))]
    (fn [env]
      (let [v1 (if (re-matches #"[a-z]" o1) (env o1 0) (Integer/valueOf o1))
            v2 (when o2
                 (if (re-matches #"[a-z]" o2) (env o2 0) (Integer/valueOf o2)))]
        (apply assoc env :pc (inc (:pc env)) (attr env v1 v2))))))

(defn slurp-ops-from-stdin []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       (map (fn [line]
              (let [[op o1 o2] (clojure.string/split line #" ")]
                (instruction op o1 o2))))
       vec))

(defn exec-till-interrupt [instructions env]
  (loop [env env]
    (if (:int env)
      (assoc env :int nil)
      (recur ((instructions (:pc env)) env)))))

(defn get-last-send-after-receive []
  (:send (exec-till-interrupt
          (slurp-ops-from-stdin)
          {:pc 0
           :send-callback (fn [v] [:send v])
           :receive-callback (fn [o v] (if (= 0 v) [] [:int true]))})))

(defn exec-till-termination []
  (let [instructions (slurp-ops-from-stdin)]
    (loop [env1 {:pc 0
                 "p" 0
                 :send-callback (fn [v] [:send v :int true])
                 :receive-callback (fn [o v] [:receive o :int true])}
           env2 (assoc env1 "p" 1)
           send1count 0
           send1 clojure.lang.PersistentQueue/EMPTY
           send2 clojure.lang.PersistentQueue/EMPTY
           ]
      (cond
        (:send env1) (recur (assoc env1 :send nil)
                            env2
                            send1count
                            (conj send1 (:send env1))
                            send2)
        (:send env2) (recur env1
                            (assoc env2 :send nil)
                            (inc send1count)
                            send1
                            (conj send2 (:send env2)))
        (and (:receive env1)
             (:receive env2)
             (empty? send1)
             (empty? send2)) send1count
        (:receive env1) (if (empty? send2)
                          (recur env1
                                 ((instructions (:pc env2)) env2)
                                 send1count send1 send2)
                          (recur (assoc env1 :receive nil (:receive env1) (peek send2))
                                 env2
                                 send1count
                                 send1
                                 (pop send2)))
        (:receive env2) (if (empty? send1)
                          (recur ((instructions (:pc env1)) env1)
                                 env2
                                 send1count send1 send2)
                          (recur env1
                                 (assoc env2 :receive nil (:receive env2) (peek send1))
                                 send1count
                                 (pop send1)
                                 send2))
        :else (recur (exec-till-interrupt instructions env1)
                     (exec-till-interrupt instructions env2)
                     send1count send1 send2)))))

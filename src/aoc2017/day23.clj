(ns aoc2017.day23 (:require [com.hypirion.primes :refer [take-below]]))

(defn instruction [op o1 o2]
  (let [attr (case (eval op)
               "set" (fn [env v1 v2] [o1 v2])
               "sub" (fn [env v1 v2] [o1 (- v1 v2)])
               "mul" (fn [env v1 v2] [:mul true o1 (* v1 v2)])
               "jnz" (fn [env v1 v2] (if (not= v1 0) [:pc (+ (:pc env) v2)] [])))]
    (fn [env]
      (let [v1 (if (re-matches #"[a-z]" o1) (env o1 0) (Integer/valueOf o1))
            v2 (when o2
                 (if (re-matches #"[a-z]" o2) (env o2 0) (Integer/valueOf o2)))]
        (apply assoc env :pc (inc (:pc env)) (attr env v1 v2))))))

(defn slurp-from-stdin []
  (->> *in*
       java.io.BufferedReader.
       line-seq
       (map (fn [line]
              (let [[op o1 o2] (clojure.string/split line #" ")]
                (instruction op o1 o2))))
       vec))

(defn exec-count-mul [instructions env]
  (loop [env env
         mul 0]
    (cond
      (>= (:pc env) (count instructions)) mul
      (:mul env) (recur (dissoc env :mul) (inc mul))
      :else (recur ((instructions (:pc env)) env) mul))))

;; This is counting non-prime numbers between register b and c in steps of 17
;;
;;         set b 67
;;         set c b
;;         jnz a 2
;;         jnz 1 loop3  ; Ignored for a = 1, so b and c are much higher
;;         mul b 100
;;         sub b -100000
;;         set c b
;;         sub c -17000 ; b = 106700, c = 123700
;; loop3:  set f 1
;;         set d 2
;; loop2:  set e 2
;; loop1:  set g d
;;         mul g e
;;         sub g b
;;         jnz g 2 
;;         set f 0      ; Only happens if d * e and d, e > 1
;;         sub e -1     ; e++
;;         set g e
;;         sub g b
;;         jnz g loop1  ; e = b? => d++
;;         sub d -1
;;         set g d  
;;         sub g b
;;         jnz g loop2  ; d = b? 
;;         jnz f 2      ; d = e = b, f = 0?
;;         sub h -1     ; f == 0 => we found a factorization for b
;;         set g b
;;         sub g c
;;         jnz g 2      ; b = c? => exit
;;         jnz 1 3
;;         sub b -17    ; b += 17
;;         jnz 1 loop3

(defn calculate-h [instructions env]
  (let [{b "b" c "c"}
        (loop [env env
               mul 0]
          (cond
            ;; At the second mul instruction we have the initial values for b and c
            (= mul 2) env
            (:mul env) (recur (dissoc env :mul) (inc mul))
            :else (recur ((instructions (:pc env)) env) mul)))]
    (->> c
         take-below
         (filter #(and (> % b) (= 0 (mod (- % b) 17))))
         count
         (- (/ (- c b) 17))

         ;; Need to add 1 because we go to c inclusive
         inc)))


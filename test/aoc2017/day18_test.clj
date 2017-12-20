(ns aoc2017.day18-test
  (:use aoc2017.test-helper aoc2017.day18)
  (:require [clojure.test :refer :all]))

(deftest test-day18-part1
  (testing "example"
    (is (= (with-in-str "set a 1
add a 2
mul a a
mod a 5
snd a
set a 0
rcv a
jgz a -1
set a 1
jgz a -2" (get-last-send-after-receive)) 4)))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day18-input.txt")]
     (is (= (get-last-send-after-receive) 1187)))))

(deftest test-day18-part2
  (testing "example"
    (is (= (with-in-str "snd 1
snd 2
snd p
rcv a
rcv b
rcv c
rcv d" (exec-till-termination)) 3))
    (testing "puzzle input"
      (binding [*in* (resource-as-stdin "day18-input.txt")]
        (is (= (exec-till-termination) 5969))))))

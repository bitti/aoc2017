(ns aoc2017.day25-test
  (:use aoc2017.test-helper aoc2017.day25)
  (:require [clojure.test :refer :all]))

(deftest test-day25
  (testing "example"
    (is (= (with-in-str "Begin in state A.
Perform a diagnostic checksum after 6 steps.

In state A:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state B.
  If the current value is 1:
    - Write the value 0.
    - Move one slot to the left.
    - Continue with state B.

In state B:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the left.
    - Continue with state A.
  If the current value is 1:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state A."
(slurp-from-stdin)) 3))
  (testing "puzzle input"
    (is (= (binding [*in* (resource-as-stdin "day25-input.txt")] (slurp-from-stdin)) 4230)))))

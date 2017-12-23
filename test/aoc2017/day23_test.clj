(ns aoc2017.day23-test
  (:use aoc2017.test-helper aoc2017.day23)
  (:require [clojure.test :refer :all]))

(deftest test-day21-part1
  (testing "puzzle input"
    (is (= (binding [*in* (resource-as-stdin "day23-input.txt")]
             (exec-count-mul (slurp-from-stdin) {:pc 0})) 4225))))

(deftest test-day21-part2
  (testing "puzzle input"
    (is (= (binding [*in* (resource-as-stdin "day23-input.txt")]
             (calculate-h (slurp-from-stdin) {:pc 0 "a" 1})) 905))))

(ns aoc2017.day5-test
  (:use aoc2017.test-helper aoc2017.day5)
  (:require [clojure.test :refer :all]))

(deftest test-day2-part1
  (testing "example"
    (is (= (steps-to-jump-out 0 0 (int-array [0 3 0 1 -3])) 5)))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day5-input.txt")]
      (is (= (steps-to-jump-out-from-stdin) 372139)))))

(deftest test-day2-part2
  (testing "example"
    (is (= (strange-steps-to-jump-out 0 0 (int-array [0 3 0 1 -3])) 10)))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day5-input.txt")]
      (with-redefs [steps-to-jump-out strange-steps-to-jump-out]
        (is (= (steps-to-jump-out-from-stdin) 29629538))))))

(ns aoc2017.day17-test
  (:use aoc2017.test-helper aoc2017.day17)
  (:require [clojure.test :refer :all]))

(deftest test-day17-part1
  (testing "example"
    (is (= (next-value 2017 3) 638)))
  (testing "puzzle input"
    (is (= (next-value 2017 345) 866))))

(deftest test-day17-part2
  (testing "puzzle input"
    (is (= (next-value-after-0 50000000 345) 11995607))))

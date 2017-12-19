(ns aoc2017.day15-test
  (:use aoc2017.test-helper aoc2017.day15)
  (:require [clojure.test :refer :all]))

(deftest test-day15-part1
  (testing "example"
    (is (= (judge 65 8921) 588)))
  (testing "puzzle input"
    (is (= (judge 277 349) 592))))
  
(deftest test-day15-part2
  (testing "example"
    (is (= (generic-judge 5000000 (picky-generator 65 16807 3) (picky-generator 8921 48271 7)) 309)))
  (testing "puzzle input"
    (is (= (generic-judge 5000000 (picky-generator 277 16807 3) (picky-generator 349 48271 7)) 320))))


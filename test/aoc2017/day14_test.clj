(ns aoc2017.day14-test
  (:use aoc2017.test-helper aoc2017.day14)
  (:require [clojure.test :refer :all]))

(deftest test-day14-part1
  (testing "example"
    (is (= (used-squares "flqrgnkx") 8108)))
  (testing "puzzle input"
    (is (= (used-squares "hwlqcszp") 8304))))

(deftest test-day14-part1
  (testing "example"
    (is (= (used-groups "flqrgnkx") 1242)))
  (testing "puzzle input"
    (is (= (used-groups "hwlqcszp") 1018))))

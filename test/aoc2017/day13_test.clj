(ns aoc2017.day13-test
  (:use aoc2017.test-helper aoc2017.day13)
  (:require [clojure.test :refer :all]))

(deftest test-day13-part1
  (testing "example"
    (is (= (severity
            {0 3
             1 2
             4 4
             6 4}) 24)))
  (testing "puzzle input"
    (is (= (severity (binding [*in* (resource-as-stdin "day13-input.txt")]
                         (parse-layer-ranges))) 3184))))

(deftest test-day13-part2
  (testing "example"
    (is (= (min-delay
            {0 3
             1 2
             4 4
             6 4}) 10)))
  (testing "puzzle input"
    (is (= (min-delay (binding [*in* (resource-as-stdin "day13-input.txt")]
                        (parse-layer-ranges))) 3878062))))

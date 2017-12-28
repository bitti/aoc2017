(ns aoc2017.day11-test
  (:use aoc2017.test-helper aoc2017.day11)
  (:require [clojure.test :refer :all]))

(deftest test-day11-part1
  (testing "examples"
    (is (= (with-in-str "ne,ne,ne" (distance-from-stdin))) 3)
    (is (= (with-in-str "ne,ne,sw,sw" (distance-from-stdin)) 0))
    (is (= (with-in-str "ne,ne,s,s" (distance-from-stdin)) 2))
    (is (= (with-in-str "se,sw,se,sw,sw" (distance-from-stdin)) 3)))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day11-input.txt")]
      (is (= (distance-from-stdin) 761)))))

(deftest test-day11-part2
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day11-input.txt")]
      (is (= (max-distance-from-stdin) 1542)))))

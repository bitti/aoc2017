(ns aoc2017.day12-test
  (:use aoc2017.test-helper aoc2017.day12)
  (:require [clojure.test :refer :all]))

(deftest test-day12-part1
  (testing "example"
    (is (= (with-in-str "0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5" (count-group-size 0)) 6)))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day12-input.txt")]
      (is (= (count-group-size 0)) 169))))

(deftest test-day12-part1
  (testing "example"
    (is (= (with-in-str "0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5" (count-groups)) 2))
    )
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day12-input.txt")]
      (is (= (count-groups) 179)))))

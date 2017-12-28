(ns aoc2017.day24-test
  (:use aoc2017.test-helper aoc2017.day24)
  (:require [clojure.test :refer :all]))

(def ^:const example "0/2
2/2
2/3
3/4
3/5
0/1
10/1
9/10")

(deftest test-day24-part1
  (testing "example"
    (is (= (with-in-str example
             (find-strongest-bridge (slurp-from-stdin) 0 0)) 31)))
  (testing "puzzle input"
    (is (= (binding [*in* (resource-as-stdin "day24-input.txt")]
             (find-strongest-bridge (slurp-from-stdin) 0 0)) 1868))))

(deftest test-day24-par2
  (testing "example"
    (is (= (with-in-str example
             (find-strongest-longest-bridge (slurp-from-stdin) 0 0 0)) [19 4])))
  (testing "puzzle input"
    (is (= (binding [*in* (resource-as-stdin "day24-input.txt")]
             (first (find-strongest-longest-bridge (slurp-from-stdin) 0 0 0))) 1841))))

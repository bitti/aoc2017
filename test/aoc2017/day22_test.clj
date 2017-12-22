(ns aoc2017.day22-test
  (:use aoc2017.test-helper aoc2017.day22)
  (:require [clojure.test :refer :all]))

(def ^:const example "..#
#..
...")

(deftest test-day21-part1
  (testing "example"
    (is (= (with-in-str example (advance (slurp-from-stdin) 10000)) 5587)))
  (testing "puzzle input"
    (is (= (binding [*in* (resource-as-stdin "day22-input.txt")]
             (advance (slurp-from-stdin) 10000)) 5305))))

(deftest test-day21-part2
  (testing "example"
    (is (= (with-in-str example (evolved-virus (slurp-from-stdin) 100)) 26))
    (is (= (with-in-str example (evolved-virus (slurp-from-stdin) 10000000)) 2511944)))
  (testing "puzzle input"
    (is (= (binding [*in* (resource-as-stdin "day22-input.txt")]
             (evolved-virus (slurp-from-stdin) 10000000)) 2511424))))

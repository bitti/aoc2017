(ns aoc2017.day2-test
  (:use aoc2017.test-helper aoc2017.day2)
  (:require [clojure.test :refer :all]))

(deftest test-day2-part1
  (testing "example"
    (is (= (with-in-str "5\t1\t9\t5
7\t5\t3
2\t4\t6\t8" (spreadsheet-chksum)) 18)))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day2-input.txt")]
      (is (= (spreadsheet-chksum) 41919)))))

(deftest test-day2-part2
  (testing "example"
    (is (= (with-in-str "5\t9\t2\t8
9\t4\t7\t3
3\t8\t6\t5" (spreadsheet-evenly-divisible-checksum)) 9)))

  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day2-input.txt")]
      (is (= (spreadsheet-evenly-divisible-checksum) 303)))))

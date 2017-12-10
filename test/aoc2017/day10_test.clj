(ns aoc2017.day10-test
  (:use aoc2017.test-helper aoc2017.day10)
  (:require [clojure.test :refer :all]))

(deftest test-day9-part1
  (testing "examples"
    (is (= (hash-string-cycle 5 [3, 4, 1, 5]) [3 4 2 1 0])))
  (testing "puzzle input"
    (let [[first second & _ ]
          (hash-string-cycle 256 [192,69,168,160,78,1,166,28,0,83,198,2,254,255,41,12])]
      (is (= (* first second) 48705)))))

(deftest test-day9-part2
  (testing "examples"
    (is (= (full-hash-cycle 256 "") "a2582a3a0e66e6e86e3812dcb672a272"))
    (is (= (full-hash-cycle 256 "AoC 2017") "33efeb34ea91902bb2f59c9920caa6cd"))
    (is (= (full-hash-cycle 256 "1,2,3") "3efbe78a8d82f29979031a4aa0b16a9d"))
    (is (= (full-hash-cycle 256 "1,2,4") "63960835bcdc130f0b66d7ff4f6a5a8e")))
  (testing "puzzle input"
    (is (= (full-hash-cycle 256 "192,69,168,160,78,1,166,28,0,83,198,2,254,255,41,12")
           "1c46642b6f2bc21db2a2149d0aeeae5d"))))

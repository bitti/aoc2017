(ns aoc2017.day9-test
  (:use aoc2017.test-helper aoc2017.day9)
  (:require [clojure.test :refer :all]))

(deftest test-day9-part1
  (testing "examples"
    (is (= (with-in-str "{}" (score-groups-from-stdin)) 1))
    (is (= (with-in-str "{{{}}}" (score-groups-from-stdin)) 6))
    (is (= (with-in-str "{{{},{},{{}}}}" (score-groups-from-stdin)) 16))
    (is (= (with-in-str "{{<!!>},{<!!>},{<!!>},{<!!>}}" (score-groups-from-stdin)) 9))
    (is (= (with-in-str "{<{},{},{{}}>}" (score-groups-from-stdin)) 1))
    (is (= (with-in-str "{<a>,<a>,<a>,<a>}" (score-groups-from-stdin)) 1))
    (is (= (with-in-str "{{<ab>},{<ab>},{<ab>},{<ab>}}" (score-groups-from-stdin)) 9))
    (is (= (with-in-str "{{<!!>},{<!!>},{<!!>},{<!!>}}" (score-groups-from-stdin)) 9))
    (is (= (with-in-str "{{<a!>},{<a!>},{<a!>},{<ab>}" (score-groups-from-stdin)) 3)))

  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day9-input.txt")]
      (is (= (score-groups-from-stdin) 16869)))))

(deftest test-day9-part2
  (testing "examples"
    (is (= (with-in-str "<>" (count-garbage-from-stdin)) 0))
    (is (= (with-in-str "<random characters>" (count-garbage-from-stdin)) 17))
    (is (= (with-in-str "<<<<>" (count-garbage-from-stdin)) 3))
    (is (= (with-in-str "<{!>}>" (count-garbage-from-stdin)) 2))
    (is (= (with-in-str "<!!>" (count-garbage-from-stdin)) 0))
    (is (= (with-in-str "<!!!>>" (count-garbage-from-stdin)) 0))
    (is (= (with-in-str "<{o\"i!a,<{i<a>" (count-garbage-from-stdin)) 10)))

  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day9-input.txt")]
      (is (= (count-garbage-from-stdin) 7284)))))

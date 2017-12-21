(ns aoc2017.day21-test
  (:use aoc2017.test-helper aoc2017.day21)
  (:require [clojure.test :refer :all]))

(def ^:const gen2square "##.#.....")

(deftest test-day21-part1
  (testing "subsquares"
    (is (= (get-subsquare "#..#........#..#" 0 1 4 2) "..#."))
    (is (= (transform-to-pattern [gen2square gen2square gen2square gen2square])
           "##.##.#..#........##.##.#..#........")))
  (testing "example"
    (with-in-str "../.# => ##./#../...
.#./..#/### => #..#/..../..../#..#"
      (is (= (transform-pattern (slurp-from-stdin) 2) 12))))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day21-input.txt")]
      (is (= (transform-pattern (slurp-from-stdin) 5) 136)))))

(deftest test-day21-part2
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day21-input.txt")]
      (is (= (transform-pattern (slurp-from-stdin) 18) 1911767)))))

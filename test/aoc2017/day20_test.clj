(ns aoc2017.day20-test
  (:use aoc2017.test-helper aoc2017.day20)
  (:require [clojure.test :refer :all]))

(deftest test-day20-part1
  (testing "example"
    (is (= (with-in-str "p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>
p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>" (nearest-to-zero)) 0)))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day20-input.txt")]
      (is (= (nearest-to-zero) 376)))))

(deftest test-day20-part2
  (testing "example"
    (is (= (with-in-str "p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>
p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>
p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>
p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>
" (remove-colliding-particles)) 1)))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day20-input.txt")]
     (is (= (remove-colliding-particles) 574)))))

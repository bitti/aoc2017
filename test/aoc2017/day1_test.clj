(ns aoc2017.day1-test
  (:use aoc2017.test-helper aoc2017.day1)
  (:require [clojure.test :refer :all]))

(deftest test-rotate
  (is (= (rotate 3 nil) '()))
  (is (= (rotate 27 '()) '()))
  (is (= (rotate 27 '(1)) '(1)))
  (is (= (rotate 1 '(1 2)) '(2 1)))
  (is (= (rotate 0 '(a b c d e f)) '(a b c d e f)))
  (is (= (rotate 6 '(a b c d e f)) '(a b c d e f)))
  (is (= (rotate 4 '(a b c d e f)) '(e f a b c d)))
  (is (= (rotate 1 '(a b c d e f)) '(b c d e f a)))
  (is (= (rotate 3 [1 2 3 4 5 6 7 8 9]) '(4 5 6 7 8 9 1 2 3))))

(deftest test-day1-part1
  (testing "examples"
    (is (= (with-in-str "1122" (add-next-digit-if-equal)) 3))
    (is (= (with-in-str "1111" (add-next-digit-if-equal)) 4))
    (is (= (with-in-str "1234" (add-next-digit-if-equal)) 0))
    (is (= (with-in-str "91212129" (add-next-digit-if-equal)) 9)))
  
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day1-input.txt")]
      (is (= (add-next-digit-if-equal) 1158)))))

(deftest test-day1-part2
  (testing "examples"
    (is (= (with-in-str "1212" (add-halfway-digit-if-equal)) 6))
    (is (= (with-in-str "1221" (add-halfway-digit-if-equal)) 0))
    (is (= (with-in-str "123425" (add-halfway-digit-if-equal)) 4))
    (is (= (with-in-str "123123" (add-halfway-digit-if-equal)) 12))
    (is (= (with-in-str "12131415" (add-halfway-digit-if-equal)) 4)))
  
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day1-input.txt")]
      (is (= (add-halfway-digit-if-equal) 1132)))))

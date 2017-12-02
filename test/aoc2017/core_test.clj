(ns aoc2017.core-test
  (:require [clojure.test :refer :all]
            [aoc2017.core :refer :all]))

(deftest test-main
  (testing "-main outputs help text"
    (with-redefs [alert! identity]
      (is (re-matches #"(?s)Please provide the day number.*" (-main)))
      (is (re-matches #"(?s)Please provide a number between 1 and 25.*" (-main 0)))
      (is (re-matches #"(?s)Please provide a number between 1 and 25.*" (-main 26)))))
  (testing "-main "))

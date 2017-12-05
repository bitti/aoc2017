(ns aoc2017.day4-test
  (:use aoc2017.test-helper aoc2017.day4)
  (:require [clojure.test :refer :all]))

(deftest test-day2-part1
  (testing "examples"
    (is (passphrase? '("aa" "bb" "cc" "dd" "ee")))
    (is (not (passphrase? '("aa" "bb" "cc" "dd" "aa"))))
    (is (passphrase? '("aa" "bb" "cc" "dd" "aaa"))))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day4-input.txt")]
      (is (= (count-passphrases-from-stdin) 386)))))

(deftest test-day2-part1
  (testing "examples"
    (are [phrase] (additional-policy-passphrase? phrase)
      '("abcde" "fghij")
      '("a" "ab" "abc" "abd" "abf" "abj")
      '("iiii" "oiii" "ooii" "oooi" "oooo"))
    (are [phrase] (not (additional-policy-passphrase? phrase))
      '("abcde" "xyz" "ecdab")
      '("oiii" "ioii" "iioi" "iiio"))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day4-input.txt")]
      (with-redefs [passphrase? additional-policy-passphrase?]
        (is (= (count-passphrases-from-stdin) 208)))))))

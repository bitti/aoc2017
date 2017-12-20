(ns aoc2017.day19-test
  (:use aoc2017.test-helper aoc2017.day19)
  (:require [clojure.test :refer :all]))

(deftest test-day19
  (testing "example"
    (is (= (with-in-str "     |          
     |  +--+    
     A  |  C    
 F---|----E|--+ 
     |  |  |  D 
     +B-+  +--+ 
                " (record-chars-on-path (slurp-map-from-stdin))) ["ABCDEF" 38])))
  (testing "puzzle input"
    (binding [*in* (resource-as-stdin "day19-input.txt")]
     (is (= (record-chars-on-path (slurp-map-from-stdin)) ["LIWQYKMRP" 16764])))))

(ns aoc2017.test-helper
  (:require [clojure.java.io :as io]))

(defn resource-as-stdin
  "Provide the given resource as a BufferedReader which can be used as stdin"
  [filename]
  (-> filename io/resource io/file java.io.FileReader. java.io.BufferedReader.))

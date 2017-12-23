(defproject aoc2017 "0.1.0-SNAPSHOT"
  :description "My attempts for Advent of Code 2017"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.hypirion/primes "0.2.2"]]
  :main ^:skip-aot aoc2017.core
  :target-path "target/%s"
  :profiles {:dev {:resource-paths ["test/resources"]}
             :uberjar {:aot :all}})

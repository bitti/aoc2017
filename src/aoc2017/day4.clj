(ns aoc2017.day4)

(defn passphrase?
  "Determines if given sequence is a passphrase when defined as
  'contains no duplicate words'"
  [seq]
  (= (count (set seq)) (count seq)))

(defn count-passphrases-from-stdin []
  (->>
   *in*
   line-seq
   (filter #(passphrase? (clojure.string/split % #" ")))
   count))

(defn additional-policy-passphrase?
  "Determines if given sequence is a passphrase when defined as
  'no two words are anagrams of each other'"
  [seq]
  (= (count (set (map sort seq))) (count seq)))

(ns aoc2017.day1)

(defn rotate
  "Rotate sequence s by n elements to the left. n has to be 0 or
  positive and not greater than the length of s. The length of s is
  not checked by rotate in order to ensure a lazy evaluation."
  [n s]
  (concat (drop n s) (take n s)))

(defn add-next-digit-if-equal
  "Provide the sum of all digits which have the same successor. The
  input from stdin should be a single line which is interpreted as a
  circular list"
  []
  (let [digits (map #(- (byte %) (byte \0)) (read-line))
        equal-digits (map #(if (= %1 %2) %1 0) digits (rotate 1 digits))]
    (reduce #(+ %1 %2) equal-digits)))

(defn add-halfway-digit-if-equal []
  (let [digits (map #(- (byte %) (byte \0)) (read-line))
        equal-digits (map #(if (= %1 %2) %1 0) digits (rotate (/ (count digits) 2) digits))]
    (reduce #(+ %1 %2) equal-digits)))

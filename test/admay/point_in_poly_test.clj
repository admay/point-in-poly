(ns admay.point-in-poly-test
  (:require [clojure.test :refer :all]
            [admay.point-in-poly :refer :all]))

(deftest simple-polygons
  (testing "point inside of polygon is found"
    (is (= true (inside-polygon? [1 1] [[2 2] [2 -2] [-2 -2] [-2 2]]))))
  (testing "point outside of polygon is found"
    (is (= false (inside-polygon? [10 10] [[2 2] [2 -2] [-2 -2] [-2 2]])))))
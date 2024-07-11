(ns admay.point-in-poly-test
  (:require [clojure.test :refer [deftest is testing]]
            [admay.point-in-poly :refer [inside-polygon?]]))

(deftest simple-polygons
  (let [simple-polygon [[2 2] [2 -2] [-2 -2] [-2 2]]]
    (testing "point inside of polygon"
      (is (= true (inside-polygon? [1 1] simple-polygon))))
    (testing "point outside of polygon"
      (is (= false (inside-polygon? [10 10] simple-polygon))))
    (testing "point on boundary"
      (is (= false (inside-polygon? [2 2] simple-polygon))))))

(deftest complex-polygons
  (let [complex-polygon [[-5 0] [-4 3] [-3 0] [-2 3] [-1 0] [-2 -3] [-4 -3]]]
    (testing "point inside of polygon"
      (is (= true (inside-polygon? [-4 1] complex-polygon))))
    (testing "point outside of polygon"
      (is (= false (inside-polygon? [5 5] complex-polygon))))
    (testing "point on boundary"
      (is (= false (inside-polygon? [-2 3] complex-polygon))))))

(deftest triangle
  (let [triangle [[0 0] [-6 6] [6 6] [0 0]]]
    (testing "point inside of triangle"
      (is (= true (inside-polygon? [1 4] triangle))))

    (testing "point outside of triangle"
      (is (= false (inside-polygon? [-1 -1] triangle))))))

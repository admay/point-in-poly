(ns admay.point-in-poly)

(defn colinear?
  "Check to see if a value p is at or beyond the lower bound but
  does not exceed the upper bound."
  [p x y]
  (or (and (<= x p) (> y p))
      (and (> x p) (<= y p))))

(defn crosses?
  "Checks to see if a ray from point p will intersect an edge from point (x1,y1) to point (x2,y2)."
  [[px py] [[x1 y1] [x2 y2]]]
  (let [vt (/ (- py y1) (- y2 y1))]
    (< px (+ x1 (* vt (- x2 x1))))))

(defn num-crosses
  "Returns 1 for an intersection with a ray from point p to the edge and 0 for a lack thereof"
  [point edge]
  (let [[_ py] point
        [[_ y1] [_ y2]] edge]
    (if (colinear? py y1 y2)
      (if (crosses? point edge)
        1
        0)
      0)))

(defn count-crosses
  "Counts the number of times a ray from point p will intersect the edges of a polygon."
  [point polygon]
  (reduce + (for [n (range (dec (count polygon)))]
              (num-crosses point [(nth polygon n)
                                  (nth polygon (inc n))]))))

(defn inside-polygon?
  "Determines whether or not a point is in a polygon."
  [point polygon]
  (odd? (count-crosses point polygon)))
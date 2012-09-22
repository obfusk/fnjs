; File        : examples/misc.fnjs
; Date        : 2012-09-22
; Description : Testing ground.

(def PI+ 3.14159265)

(console.log (jobj foo- 99, "bar" -1 baz "!"))
(console.log (jary PI+ 42 37 "hi!"))
(console.log (if (jbop === 1 1) "foo" "bar"))
(console.log (let [ n+ 10, m (jbop * 3 3) ] (jbop + m n+ PI+)))

(def obj- (jobj
  foo (jary 1 (jobj a- (jobj d 42), b (jobj c- 37)))
  bar- (fn [a b- c] (jbop + PI+ a b- c)) ))

(let [ my-obj 0xDEADBEEF ] (console.log my-obj))

(console.log (.a- (jget obj- "foo" 1) d))
(console.log (. (jget obj- "foo" 1) b c-))

(console.log (.!bar- obj- 1 2 3))
(console.log (.! obj- bar- 4 5 6))

(jfor [ x- (jary 1 2 3), y- (jary "foo" "bar") ]
  (console.log "..." x- y-))

(defn foo* [x y+] (console.log "x=" x "y=" y+))

(foo* "hi" 1337)

(if-let [ z- (jbop * 1 10) ]  (console.log "z=" z-)
                              (console.log "false") )

(def f #(jbop - %2 %1))
(def g #(jbop + %2 %1))
(console.log (f 3 (g 11 2)))

(defn multi
  ([] "zero")
  ([x y] "two")
  ([x y & rest] (jary x y rest)) )

(console.log (multi 1 2) (multi) (multi 1 2 3 4)) ; (multi 1)

; --

(defn seq' [xs] (.length xs))
(defn first' [xs] (jget xs 0))
(defn rest' [xs] (.!slice xs 1))

(defn reduce' [f z xs]
  (loop [z z, xs xs]
    (if (seq' xs)
      (recur (f z (first' xs)) (rest' xs))
      z )))

(console.log (reduce' #(jbop + %1 %2) 5 (jary 1 2 3)))

(def x nil)

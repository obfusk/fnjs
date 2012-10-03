; --                                                            ; {{{1
;
; File        : fnjs.core.fnjs
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-10-03
;
; Copyright   : Copyright (C) 2012  Felix C. Stegerman
; Licence     : GPLv2 or EPLv1
;
; Depends     : ...
; Description : ...
;
; TODO        : ...
;
; --                                                            ; }}}1

; NB: for now, these functions will only work with JS arrays/objects;
; support for vectors/maps/etc. will follow with those types.

(ns fnjs.core
  (:use [U underscore :obj *root*._]) )

(def VERSION "0.2.0-dev")

(if (jbop == *root*.*fnjs* nil) (jbop = *root*.*fnjs* (jobj)))  ; !!!!
(jbop = *root*.*fnjs*.core *ns*)                                ; TODO

; --

(def- _red U.reduce)

; === Utils ===                                                   {{{1

(def -else true)

(defn apply [f & xs]
  (.!apply f nil (.!concat (U.initial xs) (U.last xs))) )

(defn all-pairs? [f xs]
  (js  "for (var i = 1; i < xs.length; ++i) {
          if (! f (xs[i-1], xs[i])) { return false; }
        }" ) true )

; ...
                                                                ; }}}1

; === Arithmetic ===                                              {{{1

(defn +                       [  & xs] (_red xs #(jbop + %1 %2) 0))
(defn *                       [  & xs] (_red xs #(jbop * %1 %2) 1))
(defn -   ([x] (juop -   x)) ([x & xs] (_red xs #(jbop - %1 %2) x)))
(defn div ([x] (jbop / 1 x)) ([x & xs] (_red xs #(jbop / %1 %2) x)))

; TODO: quot, rem, mod

(defn inc [x] (jbop + x 1))
(defn dec [x] (jbop - x 1))

(defn max [& xs] (U.max xs))
(defn min [& xs] (U.min xs))
                                                                ; }}}1

; === Truth/Compare ===                                           {{{1

(defn not [x] (or (jbop === x false) (jbop === x nil)
                  (jbop === x undefined) ))
(defn ? [x] (juop ! (not x)))

; ...

(defn = [& xs] (all-pairs? U.isEqual xs))
(defn not= [& xs] (juop ! (apply = xs)))

; NB: JS and Clojure comparison semantics differ.

(defn <  [& xs] (all-pairs? #(jbop <  %1 %2) xs))
(defn <= [& xs] (all-pairs? #(jbop <= %1 %2) xs))
(defn >  [& xs] (all-pairs? #(jbop >  %1 %2) xs))
(defn >= [& xs] (all-pairs? #(jbop >= %1 %2) xs))

(defn compare [x y]
  (cond (= x y) 0, (jbop < x y) -1, (jbop > x y) 1
        -else (throw (new Error "compare: not <, >, or =")) ))
                                                                ; }}}1

; === Test ===                                                    {{{1

; NB: JS and Clojure object identity comparison semantics differ.

(defn identical? [x y] (jbop === x y))

(defn nil?   [x] (identical? x nil  ))
(defn true?  [x] (identical? x true ))
(defn false? [x] (identical? x false))

(defn zero? [x] (= x 0))
(defn pos?  [x] (> x 0))
(defn neg?  [x] (< x 0))

(defn even? [x] (zero? (jbop & x 1)))                           ; TODO
(defn odd?  [x] (not (even? x)))

; ...
                                                                ; }}}1

; === Cast ===                                                  ; {{{1

(defn int [x]
  (cond (U.isNumber x) (if (neg? x) (Math.ceil x) (Math.floor x))
        (U.isString x) (.!charCodeAt x 0)
        -else (throw (new Error "int: not number or string")) ))

; ...
                                                                ; }}}1

; === Collections ===                                             {{{1

(defn count [x] (cond (nil? x) 0, -else (U.size x)))

; ...
                                                                ; }}}1

; === Strings ===                                                 {{{1

(defn str [& xs] (_red xs #(jbop + %1 %2) ""))

; ...
                                                                ; }}}1

; ---> TODO <----

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

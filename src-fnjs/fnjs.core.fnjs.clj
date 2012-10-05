; --                                                            ; {{{1
;
; File        : fnjs.core.fnjs
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-10-05
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

; === Helpers ===                                               ; {{{1

(def _zip (fn zip [& xss]                                       ; {{{2
  (when (juop ! xss.length) (throw (new Error "_zip: no arguments")))
  (let [ l (U.min (U.map xss #(.length %))), res (new Array l) ]
    (js  "for (var i = 0; i < l; ++i) {
            res[i] =" (U.map xss #(jget % i))
         "}" ) res )))
                                                                ; }}}2

(def _map (fn map [f & xss]
  (U.map (.!apply _zip nil xss) #(.!apply f nil %)) ))

(def _fil (fn filter [p xs] (U.filter xs #(p %))))

(def _red (fn reduce
  ([f xs] (if xs.length (U.reduce xs #(f %1 %2)) (f)))
  ([f z xs] (U.reduce xs #(f %1 %2) z)) ))

; ...
                                                                ; }}}1

; === Utils ===                                                   {{{1

(defn reload [x]
  (delete (jget require.cache (require.resolve x)))
  (require x) )

(def -else true)

(defn apply [f & xs]
  (.!apply f nil (.!concat (U.initial xs) (U.last xs))) )

(defn apply-new [cls & xs]
  (new (Function.prototype.bind.apply cls
    (.!concat (jary nil) (U.initial xs) (U.last xs)) )))

(defn all-pairs? [f xs]
  (js  "for (var i = 1; i < xs.length; ++i) {
          if (! f (xs[i-1], xs[i])) { return false; }
        }" ) true )

(defn cls-to-string [c x] (c.prototype.toString.call x))
(defn obj-to-string [x] (cls-to-string Object x))

(defn rx [& args] (apply-new RegExp args))

; ...
                                                                ; }}}1

; === Arithmetic ===                                              {{{1

(defn +                       [  & xs] (_red #(jbop + %1 %2) 0 xs))
(defn *                       [  & xs] (_red #(jbop * %1 %2) 1 xs))
(defn -   ([x] (juop -   x)) ([x & xs] (_red #(jbop - %1 %2) x xs)))
(defn div ([x] (jbop / 1 x)) ([x & xs] (_red #(jbop / %1 %2) x xs)))

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

(defn undefined?  [x] (identical? x undefined))
(defn nil?        [x] (identical? x nil))
(defn boolean?    [x] (U.isBoolean x))
(defn number?     [x] (U.isNumber x))
(defn string?     [x] (U.isString x))
(defn regexp?     [x] (U.isRegExp x))
(defn date?       [x] (U.isDate x))
(defn error?      [x] (and (= (typeof x) "object")
                           (= (obj-to-string x) "[object Error]") ))
(defn function?   [x] (U.isFunction x))
(defn array?      [x] (U.isArray x))
(defn object?     [x] (U.isObject x))

(defn true?  [x] (identical? x true))
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
  (cond (number? x) (if (neg? x) (Math.ceil x) (Math.floor x))
        (string? x) (.!charCodeAt x 0)
        -else (throw (new Error "int: not number or string")) ))

; ...
                                                                ; }}}1

; === Collections ===                                             {{{1

(def map    _map)
(def filter _fil)
(def reduce _red)

(defn count [x] (cond (nil? x) 0, -else (U.size x)))

; ...
                                                                ; }}}1

; === Strings ===                                                 {{{1

(defn _cjoin [sep xs]
  (if xs.length (jbop + " " (_red #(jbop + %1 sep %2) xs)) "") )

(defn _brckt [x] (jbop + "<" x ">"))

(defn _pr_undefined [pr?] (if pr? "undefined" ""))
(defn _pr_nil       [pr?] (if pr? "nil" ""))
(defn _pr_boolean   [x] (jbop + "" x))
(defn _pr_number    [x] (jbop + "" x))
(defn _pr_string    [x pr?] (if pr? (JSON.stringify x) x))
(defn _pr_regexp    [x pr?] (cls-to-string RegExp x))

(defn _pr_date   [x pr?] (if pr? (_brckt (_pr_date x false))
                                 (cls-to-string Date x) ))
(defn _pr_error  [x pr?] (if pr? (_brckt (_pr_error x false))
                                 (cls-to-string Error x) ))

(defn _pr_function [x]
  (jbop + "<fn" (if x.name (jbop + " " x.name) "") ">") )

(defn _pr_pairs [ps f]
  (_map (fn [(:ary k v)] (jbop + (f k) " " (f v))) ps) )

(defn _pr_array [x f]
  (jbop + "(jary" (_cjoin " " (_map f x)) ")") )

(defn _pr_object [x f]
  (jbop + "(jobj" (_cjoin ", " (_pr_pairs (U.pairs x) f)) ")") )

(defn _pr_value [x pr? seen]                                    ; {{{2
  ; TODO: window, array/date/... w/ properties, ...

  (let [ f #(_pr_value % true (.!concat seen (jary x))) ]
    (cond (undefined? x) (_pr_undefined pr?), (nil? x) (_pr_nil pr?)
      (boolean? x) (_pr_boolean x), (number? x) (_pr_number x)
      (string? x) (_pr_string x pr?), (regexp? x) (_pr_regexp x)
      (date? x) (_pr_date x pr?), (error? x) (_pr_error x pr?)
      (function? x) (_pr_function x)
      (or (array? x) (U.isArguments x)) (_pr_array x f)
      (>= (.!indexOf seen x) 0) "<circular>"
      (and pr? (function? (.inspect x))) (.!inspect x)          ; TODO
      (and (function? (.toString x))
          (not= (.toString x) Object.prototype.toString) )
        (if pr? (_brckt (jbop + (obj-to-string x) " "
                                (_pr_string (.!toString x) true) ))
                (.!toString x) )
      -else (_pr_object x f) )))
                                                                ; }}}2

(defn _pr-str [x] (_pr_value x true  (jary)))
(defn _str    [x] (_pr_value x false (jary)))

(defn pr-str [& xs]
  (if xs.length (_red #(jbop + %1 " " %2) (_map _pr-str xs)) "") )

(defn str [& xs] (_red #(jbop + %1 %2) "" (_map _str xs)))

; ...
                                                                ; }}}1

; ---> TODO <----

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

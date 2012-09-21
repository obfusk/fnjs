; --                                                            ; {{{1
;
; File        : fnjs/elem.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-09-21
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

(ns fnjs.elem
  (:use [ clojure.string :only [ join ] ]) )

; --

(defn build [xs] (join " " (filter identity (flatten xs))))

(defn list_ [xs] (interpose "," xs))

(def null   "null")
(def true_  "true")
(def false_ "false")

(defn comm-lines [& xs] (for [x xs] [ "//" x "\n" ]))
(defn comm-block [& xs] [ "/*" xs "*/" ])

(defn group [& xs] [ "(" xs ")" ])
(defn block [& xs] [ "{" xs "}" ])

(defn index     [x] [ "[" x "]"])
(defn return    [x] [ "return" x ])
(defn statement [x] [ x ";" ])

(defn statements  [xs]      (map statement xs))
(defn unop        [o x]     (group [o x]))
(defn binop       [o args]  (group (interpose o args)))

(defn object [& xs]
  [ "{" (list_ (for [ [k v] xs ] [ k ":" v ])) "}" ] )

(defn array [& xs]    [ "[" (list_ xs) "]" ])
(defn var_  [k v]     [ "var" k "=" v ] )
(defn call  [f args]  [ (group f (group (list_ args))) ])
(defn get_  [x ys]    [ x (map index ys) ])

(defn if-expr [c a b] [ c "?" a ":" b ])
(defn if-stmt [c a b] [ "if" (group c) (block a) "else" (block b) ])

(defn do-body [xs ret?]
  (let [  ys  (statements (or (seq xs) [ null ]))
          yi  (butlast ys), y (last ys) ]
    [ yi (if ret? (return y) y) ] ))

(defn function
  ([args body]      (function args body true))
  ([args body ret?] (group "function" (group (list_ args))
                      (block (do-body body ret?)) )))

(defn do_
  ([xs]       (do_ xs true))
  ([xs ret?]  (call (function [] xs ret?) [])) )

(defn wrap [body]
  [ "(function () { var _STR_root_STR_ = this;"
      (do-body body false)
    "}).call (this);" ] )

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

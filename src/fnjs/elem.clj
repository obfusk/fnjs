; --                                                            ; {{{1
;
; File        : fnjs/elem.cljs
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-04-12
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
  (:use clojure.string :only [ join ]) )

; --

(defn list_ [xs] (interpose "," xs))

(defn group [& xs] [ "(" xs ")" ])
(defn block [& xs] [ "{" xs "}" ])

(defn return    [x] [ "return" x  ])
(defn statement [x] [ x ";"       ])

(defn statements [xs] (map statement xs))

(defn array [& xs]    [ "[" (list_ xs) "]" ])
(defn var_  [k v]     [ "var" k "=" v ] )
(defn call  [f args]  [ f (group (list_ args)) ])

(defn function [args body]
  (group "function" (group (list_ args)) (block body)) )

(defn if-expr [c a b]
  (let [ [c_ a_ b_] (map group c a b) ]
    [ c_ "?" a_ ":" b_ ] ))

(defn if-stmt [c a b] [ "if" (group c) (block a) "else" (block b) ])

(defn do-body [xs]
  (let [  ys  (statements xs)
          yi  (butlast    ys)
          y   (last       ys) ]
    [ yi (return y) ] ))

(defn do_ [xs] (call (function [] (do-body xs)) []) )

; --

(defn build [xs] (join " " (flatten xs)))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

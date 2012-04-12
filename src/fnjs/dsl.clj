; --                                                            ; {{{1
;
; File        : fnjs/dsl.clj
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

(ns fnjs.dsl
  (:use [ clojure.string :only [ join ] :as _s ]
        [ fnjs.elem                     :as _e ] ))

; --

; MOVE {

(defn die [x] (throw (Exception. x)))

; } MOVE

; --

(declare tr)
(declare tr-list)

(defn mtr [xs] (map tr xs))

; --

(defn *js   [& xs] xs)
(defn *op   [o & args] (_e/operator o (mtr args)))
(defn *def  [k v] (_e/var_ k (tr v)))                           ; TODO
(defn *do   [& body] (_e/do_ (mtr body)))
(defn *fn   [args & body] (_e/function args (mtr body)))

(defn *let [vars & body]
  (let [ vs (map #(apply var_ %1) (partition 2 vars)) ]         ; TODO
    (_e/do_ (concat vs (mtr body))) ))

(defn *if       [c a b] (apply _e/if-expr (mtr [c a b])))
(defn *if-stmt  [c a b] (apply _e/if-stmt (mtr [c a b])))

; --

(def sym-map {                                                  ; {{{1
  "!" "_BNG_"   "%" "_PCT_"   "&" "_AMP_"   "*" "_STR_"   "-" "_MIN_"
  "+" "_PLS_"   "=" "_EQS_"   "|" "_BAR_"   "<" "_LTS_"   ">" "_GTS_"
  "?" "_QMK_"   })
                                                                ; }}}1

(def syms (join (keys sym-map)))
(def sym-rx (re-pattern (str "[\\Q" syms "\\E]")))

; --

(defn tr-sym  [x] (_s/replace x sym-rx #(str (sym-map %1))))
(defn tr-str  [x] (pr-str x))                                   ; TODO
(defn tr-num  [x] x)                                            ; TODO

(defn tr [x]                                                    ; {{{1
; (println (str " --> tr " (pr-str x) " -- (" (type x) ")"))
  (cond
    (and (seq? x) (not (vector? x)))  (tr-list x)
    (symbol?  x)                      (tr-sym  x)
    (string?  x)                      (tr-str  x)
    (number?  x)                      (tr-num  x)
    :else (die "oops: unknown type") ))                         ; TODO
                                                                ; }}}1

; --

; NB: order matters here !!!

(def pub-map (ns-publics *ns*))

(def funcs
  (filter #(.startsWith (str %1) "*") (keys pub-map)) )

(def func-rx
  (re-pattern (join "|" (map #(str "\\Q" %1 "\\E") funcs))) )

; --

(defn tr-list [xs]                                              ; {{{1
  (if (seq xs)
    (let [  x   (first  xs)
            xt  (rest   xs) ]
      (if (and (symbol? x) (re-matches func-rx (str x)))
        (apply (pub-map x) xt)
        (_e/call (tr x) (map tr xt)) ))
    (die "oops: empty list") ))                                 ; TODO
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

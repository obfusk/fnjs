; --                                                            ; {{{1
;
; File        : fnjs/dsl.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-04-13
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
        [ fnjs.elem                     :as _e ]
        [ fnjs.misc                     :as _m ] ))

; --

; coco: (x!)@(y!) --> (_r = x())[_k = y()] || (_r[_k] = {})

; --

; (def DEBUG
;   (set (filter (complement empty?)
;     (_s/split (or (get (System/getenv) "DEBUG") "") #"\s+") )))
;
; (when-not (empty? DEBUG)
;   (.println *err* (str "DEBUG: " (pr-str DEBUG))) )

; --

(declare tr)
(declare tr-list)

(defn mtr [xs] (map tr xs))

; --

(defn *js   [& xs] (for [x xs] (if (string? x) x (tr x))))
(defn *op   [o & args] (_e/operator o (mtr args)))
(defn *def  [k v] (_e/var_ k (tr v)))                           ; TODO
(defn *do   [& body] (_e/do_ (mtr body)))
(defn *fn   [args & body] (_e/function args (mtr body)))

(defn *let [vars & body]
  (let [ vs (for [ x (partition 2 vars) ] (apply var_ x)) ]     ; TODO
    (_e/do_ (concat vs (mtr body))) ))

(defn *if       [c a b] (apply _e/if-expr (mtr [c a b])))
(defn *if-stmt  [c a b] (apply _e/if-stmt (mtr [c a b])))

(defn *ary [& xs] (apply _e/array (mtr xs)))
(defn *obj [& xs] (apply _e/object (partition 2 (mtr xs))))     ; TODO

(defn *get [x & ys] (_e/get_ x (mtr ys)))

; --

(defn dot       [x & ys] (interpose "." (cons x ys)))
(defn dot-bang  [x y & ys] (_e/call (group (dot x y)) ys))

(defn for-ary [vs body]                                         ; {{{1
  (if (seq vs)
    (let [ [v e] (first vs), b (for-ary (rest vs) body) ]
      (dot-bang (tr e) 'map (_e/function [v] b)) )
    (mtr body) ))
                                                                ; }}}1

(defn *for-ary [vars & body] (for-ary (partition 2 vars) body))

; --

(def sym-map {                                                  ; {{{1
  "!" "_BNG_"   "%" "_PCT_"   "&" "_AMP_"   "*" "_STR_"   "-" "_MIN_"
  "+" "_PLS_"   "=" "_EQS_"   "|" "_BAR_"   "<" "_LTS_"   ">" "_GTS_"
  "?" "_QMK_" })
                                                                ; }}}1

(def syms (join (keys sym-map)))
(def sym-rx (re-pattern (str "[\\Q" syms "\\E]")))

; --

(defn tr-sym  [x] (_s/replace x sym-rx #(str (sym-map %1))))
(defn tr-str  [x] (pr-str x))                                   ; TODO
(defn tr-num  [x] x)                                            ; TODO

(defn tr [x]                                                    ; {{{1
; (when (DEBUG "tr")
;   (.println *err* (str "--> [tr] " (pr-str x) " -- " (type x))) )
  (cond
    (and (seq? x) (not (vector? x)))  (tr-list x)               ; TODO
    (symbol?  x)                      (tr-sym  x)
    (string?  x)                      (tr-str  x)
    (number?  x)                      (tr-num  x)
    :else (_m/die (str "oops: unknown type " (type x))) ))      ; TODO
                                                                ; }}}1

; --

; NB: order matters here !!!

(def pub-map (ns-publics *ns*))
(def funcs (filter #(.startsWith (str %1) "*") (keys pub-map)))

(def func-rx
  (re-pattern (join "|" (for [x funcs] (str "\\Q" x "\\E")))) )

; --

(defn swap-1-2 [xs] (let [ [x y & yt] xs ] (concat [y x] yt)))
(defn sym-rest [n s] (symbol (.substring s n)))
(defn ssr      [n s xt] (swap-1-2 (cons (sym-rest n s) xt)))

(defn tr-special [x xt s f]                                     ; {{{1
  (cond
    (= x '.!)               (apply dot-bang (mtr xt))
    (= x '.)                (apply dot      (mtr xt))
    (.startsWith s ".!")    (apply dot-bang (mtr (ssr 2 s xt)))
    (.startsWith s ".")     (apply dot      (mtr (ssr 1 s xt)))
    (re-matches func-rx s)  (apply (pub-map x) xt)
    :else                   (f) ))
                                                                ; }}}1

(defn tr-list [xs]                                              ; {{{1
; (when (DEBUG "tr-list")
;   (.println *err* (str "--> [tr-list ]" (pr-str xs))) )
  (if (seq xs)
    (let [  x (first xs), xt (rest xs)
            f #(_e/call (tr x) (mtr xt)) ]
      (if (symbol? x) (tr-special x xt (str x) f) (f)) )
    (_m/die "oops: empty list") ))                              ; TODO
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

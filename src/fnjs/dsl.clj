; --                                                            ; {{{1
;
; File        : fnjs/dsl.clj
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

(ns fnjs.dsl
  (:use     [ clojure.string :only [ join ] :as _s ])
  (:require [ fnjs.elem                     :as _e ]
            [ fnjs.misc                     :as _m ] ))

; --

(def _meta_ (atom {}))

(defn     defnjs [k v] `(swap! _meta_ #(assoc %1 '~k ~v)))
(defmacro defnjm [k v] (defnjs k v))

; --

(declare tr)
(declare tr-list)

(defn mtr [xs] (map tr xs))

; --

; (defn tr-destr [x] ... TODO ...)

; --

(defn tr_js   [& xs] (for [x xs] (if (string? x) x (tr x))))
(defn tr_juop [o x] (_e/unop o (tr x)))
(defn tr_jbop [o & args] (_e/binop o (mtr args)))
(defn tr_def  [k v] (apply _e/var_ (mtr [k v])))                ; TODO
(defn tr_do   [& body] (_e/do_ (mtr body)))
(defn tr_fn   [args & body] (_e/function (mtr args) (mtr body)))

(defn tr_let [vars & body]                                      ; TODO
  (let [ vs (for [ x (partition 2 vars) ] (apply tr_def x)) ]
    (_e/do_ (concat vs (mtr body))) ))

(defn tr_if       [c a b] (apply _e/if-expr (mtr [c a b])))
(defn tr_if-stmt  [c a b] (apply _e/if-stmt (mtr [c a b])))

(defn tr_jary [& xs] (apply _e/array (mtr xs)))
(defn tr_jobj [& xs] (apply _e/object (partition 2 (mtr xs))))  ; TODO

(defn tr_jget [x & ys] (_e/get_ (tr x) (mtr ys)))

; --

(defn dot       [x & ys] (interpose "." (cons x ys)))
(defn dot-bang  [x y & ys] (_e/call (_e/group (dot x y)) ys))

(defn for-ary [vs body]                                         ; {{{1
  (if (seq vs)                                                  ; TODO
    (let [ [v e] (first vs), b (for-ary (rest vs) body) ]
      (dot-bang (tr e) 'map (_e/function [(tr v)] b)) )
    (mtr body) ))
                                                                ; }}}1

(defn tr_jfor [vars & body] (for-ary (partition 2 vars) body))

; --

; defnjm {                                                      ; {{{1

(defnjm def     tr_def    )
(defnjm do      tr_do     )
(defnjm fn      tr_fn     )
(defnjm fn*     tr_fn     )
(defnjm if      tr_if     )
(defnjm jary    tr_jary   )
(defnjm juop    tr_juop   )
(defnjm jbop    tr_jbop   )
(defnjm jfor    tr_jfor   )
(defnjm jget    tr_jget   )
(defnjm jobj    tr_jobj   )
(defnjm js      tr_js     )
(defnjm let     tr_let    )

; } defnjm                                                      ; }}}1

; --

(def sym-map {                                                  ; {{{1
  "!" "_BNG_"   "%" "_PCT_"   "&" "_AMP_"   "*" "_STR_"   "-" "_MIN_"
  "+" "_PLS_"   "=" "_EQS_"   "|" "_BAR_"   "<" "_LTS_"   ">" "_GTS_"
  "?" "_QMK_"   "#" "_HSH_" })
                                                                ; }}}1

(def sym-rx (re-pattern (str "[\\Q" (join (keys sym-map)) "\\E]")))

(defn tr-sym [x] (_s/replace x sym-rx #(sym-map %1)))
(defn tr-str [x] (pr-str x))                                    ; TODO
(defn tr-num [x] x)                                             ; TODO

; --

(defn tr [x]                                                    ; {{{1
; (.println *err* (str  "-[1]-> " (pr-str x)
;                       " isa " (pr-str (type x)) ))
  (cond
    (and (seq? x) (not (vector? x)))  (tr-list x)               ; TODO
    (symbol?  x)                      (tr-sym  x)
    (string?  x)                      (tr-str  x)
    (number?  x)                      (tr-num  x)
    (nil?     x)                      _e/null
    :else (_m/die (str  "unknown type " (pr-str (type x))
                        " for --> " (pr-str x) " <--" ))))      ; TODO
                                                                ; }}}1

; --

(defn swap-1-2 [xs] (let [ [x y & yt] xs ] (concat [y x] yt)))
(defn sym-rest [n s] (symbol (subs s n)))
(defn ssr      [n s xt] (swap-1-2 (cons (sym-rest n s) xt)))

(defn tr-special [x xt s call]                                  ; {{{1
  (cond
    (= x '.!)             (apply dot-bang (mtr xt))
    (= x '.)              (apply dot      (mtr xt))
    (.startsWith s ".!")  (apply dot-bang (mtr (ssr 2 s xt)))
    (.startsWith s ".")   (apply dot      (mtr (ssr 1 s xt)))
    :else (if-let [ g (@_meta_ x) ] (apply g xt) (call)) ))
                                                                ; }}}1

; --

(defn tr-list [xs]                                              ; {{{1
; (.println *err* (str "-[2]-> " (pr-str xs)))
  (if (seq xs)
    (let [  x     (first xs), xt (rest xs)
            call  #(_e/call (tr x) (mtr xt)) ]
      (if (symbol? x) (tr-special x xt (str x) call) (call)) )
    (_m/die "empty list") ))                                    ; TODO
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

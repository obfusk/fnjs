; --                                                            ; {{{1
;
; File        : fnjs/dsl.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-09-22
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

; TODO: destructuring, assert, loop/recur, docs, ...

; --

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

(defn mk-def  [k v*] (_e/var! (tr k) v* (str (tr '*ns*.) (tr k))))
(defn mk-def- [k v*] (_e/var! (tr k) v*))
(defn mk-var  [k v]  (mk-def- k (tr v)))

; --

(defn tr_use [& refs] (_e/use_ (for [ [k l _ v] refs] (mtr [k l v]))))

(defn tr_ns [x & refs]
  [ (_e/nspace (tr x))
    (for [[u & r] refs] (do (assert (= u :use)) (apply tr_use r))) ])

(defn tr_js   [& xs] (for [x xs] (if (string? x) x (tr x))))
(defn tr_juop [o x] (_e/unop o (tr x)))
(defn tr_jbop [o & args] (_e/binop o (mtr args)))
(defn tr_def  [k v] (mk-def k (tr v)))                          ; TODO
(defn tr_def- [k v] (mk-def- k (tr v)))                         ; TODO
(defn tr_do   [& body] (_e/do_ (mtr body)))

; --

(defn tr_loop [vars body]
  (let [ ks (take-nth 2 vars), vs (take-nth 2 (rest vars)) ]
    (_e/loop_ (mtr ks) (mtr vs) (tr body)) ))

; --

(defn variadic? [args]
  (let [ i (.indexOf args '&) ] (when (not= i -1) i)) )

(defn mk-fn [nm args body]                                      ; {{{1
  (let [ nm' (when-not (nil? nm) (tr nm)), b (mtr body) ]
    (if-let [ i (variadic? args) ]
      (let [ as (take i args), v (args (inc i)) ]
        (_e/function nm' (mtr as) { :k (tr v), :i i } b) )
      (_e/function nm' (mtr args) nil b) )))
                                                                ; }}}1

(defn tr_fn [& sigs]                                            ; {{{1
  (let [ nm (first sigs) ]
    (if (or (symbol? nm) (nil? nm))
      (let [ args (second sigs) ]
        (if (vector? args)
          (mk-fn nm args (drop 2 sigs))
          (let [ g  (group-by #(-> % first variadic? boolean)
                      (rest sigs))
                 fs (g false)
                 v  (first (g true))                            ; TODO
                 v' (when v (apply tr_fn nm v)) ]
            (_e/overload (map #(apply tr_fn nm %) fs) v') )))
      (apply tr_fn nil sigs) )))
                                                                ; }}}1

; --

(defn tr_let [vars & body]                                      ; TODO
  (let [ vs (for [ x (partition 2 vars) ] (apply mk-var x)) ]
    (_e/do_ (concat vs (mtr body))) ))

(defn tr_if [c a b] (apply _e/if_ (mtr [c a b])))

(defn tr_jary [& xs] (apply _e/array (mtr xs)))
(defn tr_jobj [& xs] (apply _e/object (partition 2 (mtr xs))))  ; TODO

(defn tr_jget [x & ys] (_e/get_ (tr x) (mtr ys)))

; --

(defn dot       [x & ys] (interpose "." (cons x ys)))
(defn dot-bang  [x y & ys] (_e/call (_e/group (dot x y)) ys))

(defn for-ary [vs body]                                         ; {{{1
  (if (seq vs)                                                  ; TODO
    (let [ [v e] (first vs), b (for-ary (rest vs) body) ]
      (dot-bang (tr e) 'map (_e/function nil [(tr v)] nil b)) )
    (mtr body) ))
                                                                ; }}}1

(defn tr_jfor [vars & body] (for-ary (partition 2 vars) body))

; --

; defnjm {                                                      ; {{{1

(defnjm def     tr_def    )
(defnjm def-    tr_def-   )
(defnjm do      tr_do     )
(defnjm fn      tr_fn     )
(defnjm fn*     tr_fn     )
(defnjm if      tr_if     )
(defnjm jary    tr_jary   )
(defnjm jbop    tr_jbop   )
(defnjm jfor    tr_jfor   )
(defnjm jget    tr_jget   )
(defnjm jobj    tr_jobj   )
(defnjm js      tr_js     )
(defnjm juop    tr_juop   )
(defnjm let     tr_let    )
(defnjm loop    tr_loop   )
(defnjm ns      tr_ns     )
(defnjm use     tr_use    )

; } defnjm                                                      ; }}}1

; --

(def sym-map {                                                  ; {{{1
  "!" "_BNG_"   "%" "_PCT_"   "&" "_AMP_"   "*" "_STR_"   "-" "_MIN_"
  "+" "_PLS_"   "=" "_EQS_"   "|" "_BAR_"   "<" "_LTS_"   ">" "_GTS_"
  "?" "_QMK_"   "#" "_HSH_"   "'" "_PRM_" })
                                                                ; }}}1

(def sym-rx (re-pattern (str "[\\Q" (join (keys sym-map)) "\\E]")))

(defn tr-sym  [x] (_s/replace x sym-rx #(sym-map %1)))
(defn tr-str  [x] (pr-str x))                                   ; TODO
(defn tr-bool [x] (if x _e/true_ _e/false_))
(defn tr-num  [x] x)                                            ; TODO

; --

(defn tr [x]                                                    ; {{{1
; (.println *err* (str  "-[1]-> " (pr-str x)
;                       " isa " (pr-str (type x)) ))          ;  DEBUG
  (cond
    (and (seq? x) (not (vector? x)))  (tr-list x)               ; TODO
    (symbol?            x)            (tr-sym  x)
    (string?            x)            (tr-str  x)
    (instance? Boolean  x)            (tr-bool x)
    (number?            x)            (tr-num  x)
    (nil?               x)            _e/null
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
; (.println *err* (str "-[2]-> " (pr-str xs)))                ;  DEBUG
  (if (seq xs)
    (let [  x     (first xs), xt (rest xs)
            call  #(_e/call (tr x) (mtr xt)) ]
      (if (symbol? x) (tr-special x xt (str x) call) (call)) )
    (_m/die "empty list") ))                                    ; TODO
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

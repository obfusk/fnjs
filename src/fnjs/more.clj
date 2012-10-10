; --                                                            ; {{{1
;
; File        : fnjs/more.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-10-10
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

(ns fnjs.more
  (:use     [ fnjs.dsl :only [ defnjm tr ] ])
  (:require [ fnjs.elem :as _e ] [ fnjs.misc :as _m ]) )

; === Helpers ===                                               ; {{{1

(def _?       '*root*.*fnjs*.core.?)
(def _seq-m   '*root*.*fnjs*.core.sequence-m)
(def _doseq-m '*root*.*fnjs*.core.doseq-m)

; ...
                                                                ; }}}1

; === Functions ===                                             ; {{{1

(defn tr_defn  [k & spec] (tr `(~'def  ~k (~'fn ~k ~@spec))))
(defn tr_defn- [k & spec] (tr `(~'def- ~k (~'fn ~k ~@spec))))
                                                                ; }}}1

; === Branch/Truth ===                                          ; {{{1

(defn tr_if-not
  ([c a] (tr_if-not c a nil))
  ([c a b] (tr `(~'if (~'not ~c) ~a ~b))) )

(defn tr_when [x & body] (tr `(~'if ~x (~'do ~@body))))
(defn tr_when-not [x & body] (tr `(~'if ~x nil (~'do ~@body))))

(defn tr_cond' [[x y & more :as clauses]]
  (when clauses `(~'if ~x ~y ~(tr_cond' more))) )

(defn tr_cond [& clauses] (tr (tr_cond' clauses)))

; --

(defn tr_not [x] (tr `(~'juop ~'! ~x)))

(defn tr_and [& args] (tr (when args `(~'jbop ~'&& ~@args))))
(defn tr_or  [& args] (tr (when args `(~'jbop ~'|| ~@args))))

(defn tr_?and' [[x & more :as exprs]]
  (if exprs
    (let [ t (_m/mk-sym '__?and__) ]
      `(~'let [~t ~x] (~'if (~_? ~t) ~(tr_?and' more) ~t)) )
    true ))

(defn tr_?or' [[x & more :as exprs]]
  (when exprs
    (let [ t (_m/mk-sym '__?or__) ]
      `(~'let [~t ~x] (~'if (~_? ~t) ~t ~(tr_?or' more))) )))

(defn tr_?and [& exprs] (tr (tr_?and' exprs)))
(defn tr_?or  [& exprs] (tr (tr_?or'  exprs)))
                                                                ; }}}1

; === Comprehensions ===                                        ; {{{1

(defn compr [m exprs body]                                      ; {{{2
  (let [ f #(compr m (rest exprs) body) ]
    (if-let [ [k v] (first exprs) ]
      (cond
        (= k :let  )  `(~'let ~v ~(f))
        (= k :when )  `(~'if  ~v ~(f) (~'.!zero ~m))
        (= k :while)  `(~'if  ~v ~(f) (~'.!exit ~m))
        (keyword? k)  (_m/chk nil "tr_for: invalid keyword")
        :else         `(~'.!bind ~m ~v (~'fn [~k] ~(f))) )
      `(~'.!result ~m ~body) )))
                                                                ; }}}2

(defn tr_comprehension [m exprs body]
  (tr (compr m (partition 2 exprs) body)) )

(defn tr_for [exprs body]
  (tr `(~'comprehension ~_seq-m ~exprs ~body)) )

(defn tr_doseq [exprs & body]
  (tr `(~'do (~'comprehension ~_doseq-m ~exprs (~'do ~@body)) nil)) )
                                                                ; }}}1

; === Destructuring ===                                         ; {{{1

(defn tr_if-let
  ([bindings a] (tr_if-let bindings a nil))
  ([[k e] a b]
    (let [ t (_m/mk-sym '__if_let__) ]
      (tr `(~'let [~t ~e] (~'if ~t (~'let [~k ~t] ~a) ~b))) )))

(defn tr_when-let [[k e] & body]
  (let [ t (_m/mk-sym '__when_let__) ]
    (tr `(~'let [~t ~e] (~'when ~t (~'let [~k ~t] ~@body)))) ))
                                                                ; }}}1

; defnjm {                                                      ; {{{1

(defnjm ?and            tr_?and         )
(defnjm ?or             tr_?or          )
(defnjm and             tr_and          )
(defnjm comprehension   tr_comprehension)
(defnjm cond            tr_cond         )
(defnjm defn            tr_defn         )
(defnjm defn-           tr_defn-        )
(defnjm doseq           tr_doseq        )
(defnjm for             tr_for          )
(defnjm if-let          tr_if-let       )
(defnjm if-not          tr_if-not       )
(defnjm not             tr_not          )
(defnjm or              tr_or           )
(defnjm when            tr_when         )
(defnjm when-let        tr_when-let     )
(defnjm when-not        tr_when-not     )

; } defnjm                                                      ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

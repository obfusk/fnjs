; --                                                            ; {{{1
;
; File        : fnjs/more.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-10-04
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

; --

(defn tr_defn  [k & spec] (tr `(~'def  ~k (~'fn ~k ~@spec))))
(defn tr_defn- [k & spec] (tr `(~'def- ~k (~'fn ~k ~@spec))))

(defn tr_when [x & body] (tr `(~'if ~x (~'do ~@body))))

(defn tr_cond' [[x y & more :as clauses]]
  (when clauses `(~'if ~x ~y ~(tr_cond' more))) )

(defn tr_cond [& clauses] (tr (tr_cond' clauses)))

(defn tr_if-let [[k e] a b]
  (let [ t (_m/mk-sym '__if_let__) ]
    (tr `(~'let [~t ~e] (~'if ~t (~'let [~k ~t] ~a) ~b))) ))

(defn tr_and [& args] (tr (when args `(~'jbop ~'&& ~@args))))
(defn tr_or  [& args] (tr (when args `(~'jbop ~'|| ~@args))))

; --

(def _? '*root*.*fnjs*.core.?)

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

; --

; defnjm {                                                      ; {{{1

(defnjm ?and    tr_?and   )
(defnjm ?or     tr_?or    )
(defnjm and     tr_and    )
(defnjm cond    tr_cond   )
(defnjm defn    tr_defn   )
(defnjm defn-   tr_defn-  )
(defnjm if-let  tr_if-let )
(defnjm or      tr_or     )
(defnjm when    tr_when   )

; } defnjm                                                      ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

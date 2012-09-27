; --                                                            ; {{{1
;
; File        : fnjs/more.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-09-27
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

; === Notes ===                                                 ; {{{1
;
; coco: (x!)@(y!) --> (_r = x())[_k = y()] || (_r[_k] = {})
;
; --                                                            ; }}}1

(ns fnjs.more
  (:use     [ fnjs.dsl :only [ defnjm tr ] :as _d ])
  (:require [ fnjs.elem :as _e ] [ fnjs.misc :as _m ]) )

; --

(defn tr_defn  [k & spec] (_d/mk-def  k (apply _d/tr_fn k spec)))
(defn tr_defn- [k & spec] (_d/mk-def- k (apply _d/tr_fn k spec)))

(defn tr_if-let [[k e] a b]
  (let [ t (_m/mk-sym '__if_let__) ]
    (tr `(~'let [~t ~e] (~'if ~t (~'let [~k ~t] ~a) ~b))) ))

; --

; defnjm {                                                      ; {{{1

(defnjm defn    tr_defn   )
(defnjm defn-   tr_defn-  )
(defnjm if-let  tr_if-let )

; } defnjm                                                      ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

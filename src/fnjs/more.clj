; --                                                            ; {{{1
;
; File        : fnjs/more.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-05-08
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
  (:require [ fnjs.dsl  :only [ defnjm tr mtr ] :as _d ]
            [ fnjs.elem                         :as _e ] ))

; --

(defn tr_defn [k args & body]
  (_e/var_ (tr k) (apply _d/tr_fn args body)) )

(defn tr_if-let [[k e] t f]
  (tr `(~'let [temp# ~e] (~'if temp# (~'let [~k temp#] ~t) ~f))) )

; --

; defnjm {                                                      ; {{{1

(defnjm defn    tr_defn   )
(defnjm if-let  tr_if-let )

; } defnjm                                                      ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

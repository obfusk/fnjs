; --                                                            ; {{{1
;
; File        : fnjs/core.clj
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

(ns fnjs.core
  (:require [ fnjs.dsl  :as _d ]
            [ fnjs.elem :as _e ]
            [ fnjs.more :as _m ] ))

; --

(defn fnjs                                                      ; {{{1
  ([body]       (fnjs body true))
  ([body wrap?] (_e/build (if wrap?
                  (_e/statement (_e/do_ (_d/mtr body) false))
                  (_e/statements (_d/mtr body)) ))))
                                                                ; }}}1

(defmacro fnjm [& body] (fnjs body))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

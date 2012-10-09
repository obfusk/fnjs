; --                                                            ; {{{1
;
; File        : fnjs/core.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-10-09
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
  (:require [ fnjs.dsl :as _d ] [ fnjs.elem :as _e ]
              fnjs.more )) ; more translations !!!

; --

(defn fnjs
  ([body]       (fnjs body true))
  ([body wrap?] (_e/build (if wrap? (_e/wrap       (_d/mtr body))
                                    (_e/statements (_d/mtr body)) ))))

(defmacro fnjm [& body] (fnjs body))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

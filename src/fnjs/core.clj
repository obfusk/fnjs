; --                                                            ; {{{1
;
; File        : fnjs/core.clj
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

(ns fnjs.core
  (:use [ fnjs.dsl :as _d ]) )

; --

(defn fnjs [body] (apply _d/*do body))
(defmacro fnjs* [& body] (fnjs body))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

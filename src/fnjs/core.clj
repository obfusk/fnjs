; --                                                            ; {{{1
;
; File        : fnjs/core.clj
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

(ns fnjs.core
  (:use [ fnjs.dsl  :as _d ]
        [ fnjs.elem :as _e ] ))

; --

(defn fnjs-nowrap [x]       (_e/build (_e/statement (_d/tr x)))) ; ???
(defn fnjs        [body]    (fnjs-nowrap `(~'*do ~@body)))
(defmacro fnjs*   [& body]  (fnjs body))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

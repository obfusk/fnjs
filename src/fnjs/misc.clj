; --                                                            ; {{{1
;
; File        : fnjs/misc.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-09-26
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

(ns fnjs.misc)

; --

; MOVE {

(defn safe-read [x] (binding [ *read-eval* false ] (read-string x)))
(defn read-many [x] (safe-read (str "(" x "\n)")))
(defn read-file [f] (read-many (slurp f)))

; } MOVE

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

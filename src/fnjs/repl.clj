; --                                                            ; {{{1
;
; File        : fnjs/repl.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-10-05
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

(ns fnjs.repl
  (:require [ fnjs.core :as _c ] [ fnjs.misc :as _m ]
            [ clojure.string :as _s ] ))

; --

(defn repl []
  (doseq [ line (line-seq (java.io.BufferedReader. *in*)) ]
    (-> line _m/read-many (_c/fnjs false) (_s/replace #"\n" " ")
        println )))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

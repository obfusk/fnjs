; --                                                            ; {{{1
;
; File        : fnjs/misc.clj
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

(ns fnjs.misc)

; --

(defn die [x] (throw (Exception. x)))

(defn safe-read [x] (binding [ *read-eval* false ] (read-string x)))
(defn read-file [f] (safe-read (str "(" (slurp f) "\n)")))

(defn err-println [& xs] (binding [ *out* *err* ] (apply println xs)))

(defn out-or-err [x f g]
  (let [ o (:out x), e (:err x) ]
    (if (empty? e) (f o) (g e o)) ))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

; --                                                            ; {{{1
;
; File        : fnjs/misc.clj
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

(ns fnjs.misc
  (:require fnjs.ParseError)
  (:import  fnjs.ParseError) )

; --

(def _sym_counter_ (atom 0))  ; gensym too unpredictable for tests
(defn mk-sym [x] (symbol (str x "_GEN_" (swap! _sym_counter_ inc))))

(defn chk [x msg] (when-not x (throw (ParseError. msg))))

(defn safe-read [x]
  (try (binding [ *read-eval* false ] (read-string x))
  (catch RuntimeException e
    (throw (ParseError. (str "read failed: " (.getMessage e)) e)) )))

(defn read-many [x] (safe-read (str "(" x "\n)")))
(defn read-file [f] (read-many (slurp f)))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

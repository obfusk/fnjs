; --                                                            ; {{{1
;
; File        : fnjs/run.clj
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

(ns fnjs.run
  (:use [ clojure.java.shell  :only [ sh    ] ]
        [ clojure.string      :only [ split ] ] ))

; --

(def uglify-cmd (split "uglifyjs -b -i 2 -nm -ns" #"\s+"))

(defn uglify! [x] (apply sh (concat uglify-cmd [:in x])))
(defn node!   [x] (sh "node" :in x))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

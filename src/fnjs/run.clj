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
  (:use [ clojure.java.shell :only [ sh ] ]) )

; --

; MOVE {

(defn safe-read [x] (binding [ *read-eval* false ] (read-string x)))
(defn read-file [f] (safe-read (str "(" (slurp f) "\n)")))

; } MOVE

; --

(defn uglify! [x] (sh "uglifyjs" "-b" "-i" "2" "-nm" :in x))
(defn node!   [x] (sh "node" :in x))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

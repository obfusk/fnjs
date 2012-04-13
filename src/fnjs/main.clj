; --                                                            ; {{{1
;
; File        : fnjs/main.clj
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

(ns fnjs.main
  (:gen-class)
  (:use [ fnjs.core :as _c ]
        [ fnjs.misc :as _m ] ))

; --

(defn compile-files [xs]                                        ; {{{1
  (let [ ys (if (seq xs) xs [ "/dev/stdin" ]) ]
    (doseq [ y ys ]
      (println "// file:" y)                                    ; TODO
      (-> y _m/read-file _c/fnjs println) )))
                                                                ; }}}1

(defn -main [& args]
  (try
    (compile-files args)
    (catch Exception e (_m/err-println "fnjs:" (.getMessage e))) ))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

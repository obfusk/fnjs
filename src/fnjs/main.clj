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
        [ fnjs.run  :as _r ]
        [ fnjs.misc :as _m ] ))

; --

(defn show-err-out [e o]
  (do (err-println (str "--STDERR--\n" e "\n--STDERR--"))
      (err-println (str "--STDOUT--\n" o "\n--STDOUT--")) ))

(defn compile-file [x]
  (-> x _m/read-file _c/fnjs _r/uglify!) )

(defn compile-files [xs]                                        ; {{{1
  (let [ ys (if (seq xs) xs [ "/dev/stdin" ]) ]
    (doseq [ y ys ]
      (println "// file:" y)                                    ; TODO
      (_m/out-or-err (compile-file y) print show-err-out) )))
                                                                ; }}}1

(defn run-files [xs]                                            ; {{{1
  (let [ ys (if (seq xs) xs [ "/dev/stdin" ]) ]
    (doseq [ y ys ]
      (_m/out-or-err (compile-file y)                           ; TODO
        (fn [z] (_m/out-or-err (_r/node! z) print show-err-out))
        show-err-out ))))
                                                                ; }}}1

; --

(defn -main [& args]                                            ; {{{1
  (try
    (if (seq args)
      (let [ x (first args), xt (rest args) ]
        (condp get (keyword x)
          #{ :i :repl     } (_m/die "oops: repl not yet implemented")
          #{ :c :compile  } (compile-files xt)
          #{ :r :run      } (run-files xt)
          (_m/die (str "oops: unknown command `" x "'")) ))
      (run-files []) )
    (catch Exception e (err-println (.getMessage e))) )
  (flush)
  (System/exit 0) ) ; workaround for BUG in sh                  ; TODO
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

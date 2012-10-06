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
            fnjs.ParseError [ clojure.string :as _s ] )
  (:import  fnjs.ParseError) )

; --

(defn repl-err [debug e]                                        ; {{{1
  (let [ m  (pr-str (.getMessage e)), c (-> e type pr-str pr-str)
         s1 (map #(-> % str pr-str) (.getStackTrace e))
         s2 (apply str (interpose ", " s1))
         s3 (when debug (str ", cls: " c ", stack: [" s2 "]")) ]
    (str "({ msg: " m s3 " })") ))
                                                                ; }}}1

(defn repl [debug]                                              ; {{{1
  (let [ ok #(println "OK" %), err #(println "ERROR" %) ]
    (doseq [ l (line-seq (java.io.BufferedReader. *in*)) ]
      (try
        (-> l _m/read-many (_c/fnjs false) (_s/replace #"\n" " ") ok)
      (catch ParseError e (err (repl-err debug e))) ))))
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

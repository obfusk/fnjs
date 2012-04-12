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
        [ fnjs.run  :as _r ] ))

; --

(defn -main [& args]                                            ; {{{1
  (if (seq args)
    (let [  x   (first  args)
            xt  (rest   args) ]
      (condp = (keyword x)
        :repl (_r/die "oops: repl not yet implemented")
        :compile
          (for [ f xt ]
            (let [ o (-> _r/read-file _c/fnjs _r/uglify!) ]
              (if-let [ e (:err o) ]
                (println (str "-- ERROR --\n" e "\n--ERROR\n" o))
                (println o) )))))
    (_r/die "oops: run not yet implemented") ))
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

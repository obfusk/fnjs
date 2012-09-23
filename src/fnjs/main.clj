; --                                                            ; {{{1
;
; File        : fnjs/main.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-09-23
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
  (:require [ fnjs.core :as _c ]
;           [ fnjs.elem :as _e ]
            [ fnjs.misc :as _m ] ))

; --

(defn compile-files [xs]                                        ; {{{1
  (let [ ys (if (seq xs) xs [ "/dev/stdin" ]) ]
    (doseq [y ys]
    ; (println (_e/comm-lines (str "file: " y)))                ; ????
      (-> y _m/read-file _c/fnjs println) )))
                                                                ; }}}1

(defn -main [& args]                                            ; {{{1
  (try
    (compile-files args)
  (catch Exception e
    (do (.printStackTrace e)                                  ;  DEBUG
        (.println *err* (str "fnjs: " (.getMessage e))))
        (System/exit 1) )))
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

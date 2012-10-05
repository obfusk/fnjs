; --                                                            ; {{{1
;
; File        : fnjs/main.clj
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

(ns fnjs.main
  (:gen-class)
  (:require [ fnjs.core :as _c ] [ fnjs.repl :as _r ]
            [ fnjs.misc :as _m ] ))

; --

(defn compile-files [xs]
  (doseq [ x (if (seq xs) xs [ "/dev/stdin" ]) ]
    (println "//" x)
    (-> x _m/read-file _c/fnjs println) ))

(defn -main [& args]                                            ; {{{1
  (let [ debug (= "yes" (System/getenv "FNJS_DEBUG")) ]
    (try (if (= args [":repl"]) (_r/repl) (compile-files args))
    (catch Exception e
      (do (.println *err* (str "fnjs: [" (type e) "] "
                               (.getMessage e) ))
          (when debug (.printStackTrace e))
          (System/exit 1) )))))
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

; --                                                            ; {{{1
;
; File        : fnjs.eval.node.fnjs
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

(ns fnjs.eval.node
  (:use [C child_process] [V vm] [F fnjs.core]) )

; --

; NB: callback (code?, fnjs_err?, eval_err?, result?)

; --

(defn show-stderr [data] (.!write process.stderr data))

(defn show-exit [code signal]
  (.!write process.stderr (F.str "[fnjs exited w/ code " code
    ", signal " (or signal "NONE") "]\n" )))

(defn run [code c f]
  (try (jary nil (if c (.!runInContext     V code c f)
                       (.!runInThisContext V code   f) ))
  (catch e (jary e nil)) ))

(defn on-stdout [stack] (fn on-stdout' [data]                   ; {{{1
  (let [ top (.!pop stack), c (.context top), f (.file top) ]
    (cond (.!test (F.rx "^OK ") data)
            (let [ code (.!slice data 3) ]
              (F.apply (.cb top) code nil (run code c f)) )
          (.!test (F.rx "^ERROR ") data)
            (let [ code (.!slice data 6) ]
              (.!cb top nil (.!runInNewContext V code) nil nil) )
          F.-else
            (throw (new Error "fnjs stdout: not OK or ERROR")) ))))
                                                                ; }}}1

(defn on-eval [fnjs stack] (fn eval [code opts]                 ; {{{1
  (let [ (:obj :strs [ context file cb ])
           (or opts (jobj)) ]
    (when (F.>= (.!indexOf code "\n") 0)
      (throw (new Error "fnjs eval: code contains newline")) )
    (.!unshift stack (jobj context context, file file, cb cb))
    (.!write fnjs.stdin (F.str code "\n")) )))
                                                                ; }}}1

(defn start [opts]                                              ; {{{1
  (let [ (:obj :strs [ exit stderr ]) (or opts (jobj))
         fnjs (C.spawn  (F.str (or process.env.FNJS_HOME ".")
                          "/bin/fnjs" ) (jary ":repl") )
         stack (jary) ]
    (.!on fnjs.stdout "data" (on-stdout stack))
    (.!on fnjs.stderr "data" (or stderr show-stderr))
    (.!on fnjs        "exit" (or exit show-exit))
    (on-eval fnjs stack) ))
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

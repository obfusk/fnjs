; --                                                            ; {{{1
;
; File        : fnjs.repl.fnjs
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-10-01
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

; TODO:
;   * review !!!
;   * improve w/ new features !!!

(ns fnjs.repl
  (:use [C child_process] [R repl] [V vm]) )

(def VERSION "0.2.0-dev")

; --

(def _data_ (jobj init false, eval (jobj file "repl", cb (fn []))))
                                                                ; TODO

(jbop = global.exports module.exports)  ; ???                   ; TODO
(jbop = global.module  module)          ; ???                   ; TODO
(jbop = global.require require)                                 ; TODO

; --

(defn eval-1 [code context file cb]                             ; {{{1
  ; (console.log "--> eval ...")                              ;  DEBUG

  ; !!! DEPENDS ON IMPLEMENTATION !!!                           ; !!!!
  (let [ code' (if (jbop && (jbop !== cb.name "finish")
                            (.!test (js "/^\\(/") code)
                            (.!test (js "/\\)$/") code) )
                ; (do (console.log "--> monkey patching")     ;  DEBUG
                  (.!slice code 1 -1) ; )
                  code ) ]
    (jbop = _data_.eval (jobj file file, cb cb))                ; TODO
    (.!write fnjs.stdin code') ))
                                                                ; }}}1

(defn eval-2 [code d]                                           ; {{{1
  (let [ (:ary err res)
           (try (jary nil (.!runInThisContext V code d.file))
           (catch e (jary e nil)) ) ]
    (jbop = global._ res)                                       ; TODO

    ; try {                                                     ; {{{2
    ;   var ns = V.runInThisContext (
    ;     '_STR_ns_STR_.__namespace__', d.file
    ;   );
    ;   if (ns) { repl.prompt = ns + '=> '; }                 //  !!!!
    ; } catch (e) {}                                            ; }}}2

    ; (console.log (jobj code (jbop + "" code), res res, err err))
    ; (console.log "--> callback ...")
    (if err (do
        (.!write process.stdout (jbop + (jbop || err.stack err) "\n"))
        (d.cb nil undefined) )
      (d.cb nil res) )))
                                                                ; }}}1

; --

(def fnjs (C.spawn
  (jbop + process.env.FNJS_HOME "/bin/fnjs") (jary ":repl") ))

; --

(defn start []                                                  ; {{{1
  (.!write process.stdout (jbop + "fnjs v" VERSION "\n"))
  (let [ repl (R.start (jobj prompt "fnjs> ", terminal false
                             eval eval-1 )) ]
    (.!on repl "exit" (fn []
      ; (.!write process.stderr "bye.\n")
      (.!exit process) ))))
                                                                ; }}}1

(.!on fnjs.stdout "data" (fn [data]                             ; {{{1
  ; (console.log (jbop + "--> " data))                        ;  DEBUG
  (eval-2 data _data_.eval)
  (when (juop ! _data_.init)                                    ; TODO
    (jbop = _data_.init true)                                   ; TODO
    (start) )))
                                                                ; }}}1

(.!on fnjs.stderr "data" (fn [data]
  (.!write process.stderr data) ))

(.!on fnjs "exit" (fn [code signal]
  (.!write process.stderr (jbop +
    "[fnjs exited w/ code " code ", signal " signal "]\n" ))
  (.!exit process) ))

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

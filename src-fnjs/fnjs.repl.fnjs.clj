; --                                                            ; {{{1
;
; File        : fnjs.repl.fnjs
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

; TODO:
;   * review !!!
;   * improve w/ new features !!!
;   * make more functional (w/ atom) !?

(ns fnjs.repl
  (:use [C child_process] [R repl] [V vm] [F fnjs.core]) )

; --

(def _data_ (jobj
  init false, count 0, fnjs nil
  eval (jobj file "repl", cb (fn []))))                         ; TODO

(jbop = global.exports  module.exports) ; ???                   ; TODO
(jbop = global.module   module)         ; ???                   ; TODO
(jbop = global.require  require)                                ; TODO

(jbop = global.DEBUG    false)          ; ???                   ; TODO
(jbop = global.F        F)                                      ; TODO

; --

(defn eval-1 [code context file cb]                             ; {{{1
  ; (console.log "--> eval ...")                              ;  DEBUG

  ; !!! DEPENDS ON IMPLEMENTATION !!!                           ; !!!!
  (let [ code' (if (and (F.not= cb.name "finish")
                        (.!test (F.rx "^\\(") code)
                        (.!test (F.rx "\\)$") code) )
                ; (do (console.log "--> monkey patching")     ;  DEBUG
                  (.!slice code 1 -1) ; )
                  code ) ]
    (jbop = _data_.eval (jobj file file, cb cb))                ; TODO
    (.!write _data_.fnjs.stdin code') ))
                                                                ; }}}1

(defn eval-2 [code d]                                           ; {{{1
  (when DEBUG (console.log (F.str "[ --> "
    (.!replace (F.str code) (F.rx "\\n" "g") " ") " <-- ]" )))
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

    ; (console.log (jobj code (F.str code), res res, err err))
    ; (console.log "--> callback ...")
    (if err (do
        (.!write process.stdout (F.str (or err.stack err) "\n"))
        (d.cb nil undefined) )
      (d.cb nil res) )))
                                                                ; }}}1

; --

(defn start []                                                  ; {{{1
  (when (F.= _data_.count 0)
    (.!write process.stdout (F.str
      "fnjs v" F.VERSION "\nF is fnjs.core.\n" ))
    (let [ repl (R.start (jobj prompt "fnjs> ", terminal false
                               writer F.pr-str, eval eval-1 )) ]
      (.!on repl "exit" (fn []
        ; (.!write process.stderr "bye.\n")
        (.!exit process) ))))
    (jbop = _data_.count (F.inc _data_.count)) )
                                                                ; }}}1

(defn start-fnjs []                                             ; {{{1
  (jbop = _data_.fnjs (C.spawn
    (F.str process.env.FNJS_HOME "/bin/fnjs") (jary ":repl") ))

  (.!on _data_.fnjs.stdout "data" (fn [data]                    ; {{{2
    ; (console.log (F.str "--> " data))                       ;  DEBUG
    (eval-2 (if (or _data_.init (F.= _data_.count 0))
                data "undefined" ) _data_.eval )
    (when (F.not _data_.init)
      (jbop = _data_.init true)
      (start) )))                                               ; TODO
                                                                ; }}}2

  (.!on _data_.fnjs.stderr "data" (fn [data]
    (.!write process.stderr data) ))

  (.!on _data_.fnjs "exit" (fn [code signal]                    ; {{{2
    (.!write process.stderr (F.str
      "[fnjs exited w/ code " code ", signal " signal
      "; respawning ...]\n" ))
    ; (.!exit process)
    (jbop = _data_.init false)
    (start-fnjs) )))
                                                                ; }}}2
                                                                ; }}}1

; --

(start-fnjs)

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

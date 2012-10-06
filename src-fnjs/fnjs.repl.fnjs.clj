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
  (:use [R repl] [F fnjs.core] [E fnjs.eval.node]) )

; --

(def DEBUG (F.= process.env.FNJS_DEBUG "yes"))

; --

(defn show-err [e]
  (.!write process.stdout (F.str (or e.stack e) "\n")) )

(defn fnjs-exit [c s] (E.show-exit c s) (.!exit process))
(defn repl-exit [] (.!exit process))

(defn patch [code] (.!slice code 1 -1))
(defn patch? [code cb] ; !!! DEPENDS ON IMPLEMENTATION !!!        !!!!
  (and (F.not= cb.name "finish") (.!test (F.rx "^\\(") code)
                                 (.!test (F.rx "\\)$") code) ))

(defn fnjs-eval [fnjs code f] (fnjs code (jobj file "repl", cb f)))

(defn repl-eval [fnjs] (fn [code context _ cb]                  ; {{{1
  (fnjs-eval fnjs (if (patch? code cb) (patch code) code)
    (fn [c fe ee x]
      (when context.DEBUG (console.log (F.str "[ --> "
        (.!replace (F.str c) (F.rx "\\n" "g") " ") " <-- ]" )))
      (if (or fe ee)
        (do (show-err (if fe fe.msg ee))
            (cb nil undefined))
        (cb nil x) )))))
                                                                ; }}}1

(defn start-repl [fnjs] (fn [_ fe ee _]                         ; {{{1
  (when (or fe ee) (throw (new Error
    (F.str "fnjs init error: " (if fe fe.msg ee)) )))
  (let [ repl (R.start (jobj prompt "fnjs> ", terminal false
                writer F.pr-str, eval (repl-eval fnjs) )) ]
  (jbop = repl.context.exports module.exports)                  ; TODO
  (jbop = repl.context.DEBUG   DEBUG)                           ; TODO
  (jbop = repl.context.F       F)                               ; TODO
  (.!on repl "exit" repl-exit) )))
                                                                ; }}}1

(defn start-fnjs []                                             ; {{{1
  (.!write process.stdout (F.str "fnjs v" F.VERSION
    "\nfnjs.core is available as F.\n" ))
  (let [ fnjs (E.start (jobj exit fnjs-exit)) ]
    (fnjs-eval fnjs "(__FNJS_INIT__)" start-repl) ))
                                                                ; }}}1

; --

(start-fnjs)

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :


; --                                                            ; {{{1
;
; File        : fnjs/repl.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-09-26
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
  (:require [ fnjs.core       :as _c ]
            [ fnjs.misc       :as _m ]
            [ clojure.string  :as _s ] ))

; --

(defn repl []                                                   ; {{{1
  (let [ o #(-> % (_s/replace #"\n" " ") println) ]
    (o (_c/fnjs-init))
    (doseq [line (line-seq (java.io.BufferedReader. *in*))]
      (-> line _m/read-many (_c/fnjs false) o) )))
                                                                ; }}}1

; --

; DAMN {{{1
; (def node-expr
;   "require ('repl').start ({ prompt: '', terminal: false });" )
;
; (def node-cmd ["node" "-e" node-expr])
;
; (defn repl []
;   (let [ p (.start (doto (ProcessBuilder. (into-array node-cmd))
;                          (.redirectErrorStream true) )) ]
;     (with-open [ i (.getInputStream p), o (.getOutputStream p) ]
;       (let [  r (-> i java.io.InputStreamReader.
;                       java.io.BufferedReader.)
;               w (-> o java.io.OutputStreamWriter.) ]
;         (clojure.main/repl
;           :prompt #(print ">>> ")
;           :eval (fn [x]
;             (println "INPUT -->" (pr-str x) "<--")
;             (.write w (str x "\n"))
;             (.flush w)
;             (loop [done? false]
;               (if (.ready r)
;                 (let [line (.readLine r)]
;                   (println "OUTPUT -->" line "<--")
;                   (recur true) )
;                 (when-not done? (recur false)) ))))
;         (.waitFor p) ))))
; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

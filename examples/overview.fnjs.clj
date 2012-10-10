; --                                                            ; {{{1
;
; File        : examples/overview.fnjs
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-09-20
;
; Copyright   : Copyright (C) 2012  Felix C. Stegerman
; Licence     : GPLv2 or EPLv1
;
; Description : Overview of current (TODO) features.
;
; --
;
; To view the javascript generated from this file and/or run it:
;
;   $ ./_scripts/run examples/overview.fnjs | ./_scripts/ugly   # view
;   $ ./_scripts/run examples/overview.fnjs | node              # run
;
; --
;
; === Syntax ===                                                  {{{2
;
;   fnjs syntax is similar to clojure.
;                                                                 }}}2
;
; === Differences ===                                             {{{2
;
;   fnjs is not clojure! -- just nicer syntax for javascript.
;   It also has no macros, only built-in forms like fn, let, ...
;                                                                 }}}2
;
; === Also ===                                                    {{{2
;
;   You can do compile-time metaprogramming if you use the fnjs
;   library from a clojure program; either via fnj[sm] or defnjm.
;                                                                 }}}2
;
; === The basics ===                                              {{{2
;
;   Most symbols simply map to indentifiers; foo.bar.baz works fine.
;   Special chars in symbols are replaced; e.g. x-y becomes x_MIN_y;
;   see src/dsl.clj for the mapping.
;
;   NB: numbers and strings currently output as they would in clojure.
;   This could cause problems with differences between clojure and
;   javascript in extended number formats and strings with special
;   characters.  This needs to be investigated.  Simple numbers and
;   strings should be fine.
;
;   NB: def just maps to var and is therefore lexically scoped.
;   NB: this also means a def is not allowed at the end of a fn or do,
;   because it is not an expression and cannot be used in a return
;   statement (and fn/do return their last expression).
;
;   There is also syntax to access and call properties -- unlike
;   foo.bar.baz (which is an identifier), these work with expressions:
;     (. obj p1 p2 p3)  --->  obj.p1.p2.p3
;     (.p obj)          --->  obj.p
;     (.! obj p a b c)  --->  (obj.p a b c)
;     (.!p obj ...)     --->  (obj.p ..c)
;                                                                 }}}2
;
; --                                                            ; }}}1

(console.log "Hello, World")          ; call, symbol, string

(def PI 3.14159265)                   ; var; lexical scope

(def foo (fn [x y z]                  ; function
  (jbop * x (jbop + PI y z)) ))       ; binary operator

(defn show []                         ; defn
  (console.log.apply console
    (Array.prototype.slice.call arguments) ))

(show (foo 1 2 3))

(show (jbop + 1 2 3)                  ; javascript operators (!)
      (jbop === 1 1)
      (juop - 42) )

(def my-ary (jary 2 3 5 7 11 "..."))  ; array
(def my-obj (jobj x 1, y 2, z 3))     ; object

(do (show my-ary)                     ; do block; lexical scope;
    (show my-obj) )                   ; files are wrapped in these

(def my-obj-2 (jobj
  x (jobj a 1, b (jary 2 3), c 4)
  y (jobj d 5, f #(jbop + % 1)) ))

(show (. my-obj-2 x a)                ; property access
      (.c my-obj-2.x)
      (.! my-obj-2.y f 99)            ; property call
      (.!f my-obj-2.y 512) )

(show (jget my-obj-2.x "b" 1))        ; indexing

; (jfor [ x (jary 1 2 3)                ; for/map
;         y (jary "foo" "bar") ]
;   (show "x=" x "y=" y))

(let [ PI 42, x 37 ]                  ; let; lexical scope
  (show PI x) )

(show (if (jbop === 1 1) "eq" "ne"))  ; if/else

(js "console.log (\"JS!\")")          ; javascript passthrough

; --

(show nil)                            ; nil is null

(if-let [ x (jbop * PI PI) ]          ; if-let
  (show "x=" x) (show "false"))

; ...

; --

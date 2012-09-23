; --                                                            ; {{{1
;
; File        : fnjs.core.fnjs
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-09-22
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

(ns fnjs.core
  (:use [U underscore :obj *root*._]) )

(def VERSION "0.1.2-dev")

; --

; TODO TODO TODO --- WORK-IN-PROGRESS --- TODO TODO TODO

; all-pairs? mk-obj

(defn ? [x] (not (or (= x undefined) (= x nil) (= x false))))

(defn apply [f]
  (def- a arguments)
  (let [ xt (U.tail (U.toArray a))
         yi (U.initial xt)
         y  (U.last xt) ]
    (.!apply f nil (.!concat yi y)) ))

; --

(defn = []                                                      ; {{{1
  (js  "for (var i = 1; i < arguments.length; ++i) {
          if (! U.isEqual (arguments[i-1], arguments [i])) {
            return false;
          }
        }" )
  true )
                                                                ; }}}1

; (defn not= [] (not (apply = arguments

; --

(defn Keyword [s]
  (jbop = this.str s)
  (jbop = this.key #(jbop + "k:" this.str))
  nil )

(defn key [x]
  (if (= (typeof x) "string") (jbop + "s:" x) (.!key x)) )

; --

(defn Vector []
  (jbop = this.data (U.toArray arguments))
  nil )

(defn Map []                                                    ; {{{1
  (jbop = this.data (jobj))
  (js  "for (var i = 0; i < arguments.length; i += 2) {
          this.data[arguments[i]] = key (arguments[i+1]);
        }" )
  nil )
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

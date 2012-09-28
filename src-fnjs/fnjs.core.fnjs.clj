; --                                                            ; {{{1
;
; File        : fnjs.core.fnjs
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-09-24
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

(def VERSION "0.2.0-dev")

; --

(defn all-pairs? [f xs]
  (js  "for (var i = 1; i < xs.length; ++i) {
          if (! f (arguments[i-1], arguments [i])) { return false; }
        }" ) true )

(defn apply [f & xs]
  (let [ xi (U.initial xs), ys (U.last xs) ]
    (.!apply f null (.!concat xi ys)) ))

; --

(defn +                     [  & xs] (U.reduce xs #(jbop + %1 %2) 0))
(defn - ([x] (juop - x  )) ([x & xs] (U.reduce xs #(jbop - %1 %2) x)))
(defn *                     [  & xs] (U.reduce xs #(jbop * %1 %2) 1))
(defn / ([x] (jbop / 1 x)) ([x & xs] (U.reduce xs #(jbop / %1 %2) x)))

(defn inc [x] (jbop + x 1))
(defn dec [x] (jbop - x 1))

; --

(defn not [x] (jbop || (jbop === x false    ) (jbop === x null)
                       (jbop === x undefined) (jbop === x nil ) ))
(defn ? [x] (juop ! (not x)))

(defn = [& xs] (all-pairs? #(U.isEqual %1 %2) xs))
(defn not= [& xs] (not (apply = xs)))

; --

; TODO TODO TODO --- WORK-IN-PROGRESS --- TODO TODO TODO

; mk-obj

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

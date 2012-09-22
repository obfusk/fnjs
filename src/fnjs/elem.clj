; --                                                            ; {{{1
;
; File        : fnjs/elem.clj
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

(ns fnjs.elem
  (:use [ clojure.string :only [ join split ] ]) )

; --

(defn build [xs] (join " " (filter identity (flatten xs))))

(defn list_ [xs] (interpose "," xs))

(def null   "null")
(def true_  "true")
(def false_ "false")

(defn comm-lines [& xs] (for [x xs] [ "//" x "\n" ]))
(defn comm-block [& xs] [ "/*" xs "*/" ])

(defn group [& xs] [ "(" xs ")" ])
(defn block [& xs] [ "{" xs "}" ])

(defn index     [x] [ "[" x "]"])
(defn return    [x] [ "return" x ])
(defn statement [x] [ x ";" ])

(defn statements  [xs]      (map statement xs))
(defn unop        [o x]     (group [o x]))
(defn binop       [o args]  (group (interpose o args)))

(defn object [& xs]
  [ "{" (list_ (for [ [k v] xs ] [ k ":" v ])) "}" ] )

(defn array [& xs]      [ "[" (list_ xs) "]" ])
(defn var!  [k v & ks]  [ "var" (interpose "=" (cons k ks)) "=" v ])
(defn call  [f args]    [ (group f (group (list_ args))) ])
(defn get_  [x ys]      [ x (map index ys) ])
(defn if_   [c a b]     [ c "?" a ":" b ])

(defn do-body [xs ret?]
  (let [  ys  (statements (or (seq xs) [ null ]))
          yi  (butlast ys), y (last ys) ]
    [ yi (if ret? (return y) y) ] ))

(defn function                                                  ; {{{1
  ([nm args v body] (function nm args v body true))
  ([nm args v body ret?]
    [ "(function" nm "(" (list_ args) ") {"
        (when v
          [ "var" (:k v) "= Array.prototype.slice.call ("
              "arguments," (:i v) ");" ])
        (do-body body ret?)
      "})" ]))
                                                                ; }}}1

(defn do_
  ([xs]       (do_ xs true))
  ([xs ret?]  (call (function nil [] nil xs ret?) [])) )

; --

(defn wrap [body]                                               ; {{{1
  [ "(function () {
        var _STR_root_STR_     = this;
        var _STR_ns_STR_       = {};
        var _STR_exports_STR_  =
          typeof exports === 'undefined' ? null : exports;"
      (do-body body false)
    "}).call (this);" ])
                                                                ; }}}1

(defn mknested [x n]                                            ; {{{1
  [ "(function (o, xs) {
        for (var x in xs) {
          o = o[xs[x]] = o[xs[x]] == null ? {} : o[xs[x]];
        }
     })(" x ", [" (list_ (map pr-str (split (str n) #"\."))) "]);" ])
                                                                ; }}}1

(defn nspace [x]                                                ; {{{1
  [  "if (_STR_exports_STR_ === null) {"
       (mknested "_STR_root_STR_" (str '_STR_namespaces_STR_. x))
       "_STR_ns_STR_ = _STR_root_STR_._STR_namespaces_STR_." x ";
      } else {
        _STR_ns_STR_ = _STR_exports_STR_;
      }
      _STR_ns_STR_.__namespace__ =" (-> x str pr-str) ";" ])
                                                                ; }}}1

(defn use_ [refs]                                               ; {{{1
  (for [ [k lib v] refs ]
    (let [ path [ "_STR_root_STR_._STR_namespaces_STR_." lib ] ]
      [  "if (_STR_exports_STR_ === null) {"
            (when v [ "if (" path "== null) {" path "=" v "; }" ])
           "var" k "=" path ";
          } else {
            var" k "= require (" (-> lib str pr-str) ");
          }" ])))
                                                                ; }}}1

(defn overload [fs v]                                           ; {{{1
  (let [              [  i     over          vari      ]
         (map gensym '[__i__ __overloads__ __variadic__]) ]
    [ "(function (" over "," vari ") {
          return (function () {
            for (var" i "in" over ") {
              if (" over "[" i "].length == arguments.length) {
                return" over "[" i "].apply (null, arguments);
              }
            }
            if (" vari "&&" vari ".length <= arguments.length) {
              return" vari ".apply (null, arguments);
            } else {
              throw new Error ('Wrong number of args');
            }
          });
       })([" (list_ fs) "]," (or v "null") ");" ]))
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

; --                                                            ; {{{1
;
; File        : fnjs/elem.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-10-09
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
  (:use [ clojure.string :only [ join split ] ])
  (:require [ fnjs.misc :as _m ]) )

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
  [ "({" (list_ (for [ [k v] xs ] [ k ":" v ])) "})" ] )

(defn var! [k v & ks]
  [ "var" (interpose "=" (cons k ks)) "=" v ";" ] )

(defn array [& xs]      [ "[" (list_ xs) "]" ])
(defn call  [f args]    [ (group f (group (list_ args))) ])
(defn get_  [x ys]      [ x (map index ys) ])
(defn if_   [c a b]     (group [ c "?" a ":" b ]))

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

(defn new_ [x args] [ "new" x "(" (list_ args) ")" ])
(defn throw_ [x] [ "(function () { throw" x "; })()" ])

(defn try_ [body cnm cbody fbody]                               ; {{{1
  [ "(function () {
        try { return" body "}"
        (when cbody [ "catch (" cnm ") { return" cbody "; }" ])
        (when fbody [ "finally { return" fbody "; }" ])
    "})()" ])
                                                                ; }}}1

; --

(def _rt  '_STR_root_STR_)
(def _ns  '_STR_ns_STR_)
(def _ex  '_STR_exports_STR_)
(def _fn  '_STR_fnjs_STR_)
(def _nss '_STR_fnjs_STR_.namespaces)
(def _cr  '_STR_fnjs_STR_.core)

(def lib (let [ f #(symbol (apply str _rt '. _cr '. %&)) ]
  { :get (f 'get), :nth (f 'nth), :drop (f 'drop) } ))

; --

(defn mknested [x n]                                            ; {{{1
  [ "(function (o, xs) {
        for (var x in xs) {
          o = o[xs[x]] = o[xs[x]] == null ? {} : o[xs[x]];
        }
     })(" x ", [" (list_ (map pr-str (split (str n) #"\."))) "]);" ])
                                                                ; }}}1

(def init
  [  "var" _ex "= typeof exports === 'undefined' ? null : exports;
      var" _rt "=" _ex "=== null ? window : global;
      var" _ns "= {}; undefined;" ])

(defn wrap [body]
  [ "(function () {" init (do-body body false) "}).call (this);" ] )

(defn nspace [x]                                                ; {{{1
  [  "if (" _ex "=== null) {"
        (mknested _rt (str _nss '. x))
        _ns "=" _rt "." _nss "." x ";
      } else {"
        _ns "=" _ex ";
      }"
      _ns ".__namespace__ =" (-> x str pr-str) ";" ])
                                                                ; }}}1

(defn use_ [refs]                                               ; {{{1
  (for [ [k l obj module] refs ]
    (let [ path (str _rt '. _nss '. l) ]
      [  "if (" _ex "=== null) {"
            (when obj [ "if (" path "== null) {" path "=" obj "; }" ])
           "var" k "=" path ";
          } else {
            var" k "= require (" (or module (-> l str pr-str)) ");
          }" ])))
                                                                ; }}}1

(defn overload [nm fs v]                                        ; {{{1
  (let [                 [  i     over          vari      ]
         (map _m/mk-sym '[__i__ __overloads__ __variadic__]) ]
    [ "(function (" over "," vari ") {
          return (function" nm "() {
            for (var" i "in" over ") {
              if (" over "[" i "].length == arguments.length) {
                return" over "[" i "].apply (null, arguments);
              }
            }
            if (" vari "&&" vari ".length <= arguments.length) {
              return" vari ".apply (null, arguments);
            } else {
              throw new Error ('Wrong number of args.');
            }
          });
       })([" (list_ fs) "]," (or v "null") ")" ]))
                                                                ; }}}1

(defn loop_ [args vars vs body]                                 ; {{{1
  (let [ [cont res] (map _m/mk-sym '[__continue__ __result__]) ]
    [ "(function (" args "," cont ") {
          var recur = function () {"
            args "= Array.prototype.slice.call (arguments);
            return" cont ";
          };
          while (true) {"
            vars
            res "=" body ";
            if (" res "!==" cont ") { return" res "; }
          }
       })([" (list_ vs) "], {})" ]))
                                                                ; }}}1

; --

; vim: set tw=70 sw=2 sts=2 et fdm=marker :

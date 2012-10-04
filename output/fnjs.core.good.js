(function() {
  var _STR_exports_STR_ = typeof exports === "undefined" ? null : exports;
  var _STR_root_STR_ = _STR_exports_STR_ === null ? window : global;
  var _STR_ns_STR_ = {};
  undefined;
  if (_STR_exports_STR_ === null) {
    (function(o, xs) {
      for (var x in xs) {
        o = o[xs[x]] = o[xs[x]] == null ? {} : o[xs[x]];
      }
    })(_STR_root_STR_, [ "_STR_fnjs_STR_", "namespaces", "fnjs", "core" ]);
    _STR_ns_STR_ = _STR_root_STR_._STR_fnjs_STR_.namespaces.fnjs.core;
  } else {
    _STR_ns_STR_ = _STR_exports_STR_;
  }
  _STR_ns_STR_.__namespace__ = "fnjs.core";
  if (_STR_exports_STR_ === null) {
    if (_STR_root_STR_._STR_fnjs_STR_.namespaces.underscore == null) {
      _STR_root_STR_._STR_fnjs_STR_.namespaces.underscore = _STR_root_STR_._;
    }
    var U = _STR_root_STR_._STR_fnjs_STR_.namespaces.underscore;
  } else {
    var U = require("underscore");
  }
  var VERSION = _STR_ns_STR_.VERSION = "0.2.0-dev";
  _STR_root_STR_._STR_fnjs_STR_ == null ? _STR_root_STR_._STR_fnjs_STR_ = {} : null;
  _STR_root_STR_._STR_fnjs_STR_.core = _STR_ns_STR_;
  var _red = U.reduce;
  var _MIN_else = _STR_ns_STR_._MIN_else = true;
  var apply = _STR_ns_STR_.apply = function apply(f) {
    var xs = Array.prototype.slice.call(arguments, 1);
    return f.apply(null, U.initial(xs).concat(U.last(xs)));
  };
  var all_MIN_pairs_QMK_ = _STR_ns_STR_.all_MIN_pairs_QMK_ = function all_MIN_pairs_QMK_(f, xs) {
    for (var i = 1; i < xs.length; ++i) {
      if (!f(xs[i - 1], xs[i])) {
        return false;
      }
    }
    return true;
  };
  var _PLS_ = _STR_ns_STR_._PLS_ = function _PLS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return _red(xs, function(__lambda__1__, __lambda__2__) {
      return __lambda__1__ + __lambda__2__;
    }, 0);
  };
  var _STR_ = _STR_ns_STR_._STR_ = function _STR_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return _red(xs, function(__lambda__3__, __lambda__4__) {
      return __lambda__3__ * __lambda__4__;
    }, 1);
  };
  var _MIN_ = _STR_ns_STR_._MIN_ = function _MIN_(__overloads___GEN_2, __variadic___GEN_3) {
    return function() {
      for (var __i___GEN_1 in __overloads___GEN_2) {
        if (__overloads___GEN_2[__i___GEN_1].length == arguments.length) {
          return __overloads___GEN_2[__i___GEN_1].apply(null, arguments);
        }
      }
      if (__variadic___GEN_3 && __variadic___GEN_3.length <= arguments.length) {
        return __variadic___GEN_3.apply(null, arguments);
      } else {
        throw new Error("Wrong number of args.");
      }
    };
  }([ function _MIN_(x) {
    return -x;
  } ], function _MIN_(x) {
    var xs = Array.prototype.slice.call(arguments, 1);
    return _red(xs, function(__lambda__5__, __lambda__6__) {
      return __lambda__5__ - __lambda__6__;
    }, x);
  });
  var div = _STR_ns_STR_.div = function div(__overloads___GEN_5, __variadic___GEN_6) {
    return function() {
      for (var __i___GEN_4 in __overloads___GEN_5) {
        if (__overloads___GEN_5[__i___GEN_4].length == arguments.length) {
          return __overloads___GEN_5[__i___GEN_4].apply(null, arguments);
        }
      }
      if (__variadic___GEN_6 && __variadic___GEN_6.length <= arguments.length) {
        return __variadic___GEN_6.apply(null, arguments);
      } else {
        throw new Error("Wrong number of args.");
      }
    };
  }([ function div(x) {
    return 1 / x;
  } ], function div(x) {
    var xs = Array.prototype.slice.call(arguments, 1);
    return _red(xs, function(__lambda__7__, __lambda__8__) {
      return __lambda__7__ / __lambda__8__;
    }, x);
  });
  var inc = _STR_ns_STR_.inc = function inc(x) {
    return x + 1;
  };
  var dec = _STR_ns_STR_.dec = function dec(x) {
    return x - 1;
  };
  var max = _STR_ns_STR_.max = function max() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return U.max(xs);
  };
  var min = _STR_ns_STR_.min = function min() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return U.min(xs);
  };
  var not = _STR_ns_STR_.not = function not(x) {
    return x === false || x === null || x === undefined;
  };
  var _QMK_ = _STR_ns_STR_._QMK_ = function _QMK_(x) {
    return !not(x);
  };
  var _EQS_ = _STR_ns_STR_._EQS_ = function _EQS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return all_MIN_pairs_QMK_(U.isEqual, xs);
  };
  var not_EQS_ = _STR_ns_STR_.not_EQS_ = function not_EQS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return !apply(_EQS_, xs);
  };
  var _LTS_ = _STR_ns_STR_._LTS_ = function _LTS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return all_MIN_pairs_QMK_(function(__lambda__9__, __lambda__10__) {
      return __lambda__9__ < __lambda__10__;
    }, xs);
  };
  var _LTS__EQS_ = _STR_ns_STR_._LTS__EQS_ = function _LTS__EQS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return all_MIN_pairs_QMK_(function(__lambda__11__, __lambda__12__) {
      return __lambda__11__ <= __lambda__12__;
    }, xs);
  };
  var _GTS_ = _STR_ns_STR_._GTS_ = function _GTS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return all_MIN_pairs_QMK_(function(__lambda__13__, __lambda__14__) {
      return __lambda__13__ > __lambda__14__;
    }, xs);
  };
  var _GTS__EQS_ = _STR_ns_STR_._GTS__EQS_ = function _GTS__EQS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return all_MIN_pairs_QMK_(function(__lambda__15__, __lambda__16__) {
      return __lambda__15__ >= __lambda__16__;
    }, xs);
  };
  var compare = _STR_ns_STR_.compare = function compare(x, y) {
    return _EQS_(x, y) ? 0 : x < y ? -1 : x > y ? 1 : _MIN_else ? function() {
      throw new Error("compare: not <, >, or =");
    }() : null;
  };
  var identical_QMK_ = _STR_ns_STR_.identical_QMK_ = function identical_QMK_(x, y) {
    return x === y;
  };
  var nil_QMK_ = _STR_ns_STR_.nil_QMK_ = function nil_QMK_(x) {
    return identical_QMK_(x, null);
  };
  var undefined_QMK_ = _STR_ns_STR_.undefined_QMK_ = function undefined_QMK_(x) {
    return identical_QMK_(x, undefined);
  };
  var true_QMK_ = _STR_ns_STR_.true_QMK_ = function true_QMK_(x) {
    return identical_QMK_(x, true);
  };
  var false_QMK_ = _STR_ns_STR_.false_QMK_ = function false_QMK_(x) {
    return identical_QMK_(x, false);
  };
  var zero_QMK_ = _STR_ns_STR_.zero_QMK_ = function zero_QMK_(x) {
    return _EQS_(x, 0);
  };
  var pos_QMK_ = _STR_ns_STR_.pos_QMK_ = function pos_QMK_(x) {
    return _GTS_(x, 0);
  };
  var neg_QMK_ = _STR_ns_STR_.neg_QMK_ = function neg_QMK_(x) {
    return _LTS_(x, 0);
  };
  var even_QMK_ = _STR_ns_STR_.even_QMK_ = function even_QMK_(x) {
    return zero_QMK_(x & 1);
  };
  var odd_QMK_ = _STR_ns_STR_.odd_QMK_ = function odd_QMK_(x) {
    return not(even_QMK_(x));
  };
  var int = _STR_ns_STR_.int = function int(x) {
    return U.isNumber(x) ? neg_QMK_(x) ? Math.ceil(x) : Math.floor(x) : U.isString(x) ? x.charCodeAt(0) : _MIN_else ? function() {
      throw new Error("int: not number or string");
    }() : null;
  };
  var count = _STR_ns_STR_.count = function count(x) {
    return nil_QMK_(x) ? 0 : _MIN_else ? U.size(x) : null;
  };
  var _cjoin = _STR_ns_STR_._cjoin = function _cjoin(xs) {
    return xs.length ? _red(xs, function(__lambda__17__, __lambda__18__) {
      return __lambda__17__ + "," + __lambda__18__;
    }) : "";
  };
  var _pr_MIN_str = _STR_ns_STR_._pr_MIN_str = function _pr_MIN_str(x, seen) {
    return nil_QMK_(x) ? "null" : undefined_QMK_(x) ? "undefined" : U.isNumber(x) || U.isBoolean(x) ? x.toString() : U.isString(x) ? JSON.stringify(x) : U.isFunction(x) ? "<fn" + (x.name ? " " + x.name : "") + ">" : _MIN_else ? _GTS__EQS_(seen.indexOf(x), 0) ? "<circular>" : function() {
      seen.push(x);
      return function() {
        var p = function(__lambda__19__) {
          return _pr_MIN_str(__lambda__19__, seen);
        };
        return U.isArray(x) || U.isArguments(x) ? "[" + _cjoin(U.map(x, p)) + "]" : U.isFunction(x.toString) ? function() {
          var s = x.toString();
          return /^\[object [A-Za-z]+\]$/.test(s) ? "{" + _cjoin(U.map(U.pairs(x), function(__destructure___GEN_7) {
            var __destructure___GEN_8 = __destructure___GEN_7;
            var k = __destructure___GEN_8[0];
            var v = __destructure___GEN_8[1];
            return p(k) + ":" + p(v);
          })) + "}" : JSON.stringify(s);
        }() : "<???>";
      }();
    }() : null;
  };
  var pr_MIN_str = _STR_ns_STR_.pr_MIN_str = function pr_MIN_str(x) {
    return _pr_MIN_str(x, []);
  };
  var _str = _STR_ns_STR_._str = function _str(x) {
    return nil_QMK_(x) || undefined_QMK_(x) ? "" : U.isString(x) ? x : _MIN_else ? pr_MIN_str(x) : null;
  };
  var str = _STR_ns_STR_.str = function str() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return _red(U.map(xs, _str), function(__lambda__20__, __lambda__21__) {
      return __lambda__20__ + __lambda__21__;
    }, "");
  };
}).call(this);

(function() {
  var _STR_exports_STR_ = typeof exports === "undefined" ? null : exports;
  var _STR_root_STR_ = exports === null ? window : global;
  var _STR_ns_STR_ = {};
  (function(o, xs) {
    for (var x in xs) {
      o = o[xs[x]] = o[xs[x]] == null ? {} : o[xs[x]];
    }
  })(_STR_root_STR_, [ "_STR_fnjs_STR_" ]);
  _STR_root_STR_._STR_fnjs_STR_.nil = _STR_root_STR_._STR_fnjs_STR_.nil || new function NIL() {
    this.nil_QMK_ = true;
  };
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
  var all_MIN_pairs_QMK_ = _STR_ns_STR_.all_MIN_pairs_QMK_ = function all_MIN_pairs_QMK_(f, xs) {
    for (var i = 1; i < xs.length; ++i) {
      if (!f(xs[i - 1], xs[i])) {
        return false;
      }
    }
    return true;
  };
  var apply = _STR_ns_STR_.apply = function apply(f) {
    var xs = Array.prototype.slice.call(arguments, 1);
    return function() {
      var xi = U.initial(xs);
      var ys = U.last(xs);
      return f.apply(null, xi.concat(ys));
    }();
  };
  var _PLS_ = _STR_ns_STR_._PLS_ = function _PLS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return U.reduce(xs, function(__lambda__1__, __lambda__2__) {
      return __lambda__1__ + __lambda__2__;
    }, 0);
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
    return U.reduce(xs, function(__lambda__3__, __lambda__4__) {
      return __lambda__3__ - __lambda__4__;
    }, x);
  });
  var _STR_ = _STR_ns_STR_._STR_ = function _STR_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return U.reduce(xs, function(__lambda__5__, __lambda__6__) {
      return __lambda__5__ * __lambda__6__;
    }, 1);
  };
  var _SLH_ = _STR_ns_STR_._SLH_ = function _SLH_(__overloads___GEN_5, __variadic___GEN_6) {
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
  }([ function _SLH_(x) {
    return 1 / x;
  } ], function _SLH_(x) {
    var xs = Array.prototype.slice.call(arguments, 1);
    return U.reduce(xs, function(__lambda__7__, __lambda__8__) {
      return __lambda__7__ / __lambda__8__;
    }, x);
  });
  var inc = _STR_ns_STR_.inc = function inc(x) {
    return x + 1;
  };
  var dec = _STR_ns_STR_.dec = function dec(x) {
    return x - 1;
  };
  var not = _STR_ns_STR_.not = function not(x) {
    return x === false || x === null || x === undefined || x === _STR_root_STR_._STR_fnjs_STR_.nil;
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
}).call(this);

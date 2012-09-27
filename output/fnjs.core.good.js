(function() {
  var _STR_root_STR_ = this, _STR_ns_STR_ = {};
  var _STR_exports_STR_ = typeof exports === "undefined" ? null : exports;
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
  var VERSION = _STR_ns_STR_.VERSION = "0.1.2-dev";
  var all_MIN_pairs_QMK_ = _STR_ns_STR_.all_MIN_pairs_QMK_ = function all_MIN_pairs_QMK_(f, xs) {
    for (var i = 1; i < xs.length; ++i) {
      if (!f(arguments[i - 1], arguments[i])) {
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
    return U.reduce(xs, function(p1__396_HSH_, p2__397_HSH_) {
      return p1__396_HSH_ + p2__397_HSH_;
    }, 0);
  };
  var _MIN_ = _STR_ns_STR_._MIN_ = function(__overloads___GEN_2, __variadic___GEN_3) {
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
    return U.reduce(xs, function(p1__398_HSH_, p2__399_HSH_) {
      return p1__398_HSH_ - p2__399_HSH_;
    }, x);
  });
  var _STR_ = _STR_ns_STR_._STR_ = function _STR_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return U.reduce(xs, function(p1__400_HSH_, p2__401_HSH_) {
      return p1__400_HSH_ * p2__401_HSH_;
    }, 1);
  };
  var _SLH_ = _STR_ns_STR_._SLH_ = function(__overloads___GEN_5, __variadic___GEN_6) {
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
    return U.reduce(xs, function(p1__402_HSH_, p2__403_HSH_) {
      return p1__402_HSH_ / p2__403_HSH_;
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
    return all_MIN_pairs_QMK_(function(p1__404_HSH_, p2__405_HSH_) {
      return U.isEqual(p1__404_HSH_, p2__405_HSH_);
    }, xs);
  };
  var not_EQS_ = _STR_ns_STR_.not_EQS_ = function not_EQS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return not(apply(_EQS_, xs));
  };
  var Keyword = _STR_ns_STR_.Keyword = function Keyword(s) {
    this.str = s;
    this.key = function() {
      return "k:" + this.str;
    };
    return _STR_root_STR_._STR_fnjs_STR_.nil;
  };
  var key = _STR_ns_STR_.key = function key(x) {
    return _EQS_(typeof x, "string") ? "s:" + x : x.key();
  };
  var Vector = _STR_ns_STR_.Vector = function Vector() {
    this.data = U.toArray(arguments);
    return _STR_root_STR_._STR_fnjs_STR_.nil;
  };
  var Map = _STR_ns_STR_.Map = function Map() {
    this.data = {};
    for (var i = 0; i < arguments.length; i += 2) {
      this.data[arguments[i]] = key(arguments[i + 1]);
    }
    return _STR_root_STR_._STR_fnjs_STR_.nil;
  };
}).call(this);

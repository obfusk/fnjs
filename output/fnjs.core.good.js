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
  var _QMK_ = _STR_ns_STR_._QMK_ = function(x) {
    return not(or(_EQS_(x, undefined), _EQS_(x, null), _EQS_(x, false)));
  };
  var apply = _STR_ns_STR_.apply = function(f) {
    var a = arguments;
    return function() {
      var xt = U.tail(U.toArray(a));
      var yi = U.initial(xt);
      var y = U.last(xt);
      return f.apply(null, yi.concat(y));
    }();
  };
  var _EQS_ = _STR_ns_STR_._EQS_ = function() {
    for (var i = 1; i < arguments.length; ++i) {
      if (!U.isEqual(arguments[i - 1], arguments[i])) {
        return false;
      }
    }
    return true;
  };
  var Keyword = _STR_ns_STR_.Keyword = function(s) {
    this.str = s;
    this.key = function() {
      return "k:" + this.str;
    };
    return null;
  };
  var key = _STR_ns_STR_.key = function(x) {
    return _EQS_(typeof x, "string") ? "s:" + x : x.key();
  };
  var Vector = _STR_ns_STR_.Vector = function() {
    this.data = U.toArray(arguments);
    return null;
  };
  var Map = _STR_ns_STR_.Map = function() {
    this.data = {};
    for (var i = 0; i < arguments.length; i += 2) {
      this.data[arguments[i]] = key(arguments[i + 1]);
    }
    return null;
  };
}).call(this);

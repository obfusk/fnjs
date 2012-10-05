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
  var _zip = _STR_ns_STR_._zip = function zip() {
    var xss = Array.prototype.slice.call(arguments, 0);
    !xss.length ? function() {
      return function() {
        throw new Error("_zip: no arguments");
      }();
    }() : null;
    return function() {
      var l = U.min(U.map(xss, function(__lambda__1__) {
        return __lambda__1__.length;
      }));
      var res = new Array(l);
      for (var i = 0; i < l; ++i) {
        res[i] = U.map(xss, function(__lambda__2__) {
          return __lambda__2__[i];
        });
      }
      return res;
    }();
  };
  var _map = _STR_ns_STR_._map = function map(f) {
    var xss = Array.prototype.slice.call(arguments, 1);
    return U.map(_zip.apply(null, xss), function(__lambda__3__) {
      return f.apply(null, __lambda__3__);
    });
  };
  var _fil = _STR_ns_STR_._fil = function filter(f, xs) {
    return U.filter(xs, function(__lambda__4__) {
      return f(__lambda__4__);
    });
  };
  var _red = _STR_ns_STR_._red = function reduce(__overloads___GEN_2, __variadic___GEN_3) {
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
  }([ function reduce(f, xs) {
    return U.reduce(xs, function(__lambda__5__, __lambda__6__) {
      return f(__lambda__5__, __lambda__6__);
    });
  }, function reduce(f, z, xs) {
    return U.reduce(xs, function(__lambda__7__, __lambda__8__) {
      return f(__lambda__7__, __lambda__8__);
    }, z);
  } ], null);
  var reload = _STR_ns_STR_.reload = function reload(x) {
    delete require.cache[require.resolve(x)];
    return require(x);
  };
  var _MIN_else = _STR_ns_STR_._MIN_else = true;
  var apply = _STR_ns_STR_.apply = function apply(f) {
    var xs = Array.prototype.slice.call(arguments, 1);
    return f.apply(null, U.initial(xs).concat(U.last(xs)));
  };
  var apply_MIN_new = _STR_ns_STR_.apply_MIN_new = function apply_MIN_new(cls) {
    var xs = Array.prototype.slice.call(arguments, 1);
    return new (Function.prototype.bind.apply(cls, [ null ].concat(U.initial(xs), U.last(xs))));
  };
  var all_MIN_pairs_QMK_ = _STR_ns_STR_.all_MIN_pairs_QMK_ = function all_MIN_pairs_QMK_(f, xs) {
    for (var i = 1; i < xs.length; ++i) {
      if (!f(xs[i - 1], xs[i])) {
        return false;
      }
    }
    return true;
  };
  var cls_MIN_to_MIN_string = _STR_ns_STR_.cls_MIN_to_MIN_string = function cls_MIN_to_MIN_string(c, x) {
    return c.prototype.toString.call(x);
  };
  var obj_MIN_to_MIN_string = _STR_ns_STR_.obj_MIN_to_MIN_string = function obj_MIN_to_MIN_string(x) {
    return cls_MIN_to_MIN_string(Object, x);
  };
  var rx = _STR_ns_STR_.rx = function rx() {
    var args = Array.prototype.slice.call(arguments, 0);
    return apply_MIN_new(RegExp, args);
  };
  var _PLS_ = _STR_ns_STR_._PLS_ = function _PLS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return _red(function(__lambda__9__, __lambda__10__) {
      return __lambda__9__ + __lambda__10__;
    }, 0, xs);
  };
  var _STR_ = _STR_ns_STR_._STR_ = function _STR_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return _red(function(__lambda__11__, __lambda__12__) {
      return __lambda__11__ * __lambda__12__;
    }, 1, xs);
  };
  var _MIN_ = _STR_ns_STR_._MIN_ = function _MIN_(__overloads___GEN_5, __variadic___GEN_6) {
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
  }([ function _MIN_(x) {
    return -x;
  } ], function _MIN_(x) {
    var xs = Array.prototype.slice.call(arguments, 1);
    return _red(function(__lambda__13__, __lambda__14__) {
      return __lambda__13__ - __lambda__14__;
    }, x, xs);
  });
  var div = _STR_ns_STR_.div = function div(__overloads___GEN_8, __variadic___GEN_9) {
    return function() {
      for (var __i___GEN_7 in __overloads___GEN_8) {
        if (__overloads___GEN_8[__i___GEN_7].length == arguments.length) {
          return __overloads___GEN_8[__i___GEN_7].apply(null, arguments);
        }
      }
      if (__variadic___GEN_9 && __variadic___GEN_9.length <= arguments.length) {
        return __variadic___GEN_9.apply(null, arguments);
      } else {
        throw new Error("Wrong number of args.");
      }
    };
  }([ function div(x) {
    return 1 / x;
  } ], function div(x) {
    var xs = Array.prototype.slice.call(arguments, 1);
    return _red(function(__lambda__15__, __lambda__16__) {
      return __lambda__15__ / __lambda__16__;
    }, x, xs);
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
    return all_MIN_pairs_QMK_(function(__lambda__17__, __lambda__18__) {
      return __lambda__17__ < __lambda__18__;
    }, xs);
  };
  var _LTS__EQS_ = _STR_ns_STR_._LTS__EQS_ = function _LTS__EQS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return all_MIN_pairs_QMK_(function(__lambda__19__, __lambda__20__) {
      return __lambda__19__ <= __lambda__20__;
    }, xs);
  };
  var _GTS_ = _STR_ns_STR_._GTS_ = function _GTS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return all_MIN_pairs_QMK_(function(__lambda__21__, __lambda__22__) {
      return __lambda__21__ > __lambda__22__;
    }, xs);
  };
  var _GTS__EQS_ = _STR_ns_STR_._GTS__EQS_ = function _GTS__EQS_() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return all_MIN_pairs_QMK_(function(__lambda__23__, __lambda__24__) {
      return __lambda__23__ >= __lambda__24__;
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
  var undefined_QMK_ = _STR_ns_STR_.undefined_QMK_ = function undefined_QMK_(x) {
    return identical_QMK_(x, undefined);
  };
  var nil_QMK_ = _STR_ns_STR_.nil_QMK_ = function nil_QMK_(x) {
    return identical_QMK_(x, null);
  };
  var boolean_QMK_ = _STR_ns_STR_.boolean_QMK_ = function boolean_QMK_(x) {
    return U.isBoolean(x);
  };
  var number_QMK_ = _STR_ns_STR_.number_QMK_ = function number_QMK_(x) {
    return U.isNumber(x);
  };
  var string_QMK_ = _STR_ns_STR_.string_QMK_ = function string_QMK_(x) {
    return U.isString(x);
  };
  var regexp_QMK_ = _STR_ns_STR_.regexp_QMK_ = function regexp_QMK_(x) {
    return U.isRegExp(x);
  };
  var date_QMK_ = _STR_ns_STR_.date_QMK_ = function date_QMK_(x) {
    return U.isDate(x);
  };
  var error_QMK_ = _STR_ns_STR_.error_QMK_ = function error_QMK_(x) {
    return _EQS_(typeof x, "object") && _EQS_(obj_MIN_to_MIN_string(x), "[object Error]");
  };
  var function_QMK_ = _STR_ns_STR_.function_QMK_ = function function_QMK_(x) {
    return U.isFunction(x);
  };
  var array_QMK_ = _STR_ns_STR_.array_QMK_ = function array_QMK_(x) {
    return U.isArray(x);
  };
  var object_QMK_ = _STR_ns_STR_.object_QMK_ = function object_QMK_(x) {
    return U.isObject(x);
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
    return number_QMK_(x) ? neg_QMK_(x) ? Math.ceil(x) : Math.floor(x) : string_QMK_(x) ? x.charCodeAt(0) : _MIN_else ? function() {
      throw new Error("int: not number or string");
    }() : null;
  };
  var count = _STR_ns_STR_.count = function count(x) {
    return nil_QMK_(x) ? 0 : _MIN_else ? U.size(x) : null;
  };
  var _cjoin = _STR_ns_STR_._cjoin = function _cjoin(sep, xs) {
    return xs.length ? " " + _red(function(__lambda__25__, __lambda__26__) {
      return __lambda__25__ + sep + __lambda__26__;
    }, xs) : "";
  };
  var _brckt = _STR_ns_STR_._brckt = function _brckt(x) {
    return "<" + x + ">";
  };
  var _pr_undefined = _STR_ns_STR_._pr_undefined = function _pr_undefined(pr_QMK_) {
    return pr_QMK_ ? "undefined" : "";
  };
  var _pr_nil = _STR_ns_STR_._pr_nil = function _pr_nil(pr_QMK_) {
    return pr_QMK_ ? "nil" : "";
  };
  var _pr_boolean = _STR_ns_STR_._pr_boolean = function _pr_boolean(x) {
    return "" + x;
  };
  var _pr_number = _STR_ns_STR_._pr_number = function _pr_number(x) {
    return "" + x;
  };
  var _pr_string = _STR_ns_STR_._pr_string = function _pr_string(x, pr_QMK_) {
    return pr_QMK_ ? JSON.stringify(x) : x;
  };
  var _pr_regexp = _STR_ns_STR_._pr_regexp = function _pr_regexp(x, pr_QMK_) {
    return cls_MIN_to_MIN_string(RegExp, x);
  };
  var _pr_date = _STR_ns_STR_._pr_date = function _pr_date(x, pr_QMK_) {
    return pr_QMK_ ? _brckt(_pr_date(x, false)) : cls_MIN_to_MIN_string(Date, x);
  };
  var _pr_error = _STR_ns_STR_._pr_error = function _pr_error(x, pr_QMK_) {
    return pr_QMK_ ? _brckt(_pr_error(x, false)) : cls_MIN_to_MIN_string(Error, x);
  };
  var _pr_function = _STR_ns_STR_._pr_function = function _pr_function(x) {
    return "<fn" + (x.name ? " " + x.name : "") + ">";
  };
  var _pr_pairs = _STR_ns_STR_._pr_pairs = function _pr_pairs(ps, f) {
    return _map(function(__destructure___GEN_10) {
      var __destructure___GEN_11 = __destructure___GEN_10;
      var k = __destructure___GEN_11[0];
      var v = __destructure___GEN_11[1];
      return f(k) + " " + f(v);
    }, ps);
  };
  var _pr_array = _STR_ns_STR_._pr_array = function _pr_array(x, f) {
    return "(jary" + _cjoin(" ", _map(f, x)) + ")";
  };
  var _pr_object = _STR_ns_STR_._pr_object = function _pr_object(x, f) {
    return "(jobj" + _cjoin(", ", _pr_pairs(U.pairs(x), f)) + ")";
  };
  var _pr_value = _STR_ns_STR_._pr_value = function _pr_value(x, pr_QMK_, seen) {
    return function() {
      var f = function(__lambda__27__) {
        return _pr_value(__lambda__27__, true, seen.concat([ x ]));
      };
      return undefined_QMK_(x) ? _pr_undefined(pr_QMK_) : nil_QMK_(x) ? _pr_nil(pr_QMK_) : boolean_QMK_(x) ? _pr_boolean(x) : number_QMK_(x) ? _pr_number(x) : string_QMK_(x) ? _pr_string(x, pr_QMK_) : regexp_QMK_(x) ? _pr_regexp(x) : date_QMK_(x) ? _pr_date(x, pr_QMK_) : error_QMK_(x) ? _pr_error(x, pr_QMK_) : function_QMK_(x) ? _pr_function(x) : array_QMK_(x) || U.isArguments(x) ? _pr_array(x, f) : _GTS__EQS_(seen.indexOf(x), 0) ? "<circular>" : pr_QMK_ && function_QMK_(x.inspect) ? x.inspect() : function_QMK_(x.toString) && not_EQS_(x.toString, Object.prototype.toString) ? pr_QMK_ ? _brckt(obj_MIN_to_MIN_string(x) + " " + _pr_string(x.toString(), true)) : x.toString() : _MIN_else ? _pr_object(x, f) : null;
    }();
  };
  var _pr_MIN_str = _STR_ns_STR_._pr_MIN_str = function _pr_MIN_str(x) {
    return _pr_value(x, true, []);
  };
  var _str = _STR_ns_STR_._str = function _str(x) {
    return _pr_value(x, false, []);
  };
  var pr_MIN_str = _STR_ns_STR_.pr_MIN_str = function pr_MIN_str() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return xs.length ? _red(function(__lambda__28__, __lambda__29__) {
      return __lambda__28__ + " " + __lambda__29__;
    }, _map(_pr_MIN_str, xs)) : "";
  };
  var str = _STR_ns_STR_.str = function str() {
    var xs = Array.prototype.slice.call(arguments, 0);
    return _red(function(__lambda__30__, __lambda__31__) {
      return __lambda__30__ + __lambda__31__;
    }, "", _map(_str, xs));
  };
}).call(this);

(function() {
  var _STR_exports_STR_ = typeof exports === "undefined" ? null : exports;
  var _STR_root_STR_ = _STR_exports_STR_ === null ? window : global;
  var _STR_ns_STR_ = {};
  undefined;
  var PI_PLS_ = _STR_ns_STR_.PI_PLS_ = 3.14159265;
  console.log({
    foo_MIN_: 99,
    bar: -1,
    baz: "!"
  });
  console.log([ PI_PLS_, 42, 37, "hi!" ]);
  console.log(1 === 1 ? "foo" : "bar");
  console.log(function() {
    var n_PLS_ = 10;
    var m = 3 * 3;
    return m + n_PLS_ + PI_PLS_;
  }());
  var obj_MIN_ = _STR_ns_STR_.obj_MIN_ = {
    foo: [ 1, {
      a_MIN_: {
        d: 42
      },
      b: {
        c_MIN_: 37
      }
    } ],
    bar_MIN_: function(a, b_MIN_, c) {
      return PI_PLS_ + a + b_MIN_ + c;
    }
  };
  (function() {
    var my_MIN_obj = 3735928559;
    return console.log(my_MIN_obj);
  })();
  console.log(obj_MIN_["foo"][1].a_MIN_.d);
  console.log(obj_MIN_["foo"][1].b.c_MIN_);
  console.log(obj_MIN_.bar_MIN_(1, 2, 3));
  console.log(obj_MIN_.bar_MIN_(4, 5, 6));
  [ 1, 2, 3 ].map(function(x_MIN_) {
    return [ "foo", "bar" ].map(function(y_MIN_) {
      return console.log("...", x_MIN_, y_MIN_);
    });
  });
  var foo_STR_ = _STR_ns_STR_.foo_STR_ = function foo_STR_(x, y_PLS_) {
    return console.log("x=", x, "y=", y_PLS_);
  };
  foo_STR_("hi", 1337);
  (function() {
    var __if_let___GEN_1 = 1 * 10;
    return __if_let___GEN_1 ? function() {
      var z_MIN_ = __if_let___GEN_1;
      return console.log("z=", z_MIN_);
    }() : console.log("false");
  })();
  var f = _STR_ns_STR_.f = function(__lambda__1__, __lambda__2__) {
    return __lambda__2__ - __lambda__1__;
  };
  var g = _STR_ns_STR_.g = function(__lambda__3__, __lambda__4__) {
    return __lambda__4__ + __lambda__3__;
  };
  console.log(f(3, g(11, 2)));
  var multi = _STR_ns_STR_.multi = function multi(__overloads___GEN_3, __variadic___GEN_4) {
    return function() {
      for (var __i___GEN_2 in __overloads___GEN_3) {
        if (__overloads___GEN_3[__i___GEN_2].length == arguments.length) {
          return __overloads___GEN_3[__i___GEN_2].apply(null, arguments);
        }
      }
      if (__variadic___GEN_4 && __variadic___GEN_4.length <= arguments.length) {
        return __variadic___GEN_4.apply(null, arguments);
      } else {
        throw new Error("Wrong number of args.");
      }
    };
  }([ function multi() {
    return "zero";
  }, function multi(x, y) {
    return "two";
  } ], function multi(x, y) {
    var rest = Array.prototype.slice.call(arguments, 2);
    return [ x, y, rest ];
  });
  console.log(multi(1, 2), multi(), multi(1, 2, 3, 4));
  var seq_PRM_ = _STR_ns_STR_.seq_PRM_ = function seq_PRM_(xs) {
    return xs.length;
  };
  var first_PRM_ = _STR_ns_STR_.first_PRM_ = function first_PRM_(xs) {
    return xs[0];
  };
  var rest_PRM_ = _STR_ns_STR_.rest_PRM_ = function rest_PRM_(xs) {
    return xs.slice(1);
  };
  var reduce_PRM_ = _STR_ns_STR_.reduce_PRM_ = function reduce_PRM_(f, z, xs) {
    return function(__arguments___GEN_5, __continue___GEN_6) {
      var recur = function() {
        __arguments___GEN_5 = Array.prototype.slice.call(arguments);
        return __continue___GEN_6;
      };
      while (true) {
        var z = __arguments___GEN_5[0];
        var xs = __arguments___GEN_5[1];
        __result___GEN_7 = seq_PRM_(xs) ? recur(f(z, first_PRM_(xs)), rest_PRM_(xs)) : z;
        if (__result___GEN_7 !== __continue___GEN_6) {
          return __result___GEN_7;
        }
      }
    }([ z, xs ], {});
  };
  console.log(reduce_PRM_(function(__lambda__5__, __lambda__6__) {
    return __lambda__5__ + __lambda__6__;
  }, 5, [ 1, 2, 3 ]));
  var _STR_root_STR_ = _STR_ns_STR_._STR_root_STR_ = {
    _STR_fnjs_STR_: {
      core: {
        nth: function(x, i) {
          return x[i];
        },
        get: function(x, i) {
          return x[i];
        }
      }
    }
  };
  var obj = _STR_ns_STR_.obj = [ "MR. X", 666, {
    ab: [ "A", "B" ],
    c: "C"
  } ];
  (function() {
    var bar = obj;
    var x = _STR_root_STR_._STR_fnjs_STR_.core.nth(bar, 0);
    var _ = _STR_root_STR_._STR_fnjs_STR_.core.nth(bar, 1);
    var qux = _STR_root_STR_._STR_fnjs_STR_.core.nth(bar, 2);
    var __destructure___GEN_15 = _STR_root_STR_._STR_fnjs_STR_.core.get(qux, "ab");
    var a = _STR_root_STR_._STR_fnjs_STR_.core.nth(__destructure___GEN_15, 0);
    var b = _STR_root_STR_._STR_fnjs_STR_.core.nth(__destructure___GEN_15, 1);
    var c = _STR_root_STR_._STR_fnjs_STR_.core.get(qux, "c");
    return console.log(x, a, b, c, bar, qux);
  })();
  (function() {
    var bar = obj;
    var x = bar[0];
    var _ = bar[1];
    var qux = bar[2];
    var c = qux["c"];
    var __destructure___GEN_16 = qux["ab"];
    var a = __destructure___GEN_16[0];
    var b = __destructure___GEN_16[1];
    return console.log(x, a, b, c, bar, qux);
  })();
  (function() {
    var __destructure___GEN_8 = {
      a: 1,
      b: 2,
      c: 3
    };
    var a = __destructure___GEN_8["a"];
    var c = __destructure___GEN_8["c"];
    var b = __destructure___GEN_8["b"];
    return console.log(a, b, c);
  })();
  (function() {
    var __destructure___GEN_9 = [ 1, 2, 3, 4, 5, 6 ];
    var xs = __destructure___GEN_9.slice(2);
    var x = __destructure___GEN_9[0];
    var y = __destructure___GEN_9[1];
    return console.log(x, y, xs);
  })();
  var foo = _STR_ns_STR_.foo = function foo(__destructure___GEN_10) {
    var xs = Array.prototype.slice.call(arguments, 1);
    var __destructure___GEN_11 = __destructure___GEN_10;
    var x = __destructure___GEN_11[0];
    var y = __destructure___GEN_11[1];
    return console.log(x, y, xs);
  };
  foo([ 7, 8, 9 ], 10, 11, 12, 13, 14);
  (function(__arguments___GEN_12, __continue___GEN_13) {
    var recur = function() {
      __arguments___GEN_12 = Array.prototype.slice.call(arguments);
      return __continue___GEN_13;
    };
    while (true) {
      var __destructure___GEN_17 = __arguments___GEN_12[0];
      var a = __destructure___GEN_17["a"];
      var c = __destructure___GEN_17["c"];
      var b = __destructure___GEN_17["b"];
      var d = __destructure___GEN_17["d"];
      __result___GEN_14 = a === b ? console.log("DONE", a, b, c, d) : function() {
        console.log("MORE", a, b, c, d);
        return recur({
          a: a + c,
          b: b + d,
          c: c,
          d: d
        });
      }();
      if (__result___GEN_14 !== __continue___GEN_13) {
        return __result___GEN_14;
      }
    }
  })([ {
    a: 0,
    b: 2,
    c: 2,
    d: 1
  } ], {});
  (function() {
    try {
      return function() {
        return function() {
          throw new Error("E1");
        }();
      }();
    } catch (e) {
      return function() {
        return console.log("E1:", e);
      }();
    }
  })();
  (function() {
    try {
      return function() {
        return console.log("OK");
      }();
    } finally {
      return function() {
        return console.log("F1");
      }();
    }
  })();
  (function() {
    try {
      return function() {
        return function() {
          throw new Error("E2");
        }();
      }();
    } catch (e) {
      return function() {
        return console.log("E2:", e);
      }();
    } finally {
      return function() {
        return console.log("F2");
      }();
    }
  })();
  var x = _STR_ns_STR_.x = null;
}).call(this);

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
  var foo_STR_ = _STR_ns_STR_.foo_STR_ = function(x, y_PLS_) {
    return console.log("x=", x, "y=", y_PLS_);
  };
  foo_STR_("hi", 1337);
  (function() {
    var temp__319__auto__ = 1 * 10;
    return temp__319__auto__ ? function() {
      var z_MIN_ = temp__319__auto__;
      return console.log("z=", z_MIN_);
    }() : console.log("false");
  })();
  var f = _STR_ns_STR_.f = function(p1__2_HSH_, p2__1_HSH_) {
    return p2__1_HSH_ - p1__2_HSH_;
  };
  var g = _STR_ns_STR_.g = function(p1__4_HSH_, p2__3_HSH_) {
    return p2__3_HSH_ + p1__4_HSH_;
  };
  console.log(f(3, g(11, 2)));
  var multi = _STR_ns_STR_.multi = function(__overloads__8, __variadic__9) {
    return function() {
      for (var __i__7 in __overloads__8) {
        if (__overloads__8[__i__7].length == arguments.length) {
          return __overloads__8[__i__7].apply(null, arguments);
        }
      }
      if (__variadic__9 && __variadic__9.length <= arguments.length) {
        return __variadic__9.apply(null, arguments);
      } else {
        throw new Error("Wrong number of args.");
      }
    };
  }([ function() {
    return "zero";
  }, function(x, y) {
    return "two";
  } ], function(x, y) {
    var rest = Array.prototype.slice.call(arguments, 2);
    return [ x, y, rest ];
  });
  console.log(multi(1, 2), multi(), multi(1, 2, 3, 4));
  var seq_PRM_ = _STR_ns_STR_.seq_PRM_ = function(xs) {
    return xs.length;
  };
  var first_PRM_ = _STR_ns_STR_.first_PRM_ = function(xs) {
    return xs[0];
  };
  var rest_PRM_ = _STR_ns_STR_.rest_PRM_ = function(xs) {
    return xs.slice(1);
  };
  var reduce_PRM_ = _STR_ns_STR_.reduce_PRM_ = function(f, z, xs) {
    return function(__arguments__10, __continue__11) {
      var recur = function() {
        __arguments__10 = Array.prototype.slice.call(arguments);
        return __continue__11;
      };
      while (true) {
        var z = __arguments__10[0];
        var xs = __arguments__10[1];
        __result__12 = seq_PRM_(xs) ? recur(f(z, first_PRM_(xs)), rest_PRM_(xs)) : z;
        if (__result__12 !== __continue__11) {
          return __result__12;
        }
      }
    }([ z, xs ], {});
  };
  console.log(reduce_PRM_(function(p1__5_HSH_, p2__6_HSH_) {
    return p1__5_HSH_ + p2__6_HSH_;
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
    var __destructure__20 = _STR_root_STR_._STR_fnjs_STR_.core.get(qux, "ab");
    var a = _STR_root_STR_._STR_fnjs_STR_.core.nth(__destructure__20, 0);
    var b = _STR_root_STR_._STR_fnjs_STR_.core.nth(__destructure__20, 1);
    var c = _STR_root_STR_._STR_fnjs_STR_.core.get(qux, "c");
    return console.log(x, a, b, c, bar, qux);
  })();
  (function() {
    var bar = obj;
    var x = bar[0];
    var _ = bar[1];
    var qux = bar[2];
    var c = qux["c"];
    var __destructure__21 = qux["ab"];
    var a = __destructure__21[0];
    var b = __destructure__21[1];
    return console.log(x, a, b, c, bar, qux);
  })();
  (function() {
    var __destructure__13 = {
      a: 1,
      b: 2,
      c: 3
    };
    var a = __destructure__13["a"];
    var c = __destructure__13["c"];
    var b = __destructure__13["b"];
    return console.log(a, b, c);
  })();
  (function() {
    var __destructure__14 = [ 1, 2, 3, 4, 5, 6 ];
    var xs = __destructure__14.slice(2);
    var x = __destructure__14[0];
    var y = __destructure__14[1];
    return console.log(x, y, xs);
  })();
  var foo = _STR_ns_STR_.foo = function(__destructure__15) {
    var xs = Array.prototype.slice.call(arguments, 1);
    var __destructure__16 = __destructure__15;
    var x = __destructure__16[0];
    var y = __destructure__16[1];
    return console.log(x, y, xs);
  };
  foo([ 7, 8, 9 ], 10, 11, 12, 13, 14);
  (function(__arguments__17, __continue__18) {
    var recur = function() {
      __arguments__17 = Array.prototype.slice.call(arguments);
      return __continue__18;
    };
    while (true) {
      var __destructure__22 = __arguments__17[0];
      var a = __destructure__22["a"];
      var c = __destructure__22["c"];
      var b = __destructure__22["b"];
      var d = __destructure__22["d"];
      __result__19 = a === b ? console.log("DONE", a, b, c, d) : function() {
        console.log("MORE", a, b, c, d);
        return recur({
          a: a + c,
          b: b + d,
          c: c,
          d: d
        });
      }();
      if (__result__19 !== __continue__18) {
        return __result__19;
      }
    }
  })([ {
    a: 0,
    b: 2,
    c: 2,
    d: 1
  } ], {});
  var x = _STR_ns_STR_.x = _STR_root_STR_._STR_fnjs_STR_.nil;
}).call(this);

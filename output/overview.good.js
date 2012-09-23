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
  console.log("Hello, World");
  var PI = _STR_ns_STR_.PI = 3.14159265;
  var foo = _STR_ns_STR_.foo = function(x, y, z) {
    return x * (PI + y + z);
  };
  var show = _STR_ns_STR_.show = function() {
    return console.log.apply(console, Array.prototype.slice.call(arguments));
  };
  show(foo(1, 2, 3));
  show(1 + 2 + 3, 1 === 1, -42);
  var my_MIN_ary = _STR_ns_STR_.my_MIN_ary = [ 2, 3, 5, 7, 11, "..." ];
  var my_MIN_obj = _STR_ns_STR_.my_MIN_obj = {
    x: 1,
    y: 2,
    z: 3
  };
  (function() {
    show(my_MIN_ary);
    return show(my_MIN_obj);
  })();
  var my_MIN_obj_MIN_2 = _STR_ns_STR_.my_MIN_obj_MIN_2 = {
    x: {
      a: 1,
      b: [ 2, 3 ],
      c: 4
    },
    y: {
      d: 5,
      f: function(p1__1_HSH_) {
        return p1__1_HSH_ + 1;
      }
    }
  };
  show(my_MIN_obj_MIN_2.x.a, my_MIN_obj_MIN_2.x.c, my_MIN_obj_MIN_2.y.f(99), my_MIN_obj_MIN_2.y.f(512));
  show(my_MIN_obj_MIN_2.x["b"][1]);
  [ 1, 2, 3 ].map(function(x) {
    return [ "foo", "bar" ].map(function(y) {
      return show("x=", x, "y=", y);
    });
  });
  (function() {
    var PI = 42;
    var x = 37;
    return show(PI, x);
  })();
  show(1 === 1 ? "eq" : "ne");
  console.log("JS!");
  show(_STR_root_STR_._STR_fnjs_STR_.nil);
  (function() {
    var temp__323__auto__ = PI * PI;
    return temp__323__auto__ ? function() {
      var x = temp__323__auto__;
      return show("x=", x);
    }() : show("false");
  })();
}).call(this);

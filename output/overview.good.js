(function() {
  var _STR_exports_STR_ = typeof exports === "undefined" ? null : exports;
  var _STR_root_STR_ = exports === null ? window : global;
  var _STR_ns_STR_ = {};
  undefined;
  console.log("Hello, World");
  var PI = _STR_ns_STR_.PI = 3.14159265;
  var foo = _STR_ns_STR_.foo = function(x, y, z) {
    return x * (PI + y + z);
  };
  var show = _STR_ns_STR_.show = function show() {
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
      f: function(__lambda__1__) {
        return __lambda__1__ + 1;
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
  show(null);
  (function() {
    var __if_let___GEN_1 = PI * PI;
    return __if_let___GEN_1 ? function() {
      var x = __if_let___GEN_1;
      return show("x=", x);
    }() : show("false");
  })();
}).call(this);

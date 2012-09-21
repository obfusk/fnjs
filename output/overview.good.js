((function() {
  var _STR_root_STR_ = this;
  console.log("Hello, World");
  var PI = 3.14159265;
  var foo = function(x, y, z) {
    return x * (PI + y + z);
  };
  var show = function() {
    return console.log.apply(console, Array.prototype.slice.call(arguments));
  };
  show(foo(1, 2, 3));
  show(1 + 2 + 3, 1 === 1, -42);
  var my_MIN_ary = [ 2, 3, 5, 7, 11, "..." ];
  var my_MIN_obj = {
    x: 1,
    y: 2,
    z: 3
  };
  (function() {
    show(my_MIN_ary);
    return show(my_MIN_obj);
  })();
  var my_MIN_obj_MIN_2 = {
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
  show(null);
  (function() {
    var temp__198__auto__ = PI * PI;
    return temp__198__auto__ ? function() {
      var x = temp__198__auto__;
      return show("x=", x);
    }() : show("false");
  })();
})).call(this);

((function() {
  var _STR_root_STR_ = this;
  var _STR_ns_STR_ = {};
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
    var temp__210__auto__ = 1 * 10;
    return temp__210__auto__ ? function() {
      var z_MIN_ = temp__210__auto__;
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
  var x = _STR_ns_STR_.x = null;
})).call(this);

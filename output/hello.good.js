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
  var hello = _STR_ns_STR_.hello = function(x) {
    return console.log("Hello, " + x);
  };
  hello("World");
}).call(this);

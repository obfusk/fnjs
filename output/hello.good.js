((function() {
  var _STR_root_STR_ = this;
  var _STR_ns_STR_ = {};
  var hello = _STR_ns_STR_.hello = function(x) {
    return console.log("Hello, " + x);
  };
  hello("World");
})).call(this);

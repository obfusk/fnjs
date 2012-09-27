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
  undefined;
  var http = _STR_ns_STR_.http = require("http");
  var server = _STR_ns_STR_.server = http.createServer(function(req, res) {
    res.writeHead(200, {
      "Content-Type": "text/plain"
    });
    return function() {
      var d = new Date;
      console.log("...");
      return res.end("Hello, World!\n\n- fnjs @ " + d + "\n");
    }();
  });
  server.listen(3e3, "127.0.0.1");
  console.log("web server @ http://localhost:3000/ ...");
}).call(this);

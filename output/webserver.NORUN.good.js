(function() {
  var _STR_exports_STR_ = typeof exports === "undefined" ? null : exports;
  var _STR_root_STR_ = exports === null ? window : global;
  var _STR_ns_STR_ = {};
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

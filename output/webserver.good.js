(function() {
  var http = require("http");
  var server = http.createServer(function(req, res) {
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
})();

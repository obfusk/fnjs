; File        : examples/webserver.fnjs
; Date        : 2012-05-08
; Description :
;   Hello World web server; adapted from node example; requires node.

(def http (require "http"))
(def server (http.createServer
  (fn [req res]
    (res.writeHead 200 (jobj "Content-Type" "text/plain"))
    (let [ d (js "new Date ()") ]
      (console.log "...")
      (res.end (jbop + "Hello, World!\n\n- fnjs @ " d "\n")) ))))

(server.listen 3000 "127.0.0.1")
(console.log "web server @ http://localhost:3000/ ...")

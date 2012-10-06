// examples/fnjs.eval.node.NORUN.fnjs
(function() {
  var _STR_exports_STR_ = typeof exports === "undefined" ? null : exports;
  var _STR_root_STR_ = _STR_exports_STR_ === null ? window : global;
  var _STR_ns_STR_ = {};
  undefined;
  if (_STR_exports_STR_ === null) {
    (function(o, xs) {
      for (var x in xs) {
        o = o[xs[x]] = o[xs[x]] == null ? {} : o[xs[x]];
      }
    })(_STR_root_STR_, [ "_STR_fnjs_STR_", "namespaces", "fnjs", "eval", "node" ]);
    _STR_ns_STR_ = _STR_root_STR_._STR_fnjs_STR_.namespaces.fnjs.eval.node;
  } else {
    _STR_ns_STR_ = _STR_exports_STR_;
  }
  _STR_ns_STR_.__namespace__ = "fnjs.eval.node";
  if (_STR_exports_STR_ === null) {
    var C = _STR_root_STR_._STR_fnjs_STR_.namespaces.child_process;
  } else {
    var C = require("child_process");
  }
  if (_STR_exports_STR_ === null) {
    var V = _STR_root_STR_._STR_fnjs_STR_.namespaces.vm;
  } else {
    var V = require("vm");
  }
  if (_STR_exports_STR_ === null) {
    var F = _STR_root_STR_._STR_fnjs_STR_.namespaces.fnjs.core;
  } else {
    var F = require("fnjs.core");
  }
  var create_MIN_context = _STR_ns_STR_.create_MIN_context = function create_MIN_context() {
    return function() {
      var c = V.createContext();
      for (var i in global) {
        c[i] = global[i];
      }
      c.global = c;
      c.global.global = c;
      c.module = module;
      c.require = require;
      return c;
    }();
  };
  var show_MIN_stderr = _STR_ns_STR_.show_MIN_stderr = function show_MIN_stderr(data) {
    return process.stderr.write(data);
  };
  var show_MIN_exit = _STR_ns_STR_.show_MIN_exit = function show_MIN_exit(code, signal) {
    return process.stderr.write(F.str("[fnjs exited w/ code ", code, ", signal ", signal || "NONE", "]\n"));
  };
  var run = _STR_ns_STR_.run = function run(code, c, f) {
    return function() {
      try {
        return function() {
          return [ null, c ? V.runInContext(code, c, f) : V.runInThisContext(code, f) ];
        }();
      } catch (e) {
        return function() {
          return [ e, null ];
        }();
      }
    }();
  };
  var on_MIN_stdout = _STR_ns_STR_.on_MIN_stdout = function on_MIN_stdout(stack) {
    return function on_MIN_stdout_PRM_(data) {
      return function() {
        var top = stack.pop();
        var c = top.context;
        var f = top.file;
        return F.rx("^OK ").test(data) ? function() {
          var code = data.slice(3);
          return F.apply(top.cb, code, null, run(code, c, f));
        }() : F.rx("^ERROR ").test(data) ? function() {
          var code = data.slice(6);
          return top.cb(null, V.runInNewContext(code), null, null);
        }() : F._MIN_else ? function() {
          throw new Error("fnjs stdout: not OK or ERROR");
        }() : null;
      }();
    };
  };
  var on_MIN_eval = _STR_ns_STR_.on_MIN_eval = function on_MIN_eval(fnjs, stack) {
    return function eval(code, opts) {
      return function() {
        var __destructure___GEN_1 = opts || {};
        var file = __destructure___GEN_1["file"];
        var context = __destructure___GEN_1["context"];
        var cb = __destructure___GEN_1["cb"];
        F._GTS__EQS_(code.indexOf("\n"), 0) ? function() {
          return function() {
            throw new Error("fnjs eval: code contains newline(s)");
          }();
        }() : null;
        stack.unshift({
          context: context,
          file: file,
          cb: cb
        });
        return fnjs.stdin.write(F.str(code, "\n"));
      }();
    };
  };
  var start = _STR_ns_STR_.start = function start(opts) {
    return function() {
      var __destructure___GEN_2 = opts || {};
      var stderr = __destructure___GEN_2["stderr"];
      var exit = __destructure___GEN_2["exit"];
      var fnjs = C.spawn(F.str(process.env.FNJS_HOME || ".", "/bin/fnjs"), [ ":repl" ]);
      var stack = [];
      fnjs.stdout.on("data", on_MIN_stdout(stack));
      fnjs.stderr.on("data", stderr || show_MIN_stderr);
      fnjs.on("exit", exit || show_MIN_exit);
      return on_MIN_eval(fnjs, stack);
    }();
  };
}).call(this);

(function() {
  var _STR_exports_STR_ = typeof exports === "undefined" ? null : exports;
  var _STR_root_STR_ = exports === null ? window : global;
  var _STR_ns_STR_ = {};
  (function(o, xs) {
    for (var x in xs) {
      o = o[xs[x]] = o[xs[x]] == null ? {} : o[xs[x]];
    }
  })(_STR_root_STR_, [ "_STR_fnjs_STR_" ]);
  _STR_root_STR_._STR_fnjs_STR_.nil = _STR_root_STR_._STR_fnjs_STR_.nil || new function NIL() {
    this.nil_QMK_ = true;
  };
  undefined;
  if (_STR_exports_STR_ === null) {
    (function(o, xs) {
      for (var x in xs) {
        o = o[xs[x]] = o[xs[x]] == null ? {} : o[xs[x]];
      }
    })(_STR_root_STR_, [ "_STR_fnjs_STR_", "namespaces", "fnjs", "repl" ]);
    _STR_ns_STR_ = _STR_root_STR_._STR_fnjs_STR_.namespaces.fnjs.repl;
  } else {
    _STR_ns_STR_ = _STR_exports_STR_;
  }
  _STR_ns_STR_.__namespace__ = "fnjs.repl";
  if (_STR_exports_STR_ === null) {
    var C = _STR_root_STR_._STR_fnjs_STR_.namespaces.child_process;
  } else {
    var C = require("child_process");
  }
  if (_STR_exports_STR_ === null) {
    var R = _STR_root_STR_._STR_fnjs_STR_.namespaces.repl;
  } else {
    var R = require("repl");
  }
  if (_STR_exports_STR_ === null) {
    var V = _STR_root_STR_._STR_fnjs_STR_.namespaces.vm;
  } else {
    var V = require("vm");
  }
  var VERSION = _STR_ns_STR_.VERSION = "0.1.2-dev";
  var _data_ = _STR_ns_STR_._data_ = {
    init: false,
    repl: null,
    eval: {
      file: "repl",
      cb: function() {
        return null;
      }
    }
  };
  global.exports = module.exports;
  global.module = module;
  global.require = require;
  var eval_MIN_1 = _STR_ns_STR_.eval_MIN_1 = function eval_MIN_1(code, context, file, cb) {
    return function() {
      var code_PRM_ = cb.name !== "finish" && /^\(/.test(code) && /\)$/.test(code) ? code.slice(1, -1) : code;
      _data_.eval = {
        file: file,
        cb: cb
      };
      return fnjs.stdin.write(code_PRM_);
    }();
  };
  var eval_MIN_2 = _STR_ns_STR_.eval_MIN_2 = function eval_MIN_2(code, d) {
    return function() {
      var __destructure___GEN_1 = function() {
        try {
          return function() {
            return [ null, V.runInThisContext(code, d.file) ];
          }();
        } catch (e) {
          return function() {
            return [ e, null ];
          }();
        }
      }();
      var err = __destructure___GEN_1[0];
      var res = __destructure___GEN_1[1];
      global._ = res;
      return err ? function() {
        process.stdout.write((err.stack || err) + "\n");
        return d.cb(null, undefined);
      }() : d.cb(null, res);
    }();
  };
  var fnjs = _STR_ns_STR_.fnjs = C.spawn(process.env.FNJS_HOME + "/bin/fnjs", [ ":repl" ]);
  var start = _STR_ns_STR_.start = function start() {
    _data_.repl = R.start({
      prompt: "fnjs> ",
      terminal: false,
      eval: eval_MIN_1
    });
    return _data_.repl.on("exit", function() {
      return process.exit();
    });
  };
  fnjs.stdout.on("data", function(data) {
    eval_MIN_2(data, _data_.eval);
    return !_data_.init ? function() {
      _data_.init = true;
      return start();
    }() : null;
  });
  fnjs.stderr.on("data", function(data) {
    return process.stderr.write(data);
  });
  fnjs.on("exit", function(code, signal) {
    process.stderr.write("[fnjs exited w/ code " + code + ", signal " + signal + "]\n");
    return process.exit();
  });
}).call(this);

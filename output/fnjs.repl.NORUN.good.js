// examples/fnjs.repl.NORUN.fnjs
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
  if (_STR_exports_STR_ === null) {
    var F = _STR_root_STR_._STR_fnjs_STR_.namespaces.fnjs.core;
  } else {
    var F = require("fnjs.core");
  }
  var VERSION = _STR_ns_STR_.VERSION = "0.2.0-dev";
  var _data_ = _STR_ns_STR_._data_ = {
    init: false,
    count: 0,
    fnjs: null,
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
  global.DEBUG = false;
  global.F = F;
  var eval_MIN_1 = _STR_ns_STR_.eval_MIN_1 = function eval_MIN_1(code, context, file, cb) {
    return function() {
      var code_PRM_ = F.not_EQS_(cb.name, "finish") && F.rx("^\\(").test(code) && F.rx("\\)$").test(code) ? code.slice(1, -1) : code;
      _data_.eval = {
        file: file,
        cb: cb
      };
      return _data_.fnjs.stdin.write(code_PRM_);
    }();
  };
  var eval_MIN_2 = _STR_ns_STR_.eval_MIN_2 = function eval_MIN_2(code, d) {
    DEBUG ? function() {
      return console.log(F.str("[ --> ", F.str(code).replace(F.rx("\\n", "g"), " "), " <-- ]"));
    }() : null;
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
        process.stdout.write(F.str(err.stack || err, "\n"));
        return d.cb(null, undefined);
      }() : d.cb(null, res);
    }();
  };
  var start = _STR_ns_STR_.start = function start() {
    F._EQS_(_data_.count, 0) ? function() {
      process.stdout.write(F.str("fnjs v", VERSION, "\nF is fnjs.core.\n"));
      return function() {
        var repl = R.start({
          prompt: "fnjs> ",
          terminal: false,
          writer: F.pr_MIN_str,
          eval: eval_MIN_1
        });
        return repl.on("exit", function() {
          return process.exit();
        });
      }();
    }() : null;
    return _data_.count = F.inc(_data_.count);
  };
  var start_MIN_fnjs = _STR_ns_STR_.start_MIN_fnjs = function start_MIN_fnjs() {
    _data_.fnjs = C.spawn(F.str(process.env.FNJS_HOME, "/bin/fnjs"), [ ":repl" ]);
    _data_.fnjs.stdout.on("data", function(data) {
      eval_MIN_2(_data_.init || F._EQS_(_data_.count, 0) ? data : "undefined", _data_.eval);
      return F.not(_data_.init) ? function() {
        _data_.init = true;
        return start();
      }() : null;
    });
    _data_.fnjs.stderr.on("data", function(data) {
      return process.stderr.write(data);
    });
    return _data_.fnjs.on("exit", function(code, signal) {
      process.stderr.write(F.str("[fnjs exited w/ code ", code, ", signal ", signal, "; respawning ...]\n"));
      _data_.init = false;
      return start_MIN_fnjs();
    });
  };
  start_MIN_fnjs();
}).call(this);

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
    var R = _STR_root_STR_._STR_fnjs_STR_.namespaces.repl;
  } else {
    var R = require("repl");
  }
  if (_STR_exports_STR_ === null) {
    var F = _STR_root_STR_._STR_fnjs_STR_.namespaces.fnjs.core;
  } else {
    var F = require("fnjs.core");
  }
  if (_STR_exports_STR_ === null) {
    var E = _STR_root_STR_._STR_fnjs_STR_.namespaces.fnjs.eval.node;
  } else {
    var E = require("fnjs.eval.node");
  }
  var DEBUG = _STR_ns_STR_.DEBUG = F._EQS_(process.env.FNJS_DEBUG, "yes");
  var show_MIN_err = _STR_ns_STR_.show_MIN_err = function show_MIN_err(e) {
    return process.stdout.write(F.str(e.stack || e, "\n"));
  };
  var fnjs_MIN_exit = _STR_ns_STR_.fnjs_MIN_exit = function fnjs_MIN_exit(c, s) {
    E.show_MIN_exit(c, s);
    return process.exit();
  };
  var repl_MIN_exit = _STR_ns_STR_.repl_MIN_exit = function repl_MIN_exit() {
    return process.exit();
  };
  var patch = _STR_ns_STR_.patch = function patch(code) {
    return code.slice(1, -1);
  };
  var fnjs_MIN_eval = _STR_ns_STR_.fnjs_MIN_eval = function fnjs_MIN_eval(fnjs, code, f) {
    return fnjs(code, {
      file: "repl",
      cb: f
    });
  };
  var repl_MIN_eval = _STR_ns_STR_.repl_MIN_eval = function repl_MIN_eval(fnjs) {
    return function(code, _, _, cb) {
      return fnjs_MIN_eval(fnjs, patch(code).replace(F.rx("\\n$"), ""), function(c, fe, ee, x) {
        global.DEBUG ? function() {
          return console.log(F.str("[ --> ", F.str(c).replace(F.rx("\\n", "g"), " "), "<-- ]"));
        }() : null;
        return fe || ee ? function() {
          show_MIN_err(fe ? F.str("[fnjs error] ", fe.msg) : ee);
          return cb(null, undefined);
        }() : cb(null, x);
      });
    };
  };
  var start_MIN_repl = _STR_ns_STR_.start_MIN_repl = function start_MIN_repl(fnjs) {
    return function(_, fe, ee, _) {
      fe || ee ? function() {
        return function() {
          throw new Error(F.str("fnjs init error: ", fe ? fe.msg : ee));
        }();
      }() : null;
      return function() {
        var repl = R.start({
          prompt: "fnjs> ",
          terminal: false,
          writer: F.pr_MIN_str,
          eval: repl_MIN_eval(fnjs)
        });
        return repl.on("exit", repl_MIN_exit);
      }();
    };
  };
  var start_MIN_fnjs = _STR_ns_STR_.start_MIN_fnjs = function start_MIN_fnjs() {
    process.stdout.write(F.str("fnjs v", F.VERSION, "\nfnjs.core is available as F.\n"));
    return function() {
      var fnjs = E.start({
        exit: fnjs_MIN_exit
      });
      global.exports = module.exports;
      global.DEBUG = DEBUG;
      global.F = F;
      return fnjs_MIN_eval(fnjs, "(__FNJS_INIT__)", start_MIN_repl(fnjs));
    }();
  };
  start_MIN_fnjs();
}).call(this);

//  --                                                            {{{1
//
//  File        : repl.js
//  Maintainer  : Felix C. Stegerman <flx@obfusk.net>
//  Date        : 2012-09-26
//
//  Copyright   : Copyright (C) 2012  Felix C. Stegerman
//  Licence     : GPLv2 or EPLv1
//
//  Depends     : ...
//  Description : ...
//
//  TODO        : ...
//
//  --                                                            }}}1

// TODO: review !!!

// --

var cp      = require ('child_process'),
    repl_m  = require ('repl'),
    vm      = require ('vm');

// --

var repl, fnjs, fnjs_cmd = process.env.FNJS || 'fnjs';

// --

var eval_1 = function (code, context, file, cb) {             //  {{{1
  // console.log ('--> eval ...');

  // !!! DEPENDS ON IMPLEMENTATION !!!                        //  !!!!
  if ( cb.name !== 'finish' && /^\(/.test (code)
                            && /\)$/.test (code) ) {
    // console.log ('--> monkey patching');
    code = code.slice (1, -1);
  }

  eval_data = { file: file, cb: cb };
  fnjs.stdin.write (code);
}                                                             //  }}}1

var eval_2 = function (code, d) {                             //  {{{1
  var err, res;

  try {
    res = vm.runInThisContext (code, d.file);
  } catch (e) {
    err = e;
  }

  //  try {
  //    var ns = vm.runInThisContext (
  //      '_STR_ns_STR_.__namespace__', d.file
  //    );
  //    if (ns) { repl.prompt = ns + '=> '; }                 //  !!!!
  //  } catch (e) {}

  // console.log ({ code: '' + code, res: res, err: err });
  // console.log ('--> callback ...');

  if (err) {
    process.stdout.write ((err.stack || err) + '\n');
    d.cb (null, undefined);
  } else {
    d.cb (null /* err */, res);
  }
}                                                             //  }}}1

// --

var init = false, eval_data = { file: 'repl', cb: function () {} };

fnjs = cp.spawn (fnjs_cmd, [':repl']);

var start = function () {                                     //  {{{1
  repl = repl_m.start ({
    prompt: 'fnjs> ', terminal: false, eval: eval_1
  });

  repl.on ('exit', function () {
    // process.stderr.write ('bye.');
    process.exit ();
  });
}                                                             //  }}}1

fnjs.stdout.on ('data', function (data) {
  eval_2 (data, eval_data);
  if (! init) { init = true; start (); }
});

fnjs.stderr.on ('data', function (data) {
  process.stderr.write (data);
});

fnjs.on ('exit', function (code, signal) {                    //  {{{1
  process.stderr.write (
    '[fnjs exited w/ code ' + code + ', signal ' + signal + ']\n'
  );
  process.exit ();
});                                                           //  }}}1

// vim: set tw=70 sw=2 sts=2 et fdm=marker :

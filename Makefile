# --

vsn    := $(shell cat _vsn)
PREFIX ?= /usr/local

# --

fnjs   := $(wildcard src-fnjs/*.fnjs.clj)
tree   := $(fnjs:src-fnjs/%=tree/lib/fnjs/js/%)
libs   := $(tree:.fnjs.clj=.js)

# --

.PHONY: all jar libs test install clean archive

all: jar libs

jar: tree/lib/fnjs/jar/fnjs-$(vsn)-standalone.jar

libs: jar $(libs)

tree/lib/fnjs/jar/fnjs-$(vsn)-standalone.jar: src/fnjs/*.clj
	./_scripts/build

tree/lib/fnjs/js/%.js: src-fnjs/%.fnjs.clj
	./_scripts/build-libs $(?:.clj=)

test: all
	./_scripts/test-js -q && ./_scripts/test-out -q

install: all
	cp -v -R tree/ $(PREFIX)/

clean:
	./_scripts/clean
	./_scripts/clean-output

archive: all
	./_scripts/mk-archive

# --

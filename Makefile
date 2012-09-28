# --

vsn    := $(shell cat _vsn)
PREFIX ?= /usr/local

# --

.PHONY: all test install clean archive

all: tree/lib/fnjs/jar/fnjs-$(vsn)-standalone.jar
	./_scripts/build-libs

tree/lib/fnjs/jar/fnjs-$(vsn)-standalone.jar: src/fnjs/*.clj
	./_scripts/build

test:
	./_scripts/test-js -q && ./_scripts/test-out -q

install: all
	cp -v -R tree/ $(PREFIX)/

clean:
	./_scripts/clean
	./_scripts/clean-output

archive: all
	./_scripts/mk-archive

# --

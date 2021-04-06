#!/usr/bin/env bash

[ ! -d jaxws-ri ] && unzip resources/jaxws-ri-3.0.0.zip

# Generate Java classes from WSDL.
make wsimport

# Compile Java classes from wsimport + their implementation.
make java

# Run REPL.
clojure -A:dev:nrepl

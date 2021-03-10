#!/bin/bash

JENETICS_VERSION=5.2.0
JENETICS_URL=https://github.com/jenetics/jenetics/archive/v${JENETICS_VERSION}.zip

function get_jenetics() {
    mkdir lib;
    mkdir lib/jenetics-temp;
    pushd lib/jenetics-temp;
    wget "$JENETICS_URL";
    unzip v${JENETICS_VERSION}.zip;
    cd jenetics-${JENETICS_VERSION} && \
    ./gradlew jar && \
    cd ..;
    false | cp -i `find . -name '*.jar'` ../ 2>/dev/null;
    cd ../..;
    rm -rf lib/jenetics-temp;
}

function collect_libs() {
    PATH_SUFFIX=""
    for LIB in lib/*.jar; do
        PATH_SUFFIX="$PATH_SUFFIX:$LIB"
    done
    printf "$PATH_SUFFIX"
}

function run() {
    cp -r results results.bak;
    cp -r the-one the-one.bak;
    
    pushd the-one && ./compile.sh && popd;
    get_jenetics;
    PATH_SUFFIX=`collect_libs` && mkdir classes;
    "$JAVA_HOME/bin/javac" -cp ".:$PATH_SUFFIX" -sourcepath src -d classes src/*.java;
    "$JAVA_HOME/bin/java" -cp "classes:$PATH_SUFFIX" RoutingProtocol && popd
}

run

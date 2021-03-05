#!/bin/bash

CALLED_JAVA_HOME=/usr
JENETICS_VERSION=5.2.0
JENETICS_URL=https://github.com/jenetics/jenetics/archive/v${JENETICS_VERSION}.zip

function get_jenetics() {
    mkdir lib && \
    mkdir lib/jenetics-temp && \
    pushd lib/jenetics-temp && \
    wget "$JENETICS_URL" && \
    unzip v${JENETICS_VERSION}.zip && \
    cd jenetics-${JENETICS_VERSION} && \
    ./gradlew build && \
    mv `find . -name *.jar` ../../ && \
    cd ../../../ && \
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
    OLD_JAVA_HOME="$JAVA_HOME"
    export JAVA_HOME="$CALLED_JAVA_HOME"

    cp -r results results.bak;
    cp -r the-one the-one.bak;
    
    pushd the-one && ./compile.sh && popd;
    get_jenetics;
    PATH_SUFFIX=`collect_libs` && mkdir classes;
    "$JAVA_HOME/bin/javac" -cp ".:$PATH_SUFFIX" -sourcepath src -d classes src/*.java;
    "$JAVA_HOME/bin/java" -cp "classes:$PATH_SUFFIX" RoutingProtocol && popd
    
    rm -rf the-one
    mv the-one.bak the-one;

    export JAVA_HOME="$OLD_JAVA_HOME"
}

run

#!/usr/bin/env bash
# exit whenever encounter errors
set -e
# print commands  before execute (for debug) set+x
set -x

./script/build-script/build-before.sh

#gradle clean build kylinTaskDockerImageBuild -x test -Pprofile=release

./gradlew clean build  -x test -Pprofile=release

./script/build-script/build-after.sh

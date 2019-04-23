#!/usr/bin/env bash

usage() {
    echo "Usage:"
    echo "build_local.sh"
}

if [[ $1 == "-h" ]]; then
  usage
  exit 0
fi

if [[ -z $MAVEN_LOCAL ]]; then
  echo "MAVEN_LOCAL must be set as an environment variable"
  usage
  exit 1
fi

echo $MAVEN_LOCAL

if [ ! -d ${MAVEN_LOCAL} ]; then
    mkdir ${MAVEN_LOCAL}
fi

mvn package
mvn deploy:deploy-file -DgroupId=com.bcreagh.javaalgos -DartifactId=java-algos -Dversion=1.0-SNAPSHOT \
    -Durl=file:$MAVEN_LOCAL -DrepositoryId=local-maven-repo -DupdateReleaseInfo=true \
    -Dfile="target/java-algos-1.0-SNAPSHOT.jar"
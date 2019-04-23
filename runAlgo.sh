#!/usr/bin/env bash

usage() {
    echo "Usage:"
    echo "runAlgo.sh <Class-name of algorithm>"
}

if [[ $1 == "-h" ]]; then
  usage
  exit 0
fi

if [[ -z $1 ]]; then
  echo "You must specify an algorithm to run!"
  usage
  exit 1
fi

mvn compile
java -cp "target/classes" com.bcreagh.javaalgos.Reverse
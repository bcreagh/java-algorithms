#!/usr/bin/env bash

# This runs the project simply using the java executable

mvn compile
java -cp "target/classes" com.bcreagh.javaalgos.Reverse
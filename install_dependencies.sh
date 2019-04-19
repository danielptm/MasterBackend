#!/bin/bash

if java -version | grep -q "java version" ; then
  sudo yum install java-1.8.0 -y
else
  echo "Java NOT installed!"
fi
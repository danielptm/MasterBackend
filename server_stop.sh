#!/usr/bin/env bash

if pgrep "java"
then
  echo "killing java"
  killall java
fi
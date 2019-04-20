#!/usr/bin/env bash

if pgrep "java"
then
    killall java
fi
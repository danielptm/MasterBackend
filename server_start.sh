#!/usr/bin/env bash
cd /home/ec2-user/server/api/target
kill -9 PID
java -jar application.jar -Dserver.port=80
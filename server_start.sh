#!/usr/bin/env bash
mv /home/ec2-user/server/api/target/application.jar /home/ec2-user/server/application.jar
kill -9 PID
java -jar application.jar -Dserver.port=80
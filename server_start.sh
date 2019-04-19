#!/usr/bin/env bash
mv /home/ec2-user/server/api/target/application.jar /home/ec2-user/server/application.jar
java -jar application.jar -Dserver.port=80
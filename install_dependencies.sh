#!/bin/bash

mv /home/ec2-user/server/api/target/application.jar /home/ec2-user/server/application.jar

if java -version | grep -q "java version" ; then
  sudo yum install java-1.8.0 -y
else
  echo "Java NOT installed!"
fi
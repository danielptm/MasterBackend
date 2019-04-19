#!/usr/bin/env bash
pkill -f 'java'
echo export GLOBATI_SERVER_ENV="production" > ~/.bash_profile
source ~/.bash_profile
mv /home/ec2-user/server/api/target/application.jar /home/ec2-user/server/application.jar
cd /home/ec2-user/server
java -jar application.jar -Dserver.port=22
#!/usr/bin/env bash

export GLOBATI_SERVER_ENV="production"
sudo source ~/.bash_profile
mv /home/ec2-user/server/api/target/application.jar /home/ec2-user/server/application.jar
cd /home/ec2-user/server
nohup java -jar application.jar > /dev/null &
exit 0
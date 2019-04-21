#!/usr/bin/env bash
echo export GLOBATI_SERVER_ENV="production" > ~/.bash_profile
sudo source ~/.bash_profile
mv /home/ec2-user/server/api/target/application.jar /home/ec2-user/server/application.jar
cd /home/ec2-user/server
sudo java -jar application.jar
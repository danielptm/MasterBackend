#!/usr/bin/env bash
echo export GLOBATI_SERVER_ENV="production" > ~/.bash_profile
profile ~/.bash_profile
mv /home/ec2-user/server/api/target/application.jar /home/ec2-user/server/application.jar
java -jar application.jar -Dserver.port=80
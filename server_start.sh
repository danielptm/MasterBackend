#!/usr/bin/env bash
echo GLOBATI_SERVER_ENV="prod" > ~/.bash_profile
profile ~/.bash_profile
mv /home/ec2-user/server/api/target/application.jar /home/ec2-user/server/application.jar
java -jar application.jar -Dserver.port=80
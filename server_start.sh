#!/usr/bin/env bash
cd /home/ec2-user/server/api/target
sudo /usr/bin/java -jar -Dserver.port=22 \
    *.jar > /dev/null 2> /dev/null < /dev/null &
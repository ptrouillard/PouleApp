#!/bin/sh

java -jar -Dspring.profiles.active=prod -Dserver.port=8010 ${project.build.finalName}.jar > pouleApp-api.log

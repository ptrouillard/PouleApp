#!/bin/sh

java -jar -Dspring.profiles.active=prod -Dserver.port=8000 ${project.build.finalName}.jar > poule-ui.log

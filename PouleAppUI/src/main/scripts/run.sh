#!/bin/sh

java -jar -Dspring.profiles.active=prod -Dserver.port=8000 /home/pi/PouleApp/${project.build.finalName}.jar > /home/pi/PouleApp/poule-ui.log

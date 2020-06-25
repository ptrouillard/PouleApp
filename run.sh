#!/bin/sh

java -jar -Dspring.profiles.active=prod -Dserver.port=8000 target/poule-1.0.1-SNAPSHOT.jar > poule.log

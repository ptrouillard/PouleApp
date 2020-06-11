# PouleApp

This project is intended to create a minimal UI for my Hen house
I built an automatic door (this is discribed on my blog in french) with the RaspBerryPi
The goal of this door is to work on its own without human interaction.

However, I built this project to help monitor / test the door during the experimentation phase. 

# About the code

The code is only using SprintBoot and Pi4j (a java library to control the PI).
Note that Pi4j is built on top of WiringPI which is a great lib ( thanks to Gordon Henderson who built this lib).

The core of the code (the interesting part), is GPIODoorService. If you want to take a look at only one class, it should be this one.

# About the launch

To launch the project, one important thing is to use the system property spring.profiles.active to select the "prod" profile.
If the "prod" profile is not selected, a mock implementation of service "GPIODoorService" will be used.

java -jar -Dspring.profiles.active=prod -Dserver.port=8000 target/poule-0.0.1-SNAPSHOT.jar > poule.log


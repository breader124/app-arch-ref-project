# app-arch-ref-project
Reference project containing 3 modules written using 3 different application architecture styles: hexagonal, micro kernel, pipes and filters

## How to run
All projects are simple Maven projects containing properly configured pom.xml file. All you need to do to run them is to type "mvn spring-boot:run" in project's dir. Additionally, if you want to see microkernel project in action, you need to configure git repo backing config server. After you did it, it's possible to change application's behavior at runtime when you change properties in mentioned git repo and invoke /refresh endpoint. In case of pipes and filters project, modules are connected using Kafka, thus you need to setup Kafka to see app in action. Fortunately, it's not a problem since in project's dir you'll find docker-compose file doing it for you. Just type "docker-compose up -d" before you launch app itself.

In case of any questions, you're welcome to reach me out via mail: breaderman@gmail.com. I'll answer all questions and help you run the code with pleasure.

## Important note about modelling
Please note, that purpose of this project was only to show and tryout different application architectures, examples themselves regarding business logic validity are loosely related to real world.

# MVP MDD APP

### Prerequisites

-   NodeJS 16+
-   Java JDK 11
-   Maven
-   MySQL

## Database

Create a mysql database and import the schema from `script.sql`

## Start the project

Make sure to have correct values for environment variables in `environment.ts` and `environment.prod.ts` (front) and `application.properties` (back).

Git clone:

> git clone https://github.com/naoylcb/Developpez-une-application-full-stack-complete.git

Go inside front-end folder:

> cd front

Install front dependencies:

> npm install

Launch front-end:

> npm run start

Go inside back-end folder:

> cd back

Install back-end dependencies:

> mvn clean install

Launch back-end:

> mvn spring-boot:run

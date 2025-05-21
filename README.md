# BFHL Java Test

This is the Spring Boot project for the Bajaj Finserv Health JAVA Qualifier Test.

## Project Description

- On startup, the app sends a POST request to generate a webhook.
- Based on the registration number (odd/even), it solves a SQL problem.
- The solution SQL query is submitted back to the returned webhook using a JWT token.

## Author Info

- **Name:** Vinod  
- **Email:** vinod24012004@gmail.com  
- **Reg No:** 1032222817

## How to Run

```bash
git clone https://github.com/vinodr24/bfhl-java-test.git
cd bfhl-java-test
mvn clean package
java -jar target/bfhl-java-test-0.0.1-SNAPSHOT.jar

## Jar File Link
https://github.com/vinodr24/bfhl_java_test/raw/main/bfhl-java-test/target/bfhl-java-test-0.0.1-SNAPSHOT.jar

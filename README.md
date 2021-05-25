# maven_rest_assured_api_testing

This is a pet project for API testing. The current project is based on the Java stack (Java 8, Maven, Rest Assured, Lombok, Assertj, Allure Report, Jackson)

## Prerequisites

In order to run this project maven and allure-commandline should be installed.

## Usage

Clone project using the following command:

```
git clone https://github.com/sovchenko/maven_rest_assured_api_testing.git
```
Navigate to the project directory and run tests using the following command:
```
mvn clean test
```
To generate allure report run the following command in the project directory:
```
allure serve allure-results/
```

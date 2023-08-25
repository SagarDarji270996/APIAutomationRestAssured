
# REST API Automation Testing

This project demonstrates automated testing of a REST API using Java, TestNG, RestAssured, and other utilities.

# Overview
This project aims to automate the testing of a RESTful API using various tools and libraries. It includes functionalities for user registration and authentication, making use of data-driven testing with Excel spreadsheets.

# Features
User Registration: Automatically registers users with randomized data.

Data-Driven Testing: Reads test data from an Excel spreadsheet.

Allure Reporting: Utilizes Allure framework for better test reporting.

Random String Generation: Generates random strings for test data.

API Testing: Tests API endpoints for registration and authentication.

# Prerequisites
Java JDK (11+)

Maven

Allure (for reporting)

# Getting Started



## Installation

1. Clone this repository:

```bash
  git clone <repository-url>
```
2. Set up the necessary configuration in the Test_Configuration/Config.properties file.
3. Set up your test data in the Test_Data/TestDataSheet.xlsx Excel spreadsheet.
4. Install the required dependencies using Maven:
```bash
  mvn clean install
```
5. Run the tests using Maven:
```bash
  mvn test
```
6. Generate Allure reports:
```bash
  allure generate allure-results --clean -o allure-report
```
7. Open the generated Allure report:
```bash
  allure open allure-report
```

## Project Structure

src/main/java/: Contains utility classes and configuration.

src/test/java/: Contains test classes.

Test_Configuration/Config.properties: Configuration properties.

Test_Data/TestDataSheet.xlsx: Excel spreadsheet for test data.

allure-results/: Directory for Allure report results.

allure-report/: Generated Allure report.

## Test Data

The test data is stored in the Test_Data/TestDataSheet.xlsx file. This spreadsheet contains the required data for user registration testing.

## Reporting

Allure framework is used for reporting. After running the tests, you can generate and view the detailed test reports using Allure.


    
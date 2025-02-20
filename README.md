# BBC Automation Testing Project 🚀

## Overview

This project automates functional testing for the BBC website using Selenium WebDriver and TestNG. The tests validate
key functionalities such as login, navigation, and article interactions.
¬

## 🛠️ Technologies Used

- Intellij Idea Community Version
- Java 23.0.1
- Selenium WebDriver
- TestNG
- Maven 3 (Built-in)
- Allure

## 🔧 Prerequisites

1. **Install Java** (JDK 23.0.1 or higher)
    - Verify installation:
      ```sh
      java -version
      ```
2. **Set up Maven 3** (for dependency management)
    - For this project, Intellij Idea built-in Maven 3 is used
    - Can be installed separately but this needs additional set up
    - Reference: https://www.jetbrains.com/help/idea/maven-support.html
3. **Set up WebDrivers**
    - Download chromedriver and geckodriver files.
    - Place `chromedriver.exe` and `geckodriver.exe` in the project folder on your device.
    - Update `webdriver.chrome.driver` and `geckodriver.exe` path in `config.properties`.
    - Select driver in `config.properties` which will be run for your tests by uncommenting configs
4. **Other necessary set-ups**
    - Verify all the dependencies are properly set-up in pom.xml file.
    - Install 'Lombok', 'Create TestNG XML', 'Allure Reports' plugins
    - Instead of 'Allure Reports' plugin, you can install Allure CLI using homebrew or any other available method
      ```sh
      brew install allure
      ```

## 📂 Project Structure

📂 projectJuniorHorbunova/

- logs/ #test-results file for tests output
- src/ # Main source code
- test/ # Test scripts
- resources/ # Config files, test data
- target/ #allure-results file for Allure reports
- config.properties/ # WebDrivers set-up
- allure.properties/ # Allure reporting set-up
- pom.xml # Maven dependencies
- README.md # Project documentation
- .gitignore # Git ignored files

## 🚀 How to Run Tests

- You can run tests directly from test classes
- You can run tests using testng.xml

## 🚀 How to Visualise Allure Reports

If you have an Allure plugin installed:
- Run test/tests
- To generate report: right-click on the 'allure-results' folder found in 'target' folder -> Allure -> Generate
- To open report: right-click on the 'allure-results' folder found in 'target' folder -> Allure -> Serve
- Alternative to open report: right-click on the 'allure-report' folder found in 'target' folder -> Allure -> Open

If you have Allure CLI installed:
- Open Terminal
- Check if CLI is installed
  ```sh
  allure --version
  ```
- Generate Allure report
  ```sh
  allure generate target/allure-results --clean
  ```
- Serve Allure report (opens Allure UI in browser)
  ```sh
  allure serve target/allure-results
  ```
- Clear results and report if needed
  ```sh
  rm -rf target/allure-results target/allure-report
  ```

## **📊 Where can users find test logs?**

**📄 Test Logs**

After test execution, reports are generated in:

- **log4j** → `logs/test-results.log`
- **Allure** → `target/allure-results`

## ⚙️ Configuration

WebDriver properties are contained in `config.properties`. Basic WebDriver configuration resides in BaseTest

## 🐞 Troubleshooting
❌ **Issue:** WebDriver on macbook may not run  
✅ **Solution:**
- Ensure webdrivers have appropriate permissions set in System Preferences -> Security.

❌ **Issue:** Built-in Maven 3 may not process builds  
✅ **Solution:**
- Ensure Maven 3 has necessary resources to run. Right-click on the pom.xml -> Maven ->
  Download resources. Then sync Project.

❌ **Issue:** Maven dependencies are failing to load  
✅ **Solution:**
- Ensure all dependencies are fetched. Right-click on the pom.xml -> Maven -> Sync Project.

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
- Logging log4j

## 🔧 Prerequisites

1. **Install Java** (JDK 23.0.1 or higher)
    - Verify installation:
      ```sh
      java -version
      ```
2. **Set up Maven 3** (for dependency management)
    - For this project Intellij Idea built-in Maven 3 is used
    - Can be installed separately but this needs additional set up
    - Reference: https://www.jetbrains.com/help/idea/maven-support.html
3. **Set up WebDrivers**
    - Download chromedriver files.
    - Place `chromedriver.exe` in the project folder.
    - Update `webdriver.chrome.driver` path in `config.properties`.
4. **Additional set-ups**
    - Verify all the dependencies are properly set-up in pom.xml file.

## 📂 Project Structure

📂 projectJuniorHorbunova/

- logs/ #test-results file for tests output
- src/ # Main source code
- test/ # Test scripts
- resources/ # Config files, test data
- config.properties/ # WebDrivers (ChromeDriver) set-up
- pom.xml # Maven dependencies
- README.md # Project documentation
- .gitignore # Git ignored files

## 🚀 How to Run Tests

- You can run tests directly from test classes
- You can run tests using testng.xml

## **📊 Where can users find test logs?**

**📄 Test Logs**

After test execution, reports are generated in:

- **log4j** → `logs/test-results.log`

## ⚙️ Configuration

WebDriver properties are contained in `config.properties`. Basic WebDriver configuration resides in BaseTest

## 🐞 Troubleshooting
❌ **Issue:** WebDriver on macbook may not run  
✅ **Solution:**
- Ensure ChromeDriver has appropriate permissions set in System Preferences -> Security.

❌ **Issue:** Built-in Maven 3 may not process builds  
✅ **Solution:**
- Ensure Maven 3 has necessary resources to run. Right-click on the pom.xml -> Maven ->
  Download resources. Then sync Project.

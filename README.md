```markdown
# Practice + Amazon E2E Automation Framework

![Build](https://github.com/<your-username>/AmazonEndToEndFlow/actions/workflows/ci.yml/badge.svg)
![License](https://img.shields.io/github/license/<Pankaj>/AmazonEndToEndFlow)

End-to-end **test automation framework** built in Java to validate:
- `https://practice.automationtesting.in/` (demo practice site)
- Amazon workflows (search → add to cart → checkout simulation)

**Tech stack:**  
Java 8 • Selenium WebDriver • TestNG • Maven • WebDriverManager • Log4j2 • (Extent/Allure optional)

---

---


## 🖼️ Demo
<img width="2470" height="1486" alt="image" src="https://github.com/user-attachments/assets/df164cdf-4c43-4a3a-ba54-4ca292f7932b" />

## 📂 Project Structure



src/main/java
├─ AbstractHelper/ # app utilities
├─ CommonObjectClasses/ # reusable UI objects
├─ DataResources/ # Excel/JSON/property test data
├─ ExcelDataUtil/ # Excel helper classes
├─ PageObjects/ # Page Object classes (Shop, Cart, etc)
src/main/resources # log4j, sample properties, roadmap
src/test/java
├─ BaseBrowserHelper/ # WebDriver bootstrap & env setup
├─ BaseUtilities/ # listeners, report config
├─ DataReaderFunctions/ # providers (Excel, JSON)
├─ FunctionalTestcasesPOM/ # test classes (FrameWorkSmokeTest*.java)
logs/ # local logs (gitignored)
Reports/ # generated test reports (gitignored)
target/ # maven build output (gitignored)
test-output/ # TestNG results (gitignored)
pom.xml
testng.xml



---

## 🚀 Quick Start

> Prerequisites: **Java 8+** and **Maven 3+**

```bash
# Clone repository
git clone https://github.com/<your-username>/AmazonEndToEndFlow.git
cd AmazonEndToEndFlow

# Create local config from example
cp src/main/resources/config.example.properties src/main/resources/config.properties

# Edit config.properties with your local values (do not commit secrets)

# Run full suite
mvn clean test
````

Run a single test class:

```bash
mvn -Dtest=FunctionalTestcasesPOM.FrameWorkSmokeTest3 test
```

Run a single test method:

```bash
mvn -Dtest=FunctionalTestcasesPOM.FrameWorkSmokeTest3#shouldAddProductToCartAndProceedToCheckout test
```

---

## 🔑 Features

* **Page Object Model (POM):** Organized locators & actions per page (`PageObjects/`).
* **Data-driven testing:** Excel and JSON data providers (`DataResources/`, `ExcelDataUtil/`).
* **Reusable helpers:** Common logic in `AbstractHelper/`, environment setup in `BaseUtilities/`.
* **Screenshot on failure:** Automatic capture to `Reports/screenshots/`.
* **Centralized logging:** Log4j2 console + file output (`logs/`).
* **CI-ready:** GitHub Actions workflow runs all tests on push/PR.

---

## 📊 Reports

* **TestNG HTML Reports** → `test-output/`
* **Extent/Allure Reports** (optional setup) → `Reports/`
* **Screenshots on failure** → `Reports/screenshots/`

---

## 🖼️ Demo

A sample end-to-end flow (Shop → add product → checkout) is recorded below:


https://github.com/user-attachments/assets/65f851d2-25c0-4d2c-a271-eb72d9af1229




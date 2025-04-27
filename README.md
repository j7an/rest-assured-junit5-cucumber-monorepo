# Java REST Assured JUnit 5 Cucumber Monorepo Example

This project demonstrates a monorepo setup using Java, Gradle, REST Assured, JUnit 5, and Cucumber for API testing.

## Prerequisites

*   Java Development Kit (JDK) (e.g., JDK 21 or later)
*   Gradle (The project uses the Gradle wrapper, so a local installation is optional)

## Project Structure

This is a Gradle multi-project build (monorepo).

*   **Root Project**: The top-level directory contains the main `build.gradle.kts` and `settings.gradle.kts`. `settings.gradle.kts` defines which subdirectories are included as Gradle projects (e.g., `include("common", "rest-assured-cucumber-junit5", "rest-assured-junit5")`). Key files include `gradlew` scripts and the root `README.md` and `LICENSE`.
*   **`common` Module**: A potential module for shared code, utilities, or models used across different test modules. It has its own `build.gradle.kts`.
*   **`rest-assured-cucumber-junit5` Module**: Contains the API tests using Cucumber BDD.
    *   `build.gradle.kts`: Defines dependencies like REST Assured, Cucumber, JUnit 5, and configures the Cucumber test execution.
    *   `src/test/java`: Holds the Java code for step definitions, hooks, and test runners (e.g., under `com/github/j7an/bdd/steps/`).
    *   `src/test/resources/features`: Contains the Gherkin `.feature` files describing the test scenarios (e.g., `user_api.feature`).
*   **`rest-assured-junit5` Module**: Contains API tests using standard JUnit 5 and REST Assured.
    *   `build.gradle.kts`: Defines dependencies like REST Assured and JUnit 5.
    *   `src/test/java`: Holds the Java code for JUnit 5 test classes.

## Creating a Similar Monorepo

To initialize a similar Gradle monorepo structure from scratch:

1.  **Initialize Gradle Project:**
    In an empty directory, run the Gradle init task. Choose 'basic' project type and 'Kotlin' for the build script DSL.
    ```bash
    gradle init --type basic --dsl kotlin
    ```
    This creates the root `build.gradle.kts`, `settings.gradle.kts`, and the Gradle wrapper files (`gradlew`, `gradlew.bat`, `gradle/`).

2.  **Define Modules:**
    Edit the `settings.gradle.kts` file to include the desired subprojects (modules). For example:
    ```kotlin
    // settings.gradle.kts
    rootProject.name = "your-monorepo-name"

    include("common", "rest-assured-cucumber-junit5", "rest-assured-junit5")
    ```

3.  **Create Module Directories:**
    Create the directories for each module defined in `settings.gradle.kts` (e.g., `common/`, `rest-assured-cucumber-junit5/`, `rest-assured-junit5/`).

4.  **Add Module Build Scripts:**
    Inside each module directory, create a `build.gradle.kts` file. Apply necessary plugins (like `java` or `java-library`) and define dependencies specific to that module. Refer to the `build.gradle.kts` files in this repository for examples.

5.  **Configure Root Build Script (Optional):**
    You can define common configurations or dependencies for all subprojects in the root `build.gradle.kts` using `subprojects { ... }` or `allprojects { ... }` blocks if needed.

## Setup

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd <repository-name>
    ```

2.  **Build the project:**
    Use the Gradle wrapper to compile the code and download dependencies.
    ```bash
    ./gradlew build
    ```
    On Windows:
    ```bash
    .\gradlew.bat build
    ```

## Running Tests

Execute the tests using the Gradle `test` task within the specific module or from the root.

*   **Run tests for the `rest-assured-cucumber-junit5` module:**
    ```bash
    ./gradlew :rest-assured-cucumber-junit5:test
    ```
    or on Windows:
    ```bash
    .\gradlew.bat :rest-assured-cucumber-junit5:test
    ```

*   **Run tests for the `rest-assured-junit5` module:**
    ```bash
    ./gradlew :rest-assured-junit5:test
    ```
    or on Windows:
    ```bash
    .\gradlew.bat :rest-assured-junit5:test
    ```

*   **Run all tests in the monorepo:**
    ```bash
    ./gradlew test
    ```
    or on Windows:
    ```bash
    .\gradlew.bat test
    ```

Test reports will be generated in the `build/reports/` directory of the respective module (e.g., `rest-assured-cucumber-junit5/build/reports/`, `rest-assured-junit5/build/reports/`). Cucumber HTML reports are specifically generated in `rest-assured-cucumber-junit5/build/reports/cucumber/`.

## Key Dependencies

*   **REST Assured**: For simplifying REST API testing in Java.
*   **Cucumber**: For Behavior-Driven Development (BDD) (used in `rest-assured-cucumber-junit5`).
    *   `cucumber-java`: Core Java implementation.
    *   `cucumber-junit-platform-engine`: To run Cucumber features with JUnit 5.
    *   `cucumber-picocontainer`: For dependency injection between step definition classes.
*   **JUnit 5**: The testing platform (used in both test modules).
    *   `junit-jupiter`: Core JUnit 5 annotations and assertions.
    *   `junit-platform-suite`: For potentially running suites.
*   **Gradle**: Build automation tool.

## Configuration

### `rest-assured-cucumber-junit5` Module Configuration

The `rest-assured-cucumber-junit5/build.gradle.kts` file configures the test execution environment, particularly for Cucumber:

```kotlin
tasks.withType<Test> {
    // Specifies the package where Cucumber step definitions are located
    systemProperty("cucumber.glue", "com.github.j7an.bdd.steps")
    // Specifies the directory containing the .feature files
    systemProperty("cucumber.features", "src/test/resources/features")
    // Configures Cucumber plugins (e.g., pretty console output, HTML report)
    systemProperty("cucumber.plugin", "pretty, html:build/reports/cucumber/cucumber-report.html")
    // Sets the naming strategy for tests shown in JUnit reports
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
    // Use JUnit Platform for test execution
    useJUnitPlatform()

    // Uncomment lines below to enable specific Cucumber features:
    // systemProperty("cucumber.filter.tags", "@smoke and not @wip") // Filter tests by tags
    // systemProperty("cucumber.execution.parallel.enabled", "true") // Enable parallel execution
}
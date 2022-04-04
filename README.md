# GoREST API Functional Testing Automation Framework

## Directory Structure
The project consists of the following files:
- src to store tests: API models, tests' data, test scenarios, utility functions and helpers
- pom.xml maven project file

```bash
├── README.md
├── pom.xml
├── src
│   └── main
│       ├── java
│       │  └── goRest
│       │          ├── clients
│       │          │   ├── BaseClass.java
│       │          │   ├── RESTClient.java
│       │          │   └── SQLClient.java
│       │          ├── common
│       │          │   ├── Constants.java
│       │          │   └── Data.java
│       │          ├── definitions
│       │          │   ├── RESTStepDefinitions.java
│       │          │   └── SQLStepDefinitions.java
│       │          └── environments
│       │              └── SqlEnvironment.java
│       └── resources
│                  └── lib
└── test
   ├── java
   |      └── CucumberRunnerTest
   └── resources
         └── features

```
# GoREST API Functional Testing Automation Framework

This is a Java framework, which provides a ready-state project for REST API functional testing automation.

## Environment

Required

[Python](https://www.python.org/downloads/) v3+

[Java](https://www.java.com/en/download/) 1.8

## Install

- Clone the repository
```shell
git clone https://github.com/ArsenDadunts/GoREST_Automation.git
```

- Install and generate jar with dependencies
```shell
mvn clean compile assembly:single install -DskipTests=true
```

## Directory Structure
The project consists of the following files:
- src to store tests: API models, tests' data, test scenarios, utility functions and helpers
- pom.xml maven project file

```bash
├── README.md
├── pom.xml
├── run.py
├── automation_runner_res
│   ├── argument_parser.py
│   ├── generate_xml.py
│   └── tags_parser.py
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
│       │          │   ├── ObjectValidationDefinitions.java
│       │          │   └── SQLStepDefinitions.java
│       │          └── environments
│       │              └── SqlEnvironment.java
│       │          └── utilities
│       │              └── Utils.java
│       └── resources
└── test
   ├── java
   |      └── CucumberRunnerTest
   └── resources
         └── features

```

---
## Usage

_run.py_ is defined to run the implemented test cases and provide a comprehensive report.

**Run command examples:**

*Example 1:*

Use the command below to run all positive cases.
```sh
$  python run.py --tags positive
```

*Example 2:*

Use the command below to run feature test.
```sh
$  python run.py -t Users/negative/DELETE/verify_delete_user_by_id_with_deleted_userId.feature
```

Please see all supported arguments of run.py below:

| Script arguments  | Specification  |Example|
| ------------ | ------------ | ------------ |
|-h, --help  | Show this help message and exit |  |
|-cp, --classpath  |Specify classpath |python run.py -cp GoREST_Automation-1.0-SNAPSHOT-jar-with-dependencies.jar|
|-t, --tests | Specify test(s)| python run.py -t Users/negative/DELETE|
|-ex, --exclude |Specify tags to exclude |python run.py --ex_groups positive|
|--tags |Specify tags to include in run |python run.py -ex smoke|

## Test results

After completing run the "results" folder is generated with html, json, and xml report files.  

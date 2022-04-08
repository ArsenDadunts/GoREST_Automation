import os
import argparse
import datetime


def create_parser():
    parser = argparse.ArgumentParser(description="Execute REST API tests.",
                                     formatter_class=argparse.ArgumentDefaultsHelpFormatter)

    parser.add_argument("-cp", "--classpath",
                        default="target/GoREST_Automation-1.0-SNAPSHOT-jar-with-dependencies.jar:src/main/resources/lib/mysql-connector-java-8.0.28.jar",
                        help="JAR file(s) with GoRest glue code and all external dependencies (e.g. Cucumber)")

    parser.add_argument("-mr", "--mvnRun", action='store_true',
                        help="Set tests execution type ")

    parser.add_argument("-t", "--tests",
                        help="Path to the directory or file with the tests that should be executed")

    parser.add_argument("--tags", type=str, nargs='+',
                        default=[],
                        help="Cucumber tags to run the specified tests")

    parser.add_argument("-ex", "--exclude", type=str, nargs='+',
                        help="Cucumber exclude tags")

    args = parser.parse_args()

    args.results = f"results/{datetime.datetime.now().strftime('%Y-%m-%d_%H-%M-%S')}"
    args.JOB_URL = os.environ.get('JOB_URL', '')
    return args

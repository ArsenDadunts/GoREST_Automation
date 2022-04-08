import os
import subprocess
from string import Template
from automation_runner_res.argument_parser import create_parser
from automation_runner_res.tags_parser import tags_parser
from automation_runner_res.generate_xml import generate_xml

args = create_parser()

def make_mvn_command():
    command_args = "mvn test -DGoREST_Automation.results=$results"
    return command_args

def make_cli_command():
    test_path = "src/test/resources/features/"
    if args.tests:
        test_path += args.tests
    command_args = "java -cp $classpath -DGoREST_Automation.results=$results "
    command_args = command_args + f"io.cucumber.core.cli.Main --glue goRest {test_path} " \
                                  "--plugin junit:$results/results.xml --plugin json:$results/results.json " \
                                  "--plugin html:$results/results.html "
    tags = tags_parser(args)
    command_args += f"--tags \"{tags}\""
    return command_args

def make_command():
    if args.mvnRun:
        generate_xml(args)
        return make_mvn_command()
    return make_cli_command()

# def create_dir():
#     create_result_dir = f"mkdir -p {args.results}/logs"
#     subprocess.call(create_result_dir, shell=True)

def run_command():
    command_template = Template(make_command())
    command = command_template.substitute(args.__dict__)
    subprocess.call(command, shell=True)

def delete_current_report():
    delete_current_cucumber_html_reports = "rm -rf ../cucumber-html-reports"
    subprocess.call(delete_current_cucumber_html_reports, shell=True)

def copy_json():
    copy_json_command = f"cp {args.results}/results.json results/latest.json"
    subprocess.call(copy_json_command, shell=True)

def main():
    delete_current_report()
#     create_dir()
    run_command()
    copy_json()

main()

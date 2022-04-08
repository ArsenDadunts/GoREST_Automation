import os
import xml.etree.cElementTree as ET
import xml.dom.minidom
from automation_runner_res.tags_parser import tags_parser


def generate_xml(args):
    # Constants
    cucumber_features = "cucumber.features"
    cucumber_filter_name = "cucumber.filter.tags"
    test_path = "src/test/resources/features/"

    suite = ET.Element("suite", name="Test Suite", verbose="1")
    suite.set('thread-count', "1")

    tags = tags_parser(args)
    path = f"{test_path}{args.tests}" if args.tests else f"{test_path}"

    test_element = ET.SubElement(suite, "test", name="Test 1")
    ET.SubElement(test_element, "parameter", name=cucumber_features, value=f"{path}")
    ET.SubElement(test_element, "parameter", name=cucumber_filter_name, value=f"{tags}")
    cls = ET.SubElement(test_element, "classes")
    ET.SubElement(cls, "class", name="CustomCucumberRunner")

    dom = xml.dom.minidom.parseString(ET.tostring(suite))
    xml_string = dom.toprettyxml()
    part1, part2 = xml_string.split('?>')
    doc_type = '<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">'
    with open("src/test/resources/testng.xml", 'w') as xfile:
        xfile.write(f'{part1} encoding="UTF-8"?>\n{doc_type}{part2}')

from xml.dom.minidom import parse
import xml.etree.ElementTree as ET
import pandas as pd
import csv

DOMTree = ET.parse("/Users/jayrajendran/Downloads/textjay.xml")
root = DOMTree.getroot()
#for child in root:
#    print(child.tag, child.attrib)
#    if child.tag == 'MsgSenderApplId':
#        print(":-->", child.element)
data = []
content = ""
for item in root.iter('OperatorNam'):
    #print(item.text)
    content = item.text + ","

for item in root.iter('ItemType'):
    #print(item.text)
    content = content + item.text
data.append(content)
df = pd.DataFrame(data)
#items_MsgSenderApplId = DOMTree.getElementsByTagName("MsgSenderApplId")
#for item in items_MsgSenderApplId:
#     print(items_MsgSenderApplId.getAttribute("name"))
df.to_csv('/Users/jayrajendran/Downloads/Stuff.csv', index=False, header=False)



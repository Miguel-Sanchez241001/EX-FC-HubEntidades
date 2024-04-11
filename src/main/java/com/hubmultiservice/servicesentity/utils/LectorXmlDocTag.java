package com.hubmultiservice.servicesentity.utils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//extends DefaultHandler
public class LectorXmlDocTag  {
    public Document loadXMLFromByteArray(byte[] xmlData) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true); // Ignorar espacios en blanco
        DocumentBuilder builder = factory.newDocumentBuilder();
        ByteArrayInputStream input = new ByteArrayInputStream(xmlData);
        return builder.parse(input);
    }

    private void traverseAndExtract(Node node, Set<String> visited) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            String nodeName = node.getNodeName();
            if (!visited.contains(nodeName)) {
                visited.add(nodeName);
            }
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                traverseAndExtract(children.item(i), visited);
            }
        }
    }

    public List<String> processXML(byte[] xmlData) throws Exception {
        Document doc = loadXMLFromByteArray(xmlData);
        Set<String> visited = new LinkedHashSet<>();
        traverseAndExtract(doc.getDocumentElement(), visited);
        return new ArrayList<>(visited);
    }

    // private List<String> tags = new ArrayList<>();
    // private StringBuilder currentTag = new StringBuilder();

    // public List<String> readTags(byte[] xmlContent) throws ParserConfigurationException, SAXException, IOException {
    //     SAXParserFactory factory = SAXParserFactory.newInstance();
    //     SAXParser saxParser = factory.newSAXParser();
    //     saxParser.parse(new ByteArrayInputStream(xmlContent), this);
    //     return tags;
    // }

    // @Override
    // public void startElement(String uri, String localName, String qName, Attributes attributes) {
    //     currentTag.setLength(0);
    //     currentTag.append(qName);
    // }

    // @Override
    // public void endElement(String uri, String localName, String qName) {
    //     if (!currentTag.toString().isEmpty()) {
    //         tags.add(currentTag.toString());
    //     }
    // }

    // @Override
    // public void characters(char[] ch, int start, int length) {
    //     currentTag.append(new String(ch, start, length));
    // }
}
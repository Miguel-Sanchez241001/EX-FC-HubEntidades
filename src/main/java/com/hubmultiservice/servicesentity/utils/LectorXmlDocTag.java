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
import org.w3c.dom.Element;
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

    private void traverseAndExtract(Node node, Set<String> visited, int depth) {
  
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String nodeName =  element.getTagName();

            // Imprime el nombre del elemento con un padding para mostrar jerarquía
            if (!visited.contains(nodeName)) {
                visited.add(nodeName );  
            }
            System.out.println("  ".repeat(depth) + element.getTagName());

            // Recorre todos los hijos del elemento actual
            NodeList children = element.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                traverseAndExtract(children.item(i),visited, depth + 1);
            }
        }





    }

    public List<String> processXML(byte[] xmlData) throws Exception {
        Document doc = loadXMLFromByteArray(xmlData);
        Set<String> visited = new LinkedHashSet<>();
        traverseAndExtract(doc.getDocumentElement(), visited,0);
        return new ArrayList<>(visited);
    }

}
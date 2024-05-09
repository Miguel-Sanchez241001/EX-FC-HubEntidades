package com.hubmultiservice.servicesentity.controller;

import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hubmultiservice.servicesentity.models.entidad.CamposTag;
import com.hubmultiservice.servicesentity.models.entidad.Entidad;
import com.hubmultiservice.servicesentity.models.entidad.Interfaces;
import com.hubmultiservice.servicesentity.models.entidad.Plantilla;
import com.hubmultiservice.servicesentity.models.entidad.enums.DocType;
import com.hubmultiservice.servicesentity.models.response.ResponseFront;
import com.hubmultiservice.servicesentity.repository.CampoTagRepo;
import com.hubmultiservice.servicesentity.repository.PlantillaRepo;
import com.hubmultiservice.servicesentity.services.ServicesEntidad;
import com.hubmultiservice.servicesentity.utils.LectorJsonDocTag;
import com.hubmultiservice.servicesentity.utils.LectorXmlDocTag;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("entidad")
@CrossOrigin
public class EntidadController {
    

    @Autowired
    private PlantillaRepo plantillaRepo;


    @Autowired private CampoTagRepo campoTagRepo;

    @Autowired
    private ServicesEntidad servicesentity;

    private static final Logger logger = LoggerFactory.getLogger(EntidadController.class);




    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseFront entidadSave(@RequestBody Entidad entidad) {
        return servicesentity.saveEntidad(entidad);        
    }
    
    @PutMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseFront entidadUpdate(@RequestBody Entidad entidad) {
        return servicesentity.updateEntidad(entidad);        
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseFront entidades() {
        return servicesentity.showEntidades();        
    }

    @PostMapping("/interfaz")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseFront interfazSave(@Valid  @RequestBody Interfaces interfaces) {
       return servicesentity.saveInterfaz(interfaces);
    }
 
    @PutMapping("/interfaz")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseFront interfazUdpate(@Valid  @RequestBody Interfaces interfaces) {
       return servicesentity.saveInterfaz(interfaces);
    }

    @PostMapping("/campos")
    public String postMethodName(@RequestBody List<CamposTag> campos) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        final Plantilla[] ptHolder = new Plantilla[1];  // Contenedor para la plantilla
        String data = " ";
        if (!campos.isEmpty()) {    
            campos.stream().forEach(campo -> {
               Plantilla  pt = plantillaRepo.findById(campo.getPlantillaId()).get();
               campo.setPlantilla(pt);
               campoTagRepo.save(campo);
               ptHolder[0] = pt;  // Guardar en el contenedor

            } );
        }
        // Aquí podrías hacer algo con ptHolder[0], como pasarla a otra función
    if (ptHolder[0] != null) {
        Plantilla  plantillaGuarda = ptHolder[0];
        byte[] decodedBytes =  plantillaGuarda.getContenido();   
            if (plantillaGuarda.getDocType().equals(DocType.XML)) {
           
            System.out.println(new String(decodedBytes));
            String xmlString = new String(decodedBytes);
            Document document =  parseXML(xmlString);
            System.out.println("Modificando el documento XML...");

            
            List<CamposTag> camposSave = campoTagRepo.findByPlantilla(plantillaGuarda);
                for (CamposTag cp : camposSave) {
                    modifyElementContent(document,cp.getEtiqueta(), cp.getValor());
                }
          
     
            String newXML =  generateXML(document);
            logger.info("Nuevo XML generado:");
            logger.info(newXML);
            data =  newXML; 
        }
        if (plantillaGuarda.getDocType().equals(DocType.JSON)) {

 
 
            JsonNode rootNode = parseJson(new String(decodedBytes));

            logger.info("JSON original: " + rootNode.toPrettyString());

            List<CamposTag> camposSave = campoTagRepo.findByPlantilla(plantillaGuarda);
            for (CamposTag cp : camposSave) {
                modifyValue(rootNode,cp.getEtiqueta(), cp.getValor());
 
            }
            logger.info("JSON modificado: " + rootNode.toPrettyString());
            data =  rootNode.toPrettyString();

        }
      
    }
        



        

        return data;
    }
    





    @PostMapping("/plantilla")
    public  ResponseEntity<List<String>>   plantillaSaveUdpate(@RequestBody Plantilla plantilla) throws Exception {
     
    // @PostMapping("/plantilla")
    // public  ResponseEntity<Plantilla>   plantillaSaveUdpate(@RequestBody Plantilla plantilla) throws Exception {
     

        ResponseFront r = servicesentity.savePlantilla(plantilla);
        Plantilla plantillaGuarda =  (Plantilla) r.getData();

        List<String> camposEncontrados =  new ArrayList<>();


        byte[] decodedBytes = Base64Utils.decodeFromString(plantilla.getContenidoSTR());
    
        String data = "";
        logger.info(new String(decodedBytes));
        if (plantillaGuarda.getDocType().equals(DocType.XML)) {
            
            System.out.println(new String(decodedBytes));
            String xmlString = new String(decodedBytes);
            Document document =  parseXML(xmlString);
            System.out.println("Estructura del XML:");
            camposEncontrados =  new LectorXmlDocTag().processXML(plantillaGuarda.getContenido()) ;
            System.out.println("Modificando el documento XML...");
            modifyElementContent(document,"tramaEntrada", "EXAMPLEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            // modifyElementContent(document,"apellido", "SANCHEZ");
            // modifyElementContent(document,"edad", "9999");
            // modifyElementContent(document,"ciudad", "Lima");
            // modifyElementContent(document,"calle", "Javier Prado");
            // modifyElementContent(document,"pais", "peru");
            String newXML =  generateXML(document);
            logger.info("Nuevo XML generado:");
            logger.info(newXML);
            data =  newXML; 
        }
        if (plantillaGuarda.getDocType().equals(DocType.JSON)) {

 
            camposEncontrados = new LectorJsonDocTag().readKeys(plantillaGuarda.getContenido());

            JsonNode rootNode = parseJson(new String(decodedBytes));

            logger.info("JSON original: " + rootNode.toPrettyString());

            // Modificar el valor de la clave 'city'
            modifyValue(rootNode,"nombre", "JUANITO");
            modifyValue(rootNode,"apellido", "SANCHEZ");
            modifyValue(rootNode,"edad", "9999");
            modifyValue(rootNode,"ciudad", "Lima");
            modifyValue(rootNode,"calle", "Javier Prado");
            modifyValue(rootNode,"pais", "peru");
            logger.info("JSON modificado: " + rootNode.toPrettyString());
            data =  rootNode.toPrettyString();

        }
        camposEncontrados.add(data);
        camposEncontrados.add(plantillaGuarda.toString());

        //return ResponseEntity.status(HttpStatus.CREATED).body(plantillaGuarda); 
        return ResponseEntity.status(HttpStatus.CREATED).body(camposEncontrados); 
    }

    public void modifyValue(JsonNode node, String key, String newValue) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            if (objectNode.has(key)) {
                objectNode.put(key, newValue);
            } else {
                node.fields().forEachRemaining(entry -> modifyValue(entry.getValue(), key, newValue));
            }
        } else if (node.isArray()) {
            for (JsonNode childNode : node) {
                modifyValue(childNode, key, newValue);
            }
        }
    }
    
    private JsonNode parseJson(String string) throws JsonMappingException, JsonProcessingException {
        return  new ObjectMapper().readTree(string);
    }

    public static void modifyElementContent(Document doc, String tagName, String newContent) {
        NodeList elements = doc.getElementsByTagName(tagName);
        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            element.setTextContent(newContent);  // Cambiar el contenido del elemento
        }
    }

    private String generateXML(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        return writer.getBuffer().toString();
    }

    private Document parseXML(String xmlString) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Parsea el string XML
        return builder.parse(new InputSource(new StringReader(xmlString)));
    }
}

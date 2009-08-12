package wftac.ckim.xml.xsd001;

import java.io.*;
import javax.xml.transform.dom.*;
import javax.xml.validation.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DocbookXSDAugmenter {

    public static void main(String[] args) 
      throws SAXException, IOException, ParserConfigurationException {

        SchemaFactory factory 
         = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaLocation = new File("/opt/xml/docbook/xsd/docbook.xsd");
        Schema schema = factory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();
        
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true); // never forget this
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(new File(args[0]));
        
        DOMSource source = new DOMSource(doc);
        DOMResult result = new DOMResult();
        
        try {
            validator.validate(source, result);
            Document augmented = (Document) result.getNode();
            // do whatever you need to do with the augmented document...
        }
        catch (SAXException ex) {
            System.out.println(args[0] + " is not valid because ");
            System.out.println(ex.getMessage());
        }  
        
    }

}
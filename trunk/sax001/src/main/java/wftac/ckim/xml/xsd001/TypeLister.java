package wftac.ckim.xml.xsd001;

//
// http://www.ibm.com/developerworks/xml/library/x-javaxmlvalidapi.html
//

import java.io.*;
import javax.xml.validation.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class TypeLister extends DefaultHandler {

    private TypeInfoProvider provider;
    
    public TypeLister(TypeInfoProvider provider) {
        this.provider = provider;
    }

    public static void main(String[] args) throws SAXException, IOException {

        SchemaFactory factory 
         = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaLocation = new File("/opt/xml/docbook/xsd/docbook.xsd");
        Schema schema = factory.newSchema(schemaLocation);
    
        ValidatorHandler vHandler = schema.newValidatorHandler();
        TypeInfoProvider provider = vHandler.getTypeInfoProvider();
        ContentHandler   cHandler = new TypeLister(provider);
        vHandler.setContentHandler(cHandler);
        
        XMLReader parser = XMLReaderFactory.createXMLReader();
        parser.setContentHandler(vHandler);
        parser.parse(args[0]);
        
    }
    
    public void startElement(String namespace, String localName,
      String qualifiedName, Attributes atts) throws SAXException {
        String type = provider.getElementTypeInfo().getTypeName();
        System.out.println(qualifiedName + ": " + type);
    }

}
package main;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Thomas Freitas
 */
public class Manejador extends DefaultHandler{
    ArrayList<Registro> registros = new ArrayList<>();
    Registro r;
    StringBuilder sb = new StringBuilder();
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName){
            case "registro":
              break;
            case "ci":
              r.setId(Integer.parseInt(sb.toString()));
              break;
            case "nombre":
              r.setNombre(sb.toString());
              break;
            case "fecha_nac":
              r.setFecha(sb.toString());
              break;
            case "profesion":
              r.setProfesion(Integer.parseInt(sb.toString()));
              break;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch(qName){
            case "registro":
              r =  new Registro();
              registros.add(r); 
              break;
            case "ci":
            case "nombre":
            case "fecha_nac":
            case "profesion":
              sb.delete(0, sb.length());
              break;
        }
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }
    
}

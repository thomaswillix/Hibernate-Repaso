package main;

import POJOS.Empleado;
import POJOS.Profesion;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.xml.sax.SAXException;

/**
 *
 * @author Thomas Freitas
 */
public class Main {

    public static void main(String[] args) throws SAXException, ParserConfigurationException{
        File f = new File("bbdd.xml");
        ArrayList<Registro> registros = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        SAXParserFactory spf=SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        Manejador m = new Manejador();
        try {
            sp.parse(f, m);
        } catch (IOException ex) {
            System.err.println("Error parsing document");
            errores.add("Error parsing document");
        }
        
        registros = m.getRegistros();
        System.out.println("---------------- LISTA DE REGISTROS DEL XML ----------------");
        for (Registro registro : registros) {       // COMPROBACIÓN DE QUE FUNCIONE EL PARSEO
            System.out.println(registro.toString());
        }
        System.out.println("---------------- INSERCIÓN DE LOS REGISTROS ----------------");
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        
        File err = new File("errores.txt");
        Empleado e;
        Profesion p;
        
        Scanner sc = new Scanner(System.in);
        String nomP;
        for (Registro registro : registros) {
            p = (Profesion) s.get(Profesion.class, registro.getProfesion());
            if (p==null){
                errores.add("EL CÓDIGO DE PROFESIÓN QUE SE HA DADO: " +registro.getProfesion() +" NO PERTENECE A NINGUNA PROFESIÓN");
                System.out.println("DEMOS DE ALTA LA PROFESIÓN CON CÓDIGO: " + registro.getProfesion() + "\n\nNombre de la profesión: ");
                nomP = sc.nextLine();
                p = new Profesion(registro.getProfesion(), nomP);
                s.save(p);
                System.out.println("Fin del alta");
            } else {
                e = new Empleado(registro.getId(), registro.getNombre(), registro.getFecha(), p.getNombre());
                s.save(e);
            }
        }
        System.out.println("\n ---------------- LISTA DE EMPLEADOS ----------------");
        Query q = s.createQuery("FROM Empleado");
        List<Empleado> listaE = q.list();
        for (int i = 0; i < listaE.size(); i++) {
            System.out.println(listaE.get(i).toString());
        }
    }
    
}

package main;

import POJOS.Empleado;
import POJOS.Profesion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        File err = new File("errores.txt");
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
        System.out.println("\n---------------- INSERCIÓN DE LOS REGISTROS ----------------");
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        
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
        t.commit(); 
        
        System.out.println("\n---------------- LISTA DE EMPLEADOS ----------------");
        Query q = s.createQuery("FROM Empleado");
        List<Empleado> listaE = q.list();
        for (int i = 0; i < listaE.size(); i++) {
            System.out.println(listaE.get(i).toString());
        }
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(err));
            for (int i = 0; i < errores.size(); i++) {
                bw.write(errores.get(i));
            }
            bw.close();
        } catch (IOException ex) {
            System.err.println("Error writing into error.txt file");
        }
        
        System.out.println("\n---------------- LISTA DE ERRORES ----------------");
        try {
            BufferedReader br = new BufferedReader(new FileReader(err));
            String line;
            try {
                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
            } catch (EOFException ex) {
                System.err.println("END OF FILE");
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        }
    }
}

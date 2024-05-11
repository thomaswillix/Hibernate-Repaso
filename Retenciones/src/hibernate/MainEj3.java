package hibernate;

import POJOS.Empleado;
import hibernate.Trabajadores;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author Thomas Freitas
 */
public class MainEj3 {
    public static void main(String[] args) {
        File f = new File("datos.obj");
        //int c;
        List<Trabajadores> trabajadores = new ArrayList<>();
        Trabajadores t;
        
        System.out.println("-------------LECTURA DEL FICHERO BINARIO-------------");
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            try {
                /*while ((c = fis.read()) != -1) {         //LECTURA CARACTER A CARACTER
                    System.out.print((char)c);
                }*/
                while (true) {                    
                    t  = (Trabajadores) ois.readObject(); //LECTURA COMO UN OBJETO (DE LA ANTERIOR MANERA NO SE VISUALIZA BIEN)
                    System.out.println(t.toString());
                    trabajadores.add(t);
                }
            } catch (EOFException e) {
                fis.close();
                System.err.println("End of file");
            } catch (ClassNotFoundException ex) {
                System.err.println("Class Not found");
            }
        }catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException ex) {
            Logger.getLogger(MainEj3.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n\n-------------HIBERNATE-------------");
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction tr = s.beginTransaction();
        Empleado e;
        Query q = s.createQuery("FROM Empleado");
        List<Empleado> empleados = q.list();
        for (Trabajadores trabajador : trabajadores) {
            e  = (Empleado) s.get(Empleado.class, trabajador.getEmpNo());
            if (e==null) {
                
            }
        }
    }
}

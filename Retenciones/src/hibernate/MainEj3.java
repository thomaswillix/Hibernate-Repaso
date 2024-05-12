package hibernate;

import POJOS.Departamento;
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
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction tr = s.beginTransaction();
        Empleado e;
        Departamento d;
        System.out.println("\n\n------------- LISTA DE EMPLEADOS SIN ACTUALIZAR -------------");
        Query q = s.createQuery("FROM Empleado");
        List<Empleado> list = q.list();
        for (Empleado empleado : list) {
            System.out.println(empleado.toString());
        }
        
        for (Trabajadores trabajador : trabajadores) {
            e  = (Empleado) s.get(Empleado.class, trabajador.getEmpNo());
            if (e==null) {
                System.err.println("El código introducido no pertenece a ningún empleado de la base de datos.");
            } else{
                d = (Departamento)s.get(Departamento.class, trabajador.getDept_no());
                if (d!=null) {
                    e.setDepartamento(d);
                    e.setSalario(trabajador.getSalario());
                    e.setNombre(trabajador.getNombre());
                    s.saveOrUpdate(e);
                }else System.err.println("El departamento que se indica no existe en la base de datos.");
            }
        }
        tr.commit();
        System.out.println("\n\n------------- LISTA DE EMPLEADOS ACTUALIZADOS -------------");
        Query q2 = s.createQuery("FROM Empleado");
        List<Empleado> list2 = q.list();
        for (Empleado empleado : list2) {
            System.out.println(empleado.toString());
        }
        System.exit(0);
    }
}

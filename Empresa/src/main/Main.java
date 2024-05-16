package main;

import POJOS.Departamentos;
import POJOS.Empleados;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Thomas
 */
public class Main {

    private static SessionFactory sf = HibernateUtil.sessionFactory();
    private static Session s = sf.openSession();
    private static Transaction t = s.beginTransaction();
    private static Departamentos d;
    private static Empleados e;
    
    public static void main(String[] args) {
        insertarDepartamento();
        insertarEmpleado();
    }

    private static void insertarDepartamento() {
        d = new Departamentos(60, "MARKETING", "GUADALAJARA");
        s.save(d);
        System.out.println(d.toString());
    }

    private static void insertarEmpleado() {
        d = (Departamentos) s.get(Departamentos.class, 10);
        Empleados dir = (Empleados) s.get(Empleados.class, 7499);
        Date date = new Date("2020/02/15");
        e = new Empleados(4455, d, dir, "PÉREZ", "VENDEDOR", date, 1500, 10);
        s.save(e);
        System.out.println(e.toString());
    }
    
}

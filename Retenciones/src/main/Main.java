package main;

import POJOS.Departamento;
import POJOS.Empleado;
import POJOS.Retenciones;
import POJOS.RetencionesId;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Thomas Freitas
 */
public class Main {
    //La tabla de Rentenciones la hice mediante la interfaz gráfica de mi IDE (Estoy utilzando Netbeans 8.2)
    
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        
        Empleado e;
        Departamento d;
        Retenciones r;
        RetencionesId rId;
        
        System.out.println("---------------------LISTA DE EMPLEADOS---------------------");
        Query q = s.createQuery("FROM Empleado");
        List<Empleado> empleados = q.list();
        for (Empleado empleado : empleados) {
            System.out.println(empleado.toString());
            d = empleado.getDepartamento();
            double salarioNeto, retencion, salarioBruto = empleado.getSalario();
            if (d != null){
                if (empleado.getNombre() != null)
                    rId = new RetencionesId(empleado.getNombre(), d.getNombre());
                else rId = new RetencionesId("NO NAME PROVIDED", d.getNombre());
                if(salarioBruto<1500){
                    retencion = salarioBruto*0.1;
                } else if(salarioBruto<2500){
                    retencion = salarioBruto*0.15;
                } else{
                    retencion = salarioBruto*0.2;
                }
                salarioNeto = salarioBruto-retencion;
                
                if (salarioNeto<750) {
                    salarioNeto=1000;
                }else if(salarioNeto<2200){
                    salarioNeto=2300;
                } else if(salarioNeto<3200){
                    salarioNeto = 3300;
                }
                
                r = new Retenciones(rId, salarioBruto, retencion, salarioNeto);
                s.save(r);
            } else System.err.println("Ese departamento no existe");
        }
        
        System.out.println("\n\n---------------------LISTA DE RETENCIONES---------------------");
        Query q1 = s.createQuery("From Retenciones");
        List<Retenciones> retenciones = q1.list();
        for (Retenciones retencion : retenciones) {
            System.out.println(retencion.toString());
        }
        
        t.commit();
        System.exit(0);
    }
    
}

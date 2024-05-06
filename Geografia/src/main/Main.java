package main;

import java.util.Scanner;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Thomas Freitas
 */
public class Main {

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        
        int accion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Añadir una nueva localidad\n2. Mostrar los datos de una localidad\n"
                + "3. Actualizar el censo de una localidad\n4. Eliminar una localidad\n5. Mostrar todas "
                + "las localidades\n6. Salir");
            accion = sc.nextInt();
            
            if(accion<1 || accion > 6)
              System.err.println("ERROR: Por favor, introduzca un valor válido de operación a realizar");
            else{
                switch(accion){
                    case 1:
                        aniadir();
                        break;
                    case 2:
                        mostrarDatos();
                        break;
                    case 3:
                        actualizarCenso();
                        break;
                    case 4:
                        eliminar();
                        break;
                    case 5:
                        mostrarTodas();
                        break;
                    default:
                        break;
                }
            }
        } while (accion!=6);
    }

    private static void aniadir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void mostrarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void actualizarCenso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void mostrarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

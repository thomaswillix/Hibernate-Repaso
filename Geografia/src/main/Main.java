package main;

import POJOS.Localidades;
import java.util.Scanner;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author Thomas Freitas
 */
public class Main {
    /* 
       NOTA IMPORTANTE: Cuando haya un error en la creación de los archivos (véase, que cuando se no detecten las clases
       a las que queremos hacer ingeniería inversa), lo más probable es que haya habido un error en la creación del 
       hibernate.cfg.xml. La solución que he implementado es volver a crearlo y asegurarme de que esté todo bien.
    */
    private static Localidades l;
    private static Scanner sc = new Scanner(System.in);
    private static SessionFactory sf = HibernateUtil.sessionFactory();
    private static Session s = sf.openSession();
        
    public static void main(String[] args) {
        Transaction t = s.beginTransaction();
        
        int accion;
        do {
            System.out.println("1. Añadir una nueva localidad\n2. Mostrar los datos de una localidad\n"
                + "3. Actualizar el censo de una localidad\n4. Eliminar una localidad\n5. Mostrar todas "
                + "las localidades\n6. Salir");
            accion = sc.nextInt();
            sc.nextLine();
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
        System.out.println("Para añadir una localidad necesitaremos su código, nombre, censo, "
                + "habitantes y el nombre de la provincia.\n\nEmpecemos por el código: ");
        String codLocalidad, nombreLocalidad, nombreProvincia;
        int censo, habitantes;
        do {
            codLocalidad = sc.nextLine();
            l = (Localidades) s.get(Localidades.class, codLocalidad);
            if(l != null)
              System.err.println("Ese código ya le pertenece a otra localidad");
            else if (codLocalidad.isEmpty()) {
                System.err.println("La cadena está vacía");
                l = null;
            }
        } while (l != null);
        //l = new Localidades(codLocalidad, nombre, Integer.SIZE, Integer.BYTES, nomProv);
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

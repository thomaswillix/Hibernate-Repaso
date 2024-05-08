package main;

import POJOS.Localidades;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
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
            System.out.println("\n1. Añadir una nueva localidad\n2. Mostrar los datos de una localidad\n"
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
        t.commit();
        System.exit(0);
    }

    private static void aniadir() {
        System.out.println("\nPara añadir una localidad necesitaremos su código, nombre, censo, "
                + "habitantes y el nombre de la provincia.\n\nEmpecemos por el código: ");
        String codLocalidad, nombreLocalidad, nombreProvincia;
        int censo, habitantes;
        do {
            codLocalidad = sc.nextLine().trim();
            l = (Localidades) s.get(Localidades.class, codLocalidad);
            if(l != null)
              System.err.println("Ese código ya le pertenece a otra localidad");
            else if (codLocalidad.isEmpty()) {
                System.err.println("El código está vacío");
                l = null;
            }
        } while (l != null);
        
        do {
            System.out.println("\nNombre de la localidad: ");            
            nombreLocalidad = sc.nextLine().trim();
            if (nombreLocalidad.isEmpty()) System.err.println("El nombre de la localidad está vacío");
        } while (nombreLocalidad.isEmpty());
        
        do {
            System.out.println("\nNombre de la provincia: ");            
            nombreProvincia = sc.nextLine().trim();
            if (nombreProvincia.isEmpty()) System.err.println("El nombre de la provincia está vacío");
        } while (nombreProvincia.isEmpty());
        
        do {
            System.out.println("\nCenso: ");            
            censo = sc.nextInt();
            if (censo<0) System.err.println("El censo no puede ser negativo");
        } while (censo<0);
        
        do {
            System.out.println("\nHabitantes: ");            
            habitantes = sc.nextInt();
            if (habitantes<0) {
                System.err.println("El número de habitantes no puede ser negativo");
            } else if(habitantes<censo) System.err.println("El número de habitantes no puede ser menor al censo");
            
        } while (habitantes<0 || habitantes < censo);
        
        l = new Localidades(codLocalidad, nombreLocalidad, censo, habitantes, nombreProvincia);
        s.save(l);
    }

    private static void mostrarDatos() {
        System.out.println("\nDeme el código de una localidad para obtener todos sus datos.");
        do {
            String cod = sc.nextLine();
            l = (Localidades) s.get(Localidades.class, cod);
            if (l==null) {
                System.err.println("Ese código no pertenece a ninguna localidad.\n\nVuelva a introducir el código: ");
            }
        } while (l==null);
        
        System.out.println(l.toString());
    }

    private static void actualizarCenso() {
        System.out.println("\nDeme el código de una localidad para después actualizar su censo.");
        do {
            String cod = sc.nextLine();
            l = (Localidades) s.get(Localidades.class, cod);
            if (l==null) {
                System.err.println("Ese código no pertenece a ninguna localidad.\n\nVuelva a introducir el código: ");
            }
        } while (l==null);
        
        System.out.println("El censo actual es: " + l.getCenso() +"\n\n¿Qué nuevo número de censo quiere darle?");
        int censo;
        do {            
            censo = sc.nextInt();
            if (censo > l.getHabitantes()) System.err.println("El censo de "  + l.getNombre()+ " no puede ser superior a su número de "
                    + "habitantes: "+ l.getHabitantes());
            
        } while (censo > l.getHabitantes());
        System.out.println("Censo de " + l.getNombre()+ " modificado a: " + censo);
        l.setCenso(censo);
        s.saveOrUpdate(l);
    }

    private static void eliminar() {
        System.out.println("\nDeme el código de una localidad para eliminarla: ");
        do {
            String cod = sc.nextLine();
            l = (Localidades) s.get(Localidades.class, cod);
            if (l==null) {
                System.err.println("Ese código no pertenece a ninguna localidad.\n\nVuelva a introducir el código: ");
            }
        } while (l==null);
        System.out.println("La localidad con código: " + l.getCodLoc()+ " y nombre " + l.getNombre() + " ha sido eliminada");
        s.delete(l);
    }

    private static void mostrarTodas() {
        System.out.println("\nAquí tiene todos los datos de la tabla de Localidades actualmente:");
        Query q = s.createQuery("FROM Localidades");
        List<Localidades> lista = q.list();
        for (Localidades l : lista) {
            System.out.println(l.toString());
        }
    }
    
}

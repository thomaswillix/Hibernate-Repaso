package main;

import POJOS.Productos;
import POJOS.Ventas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Thomas Freitas
 */
/**
 * NOTA IMPORTANTE: CREACIÓN DE UN PROYECTO HIBERNATE
 *
 * 1º Se creará una base de datos mediante el apartado de prestaciones de forma gráfica en el propio Netbeans
 * 2º Se introducirá el .sql de creación de la base de datos 
 * 3º Se crea el hibernate.cfg (Asistente de configuración de Hibernate) 
 * 4º Se crea el asistente de ingeniería inversa de Hibernate (hibernate.reveng.xml) 
 * 5º Se crean los archivos de mapas de Hibernate y POJOs basados en una base de datos relacional existente (el package "POJOS" se pondrá manualmente) 
 * 6º Se mira si las clases autogeneradas poseeen cualquier tipo de datos que sea incompatible con nuestro código en Java, si es así lo cambiamos por uno pertinente 
 * 7º Se crea el HibernateUtil.java en base a lo que nos dan en la chuleta y se pone en el main package 
 * 8º Se podrá proceder con el código en el Main.
 */
public class Main {

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

    // Ver los datos del producto 7.
        System.out.println("\n------------------- DATOS DEL PRODUCTO CON ID 7 -------------------");
        Productos p;
        p = (Productos) s.get(Productos.class, "7");
        if (p == null) {
            System.out.println("El producto no existe");
        } else {
            System.out.println(p.toString());
        }

    // Comprobar que la venta V30 exista.
        System.out.println("\n-------------------¿V30 EXISTE?-------------------");
        Ventas v;
        v = (Ventas) s.get(Ventas.class, "v30");
        Scanner sc = new Scanner(System.in);
        if (v == null) {
            System.out.println("LA VENTA V30 NO EXISTE\n\nProceda con su alta:");
            do {
                System.out.println("\nCódigo de producto:");
                String codProducto = sc.nextLine();
                p = (Productos) s.get(Productos.class, codProducto);
                if (p == null) {
                    System.err.println("Ese producto no existe");
                }
            } while (p == null);
            boolean fechaIncorrecta = false;
            String fecha;
            LocalDate fechaL = null;
            do {
                System.out.println("\nFecha de la venta:");
                fecha = sc.nextLine();
                try {
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    fechaL = LocalDate.parse(fecha, formato);
                    fechaIncorrecta = false;
                    if (fechaL.isAfter(LocalDate.now())) {
                        System.err.println("La fecha de la venta no puede ser posterior a la actual");
                    }
                } catch (Exception ex) {
                    fechaIncorrecta = true;
                    System.err.println("Formato de la fecha incorrecto el formato es: yyyy/MM/dd");
                }
            } while (fechaIncorrecta || fechaL.isAfter(LocalDate.now()));

            System.out.println("\nUnidades vendidas:");
            int uds = sc.nextInt();
            v = new Ventas("V30", p, fecha, uds);
            s.save(v);
        } else {
            v.toString();
        }

    // Obtener los datos de la venta v10 y los datos del producto asociado
        System.out.println("\n-------------------DATOS DE V10-------------------");
        v = (Ventas) s.get(Ventas.class, "V10");
        p = (Productos) s.get(Productos.class, v.getProductos().getCodProducto());
        System.out.println("DATOS DE LA VENTA: " + v.toString() + "\nDATOS DEL PRODUCTO: " + p.toString());
    // Dar de alta un nuevo producto y una venta. Los datos se pedirán por teclado, comprobando que no existe.
        System.out.println("\n-------------------ALTA DE UN PRODUCTO-------------------");
        sc.nextLine();
        String codS;
        do {
            System.out.println("\nCódigo de producto (número entero):");
            int cod = sc.nextInt();
            codS = String.valueOf(cod);
            p = (Productos) s.get(Productos.class, codS);
            if (p != null) {
                System.err.println("Ese producto ya existe");
            }
        } while (p != null);
        sc.nextLine();
        
        System.out.println("\nNombre:");
        String nombre =  sc.nextLine();
        p = new Productos(codS, nombre);
        s.save(p);
        System.out.println("\n-------------------ALTA DE UNA VENTA-------------------");
        String nombreVenta;
        do {
            System.out.println("\nNombre de la venta:");
            nombreVenta = sc.nextLine();
            v = (Ventas) s.get(Ventas.class, nombreVenta);
            if (v != null)
                System.err.println("Ese nombre ya está siendo utilizado");
        } while (v != null);
        do {
            System.out.println("\nCódigo de producto:");
            String codProducto = sc.nextLine();
            p = (Productos) s.get(Productos.class, codProducto);
            if (p == null) {
                System.err.println("Ese producto no existe");
            }
        } while (p == null);
        boolean fechaIncorrecta = false;
        String fecha;
        LocalDate fechaL = null;
        do {
            System.out.println("\nFecha de la venta:");
            fecha = sc.nextLine();
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                fechaL = LocalDate.parse(fecha, formato);
                fechaIncorrecta = false;
                if (fechaL.isAfter(LocalDate.now())) {
                    System.err.println("La fecha de la venta no puede ser posterior a la actual");
                }
            } catch (Exception ex) {
                fechaIncorrecta = true;
                System.err.println("Formato de la fecha incorrecto el formato es: yyyy/MM/dd");
            }
        } while (fechaIncorrecta || fechaL.isAfter(LocalDate.now()));

        System.out.println("\nUnidades vendidas:");
        int uds = sc.nextInt();
        v = new Ventas(nombreVenta, p, fecha, uds);
        s.save(v);
    // Borrar el producto v15. Si no existe, se mostrará un mensaje indicándolo.
        System.out.println("\n------------- BAJA DE UN PRODUCTO -------------");
        v = (Ventas) s.get(Ventas.class, "V15");
        if (v == null)
            System.out.println("Esa venta no existe");
        else {
            s.delete(v);
            System.out.println("VENTA BORRADA CON ÉXITO");
        }
    // Actualizar el stock de productos, descontando las unidades vendidas, teniendo en cuenta que el stock no puede ser negativo.
        System.out.println("\n-------------- PRODUCTOS SIN ACTUALIZAR --------------");
        Query q = s.createQuery("FROM Productos");
        List<Productos> listaP = q.list();
        for (int i = 0; i < listaP.size(); i++) {
            System.out.println(listaP.get(i).toString());
        }
        
        System.out.println("\n-------------- ACTUALIZACIÓN DE PRODUCTOS --------------");
        int stock, udsVendidas;
        Query q1 = s.createQuery("FROM Ventas");
        List<Ventas> listaV = q1.list();
        for (int i = 0; i < listaV.size(); i++) {
            p = (Productos) s.get(Productos.class, listaV.get(i).getProductos().getCodProducto());
            if(p==null)
                System.err.println("EL PRODUCTO " +listaV.get(i).getProductos().getCodProducto() +" NO EXISTE");
            else {
                stock = p.getStock();
                v = (Ventas) s.get(Ventas.class, listaV.get(i).getCodVenta());
                udsVendidas = v.getUnidadesVendidas();
                if(stock - udsVendidas >= 0)
                  p.setStock(stock - udsVendidas);
                else System.err.println("LA VENTA " + v.getCodVenta() + " NO SE PUDO EFECTUAR.");
            }
        }
        System.out.println("\n-------------- PRODUCTOS ACTUALIZADOS --------------");
        Query q2 = s.createQuery("FROM Productos");
        List<Productos> listaP1 = q2.list();
        for (int i = 0; i < listaP1.size(); i++) {
            System.out.println(listaP1.get(i).toString());
        }
        t.commit();
    }
}
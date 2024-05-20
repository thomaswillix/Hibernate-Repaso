package main;

import POJOS.Productos;
import POJOS.Ventas;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Thomas Freitas
 */
public class Main {

    public static void main(String[] args) {
        File f = new File("ventas.dat");
        
        Ventas v;
        Productos p;
        int id;
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction t;
        
        System.out.println("------- LISTA DE VENTAS -------");        
        Query q = s.createQuery("FROM Ventas");
        List<Ventas> lista = q.list();
        id = lista.size() + 1;
        List<String> vendedores = new ArrayList<>();
        for (Ventas venta : lista) {
            System.out.println(venta.toString());
            if (!vendedores.contains(venta.getCodVend())) {
                vendedores.add(venta.getCodVend());
            }
        }
        
        List<Ventas> listaBin = new ArrayList<>();
        String codVend;
        int unidades;
        
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(f));
            try {
                while(true){
                    codVend = dis.readUTF();
                    p = (Productos) s.get(Productos.class, dis.readInt());
                    unidades = dis.readInt();
                    v = new Ventas(codVend, p, unidades);
                    listaBin.add(v);
                }
            } catch (EOFException e) {
                System.err.println("End of file");
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        double ganancias;
        System.out.println("\n\n--------- Lista de ventas en el archivo .dat ---------");
        for (Ventas venta : listaBin) {
            t = s.beginTransaction();
            if (venta.getProductos() == null) {
                System.err.println("ERROR: EL PRODUCTO ASOCIADO A LA VENTA NO EXISTE");
            }else{ 
                System.out.println(venta.getCodVend() + ", "  + venta.getProductos().getNombre()+", " + venta.getUnidades());
                if(!vendedores.contains(venta.getCodVend())){
                    System.out.println("ESE VENDEDOR NO FIGURA EN LA LISTA DE VENDEDORES, SE LE DARÁ DE ALTA");
                    vendedores.add(venta.getCodVend());
                    p = (Productos) s.get(Productos.class, venta.getProductos().getCodprod());
                    ganancias = p.getPrecio() * venta.getUnidades();
                    v = new Ventas(id, venta.getProductos(), venta.getCodVend(), venta.getUnidades(), ganancias);
                    id++;
                    s.save(v);
                }else{
                    for (Ventas ventaBD : lista) {
                        if (ventaBD.getCodVend().equals(venta.getCodVend()) && 
                                ventaBD.getProductos().getCodprod() == venta.getProductos().getCodprod()) {
                     
                            ventaBD.setUnidades(ventaBD.getUnidades() + venta.getUnidades());
                            p = (Productos) s.get(Productos.class, venta.getProductos().getCodprod());
                            ganancias = p.getPrecio() * venta.getUnidades();
                            ventaBD.setGanancia(ganancias);
                            s.saveOrUpdate(ventaBD);
                        }
                    }
                }
                t.commit();
                
            }
        }
        System.out.println("\n\n------------ LISTA DE VENTAS TRAS AÑADIR NUEVOS REGISTROS ------------");
        q = s.createQuery("FROM Ventas");
        lista = q.list();
        for (Ventas venta : lista) {
            System.out.println(venta.toString());
        }
        
        s.close();
        sf.close();
    }
    
}

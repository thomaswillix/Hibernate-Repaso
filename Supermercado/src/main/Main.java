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
        
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        //Transaction t = s.beginTransaction();
        
        System.out.println("------- VENTAS -------");        
        Query q = s.createQuery("FROM Ventas");
        List<Ventas> lista = q.list();
        List<String> vendedores = new ArrayList<>();
        for (Ventas venta : lista) {
            System.out.println(venta.toString());
            if (!vendedores.contains(venta.getCodVend())) {
                vendedores.add(venta.getCodVend());
            }
        }
        
        lista.removeAll(lista);
        
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
                    lista.add(v);
                }
            } catch (EOFException e) {
                System.err.println("End of file");
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        s.close();
        sf.close();
    }
    
}

package main;

import POJOS.Arma;
import POJOS.Escudo;
import POJOS.Personaje;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        File f = new File("jugadas.txt");
        ArrayList<String> lineas = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            String linea;
            try {
                while((linea = bf.readLine())!= null){
                    lineas.add(linea);
                }
            } catch (EOFException e) {
                System.err.println("END OF FILE");
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        }
        
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        Personaje p1;
        Personaje p2;
        Arma a;
        Escudo e;
        System.out.println("----------------- LISTA INICIAL -----------------");
        Query q = s.createQuery("FROM Personaje");
        List<Personaje> lista = q.list();
        for (Personaje personaje : lista) {
            System.out.println(personaje.toString());
        }
        
        String[] datos;
        int p1VidaIni, p2VidaIni;
        for (String linea : lineas) {
            datos = linea.split(",");
            p1 = (Personaje) s.get(Personaje.class, Integer.parseInt(datos[0]));
            a = (Arma) s.get(Arma.class, Integer.parseInt(datos[1]));
            p2 = (Personaje) s.get(Personaje.class, Integer.parseInt(datos[2]));
            e = (Escudo) s.get(Escudo.class, Integer.parseInt(datos[3]));
            if (p1 == null || p2 == null)
              System.err.println("UNO DE LOS DOS PERSONAJES NO EXISTE");
            else if (a == null)
              System.err.println("EL ARMA NO EXISTE");
            else if (e == null)
              System.err.println("EL ESCUDO NO EXISTE");
            else{
                p1VidaIni = p1.getVida();
                p2VidaIni = p2.getVida();
                if (a.getDano()> e.getDefensa()) {
                    p2.setVida(p2.getVida() - (a.getDano() - e.getDefensa()));
                    s.save(p2);
                } else if (a.getDano() < e.getDefensa()) {
                    p1.setVida(p1.getVida() - (e.getDefensa() - a.getDano()));
                    s.save(p1);
                }
                
                if (p1.getVida() < p1VidaIni/2) {
                    System.err.println("EL JUGADOR " + p1.getNombre()+ " HA SIDO ELIMINADO");
                    s.delete(p1);
                }
                if (p2.getVida()< p2VidaIni / 2){
                    System.err.println("EL JUGADOR " + p2.getNombre()+ " HA SIDO ELIMINADO");
                    s.delete(p2);
                }
            }
        }
        t.commit();
        
        System.out.println("----------------- LISTA ACTUALIZADA -----------------");
        Query q1 = s.createQuery("FROM Personaje");
        List<Personaje> lista1 = q1.list();
        for (Personaje personaje : lista1) {
            System.out.println(personaje.toString());
        }

    }   
}

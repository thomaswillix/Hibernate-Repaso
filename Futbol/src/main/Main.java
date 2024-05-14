package main;

import POJOS.Equipos;
import POJOS.Estadisticas;
import POJOS.Partidos;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
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
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        Partidos p;
        System.out.println("------------- LISTA DE PARTIDOS -------------");
        Query q = s.createQuery("FROM Partidos");
        List<Partidos> partidos = q.list();
        for (Partidos partido : partidos) {
            System.out.println(partido.toString());
        }

        //INTRODUCCIÓN DE DATOS EN LA TABLA DE ESTADISTICAS
        HashMap<Equipos, Estadisticas> stats = new HashMap<>();
        Equipos equipoLocal, equipoVisitante;
        Estadisticas statsLocal = null, statsVisitante = null;
        int golesLocal, golesVisitante;
        boolean ganaVisitante = false, ganaLocal = false;

        for (Partidos partido : partidos) {
            equipoLocal = (Equipos) s.get(Equipos.class, partido.getEquiposByCodLocal().getCodEquipo());
            equipoVisitante = (Equipos) s.get(Equipos.class, partido.getEquiposByCodVisitante().getCodEquipo());
            statsLocal = new Estadisticas(equipoLocal.getCodEquipo(), 0, 0, 0, 0, 0, 0);
            statsVisitante = new Estadisticas(equipoVisitante.getCodEquipo(), 0, 0, 0, 0, 0, 0);
            golesLocal = partido.getGolLocal();
            golesVisitante = partido.getGolVisitante();
            if (equipoLocal == null) {
                System.err.println("EL EQUIPO LOCAL DEL PARTIDO " + partido.getCodPartido() + " NO EXISTE EN LA BASE DE DATOS");
            } else {

                if (!stats.containsKey(equipoLocal)) {
                    statsLocal.setCodEquipo(equipoLocal.getCodEquipo());
                    statsLocal.setGolFav(golesLocal);
                    statsLocal.setGolCont(golesVisitante);
                    if (partido.getGolLocal() > partido.getGolVisitante()) {
                        statsLocal.setPganados(1);
                        statsLocal.setPempatados(0);
                        statsLocal.setPperdidos(0);
                    } else if (partido.getGolLocal() == partido.getGolVisitante()) {
                        statsLocal.setPempatados(1);
                        statsLocal.setPperdidos(0);
                        statsLocal.setPganados(0);
                    } else {
                        statsLocal.setPempatados(0);
                        statsLocal.setPperdidos(1);
                        statsLocal.setPganados(0);
                    }
                    statsLocal.setPuntos(3 * (statsLocal.getPganados() + statsLocal.getPempatados()));
                    stats.put(equipoLocal, statsLocal);
                } else {
                    statsLocal = stats.get(equipoLocal);

                    if (partido.getGolLocal() > partido.getGolVisitante()) {
                        statsLocal.setPganados(statsLocal.getPganados() + 1);
                    } else if (partido.getGolLocal() == partido.getGolVisitante()) {
                        statsLocal.setPempatados(statsLocal.getPempatados() + 1);
                    } else {
                        statsLocal.setPperdidos(statsLocal.getPperdidos() + 1);
                    }
                    statsLocal.setPuntos(3 * (statsLocal.getPganados() + statsLocal.getPempatados()));
                    statsLocal.setGolFav(statsLocal.getGolFav() + golesLocal);
                    statsLocal.setGolCont(statsLocal.getGolCont() + golesVisitante);
                    stats.put(equipoLocal, statsLocal);
                }
            }
            if (equipoVisitante == null) {
                System.err.println("EL EQUIPO VISITANTE DEL PARTIDO " +partido.getCodPartido() + " NO EXISTE EN LA BASE DE DATOS");
            } else {

                if (!stats.containsKey(equipoVisitante)) {
                    statsVisitante.setCodEquipo(equipoVisitante.getCodEquipo());
                    statsVisitante.setGolFav(golesVisitante);
                    statsVisitante.setGolCont(golesLocal);
                    if (partido.getGolVisitante() > partido.getGolLocal()) {
                        statsVisitante.setPganados(1);
                        statsVisitante.setPempatados(0);
                        statsVisitante.setPperdidos(0);
                    } else if (partido.getGolLocal() == partido.getGolVisitante()) {
                        statsVisitante.setPempatados(1);
                        statsVisitante.setPperdidos(0);
                        statsVisitante.setPganados(0);
                    } else {
                        statsVisitante.setPempatados(0);
                        statsVisitante.setPperdidos(1);
                        statsVisitante.setPganados(0);
                    }
                    statsVisitante.setPuntos(3 * (statsVisitante.getPganados() + statsVisitante.getPempatados()));
                    stats.put(equipoVisitante, statsVisitante);
                } else {
                    statsVisitante = stats.get(equipoVisitante);

                    if (partido.getGolVisitante() > partido.getGolLocal()) {
                        statsVisitante.setPganados(statsVisitante.getPganados() + 1);
                    } else if (partido.getGolVisitante() == partido.getGolLocal()) {
                        statsVisitante.setPempatados(statsVisitante.getPempatados() + 1);
                    } else {
                        statsVisitante.setPperdidos(statsVisitante.getPperdidos() + 1);
                    }
                    statsVisitante.setPuntos(3 * (statsVisitante.getPganados() + statsVisitante.getPempatados()));
                    statsVisitante.setGolFav(statsVisitante.getGolFav() + golesVisitante);
                    statsVisitante.setGolCont(statsVisitante.getGolCont() + golesLocal);
                    stats.put(equipoVisitante, statsVisitante);
                }
            }
        }
        System.out.println("\n\n-------------- ESTADISTICAS CONTENIDAS EN UN HASHMAP DE EQUIPOS , ESTADISTICAS PARA HACER LA TABLA DE ESTADISTICAS -------------- ");
        for (Estadisticas es : stats.values()) {
            s.save(es);
            System.out.println(es.toString());
        }
        t.commit();
        
        //LCTURA DEL ARCHIVO BINARIO Y ACTUALIZACIÓN
        System.out.println("\n\n------------- LISTA DE ESTADISTICAS SIN ACTUALIZAR -------------");
        Query q1 = s.createQuery("FROM Estadisticas");
        List<Estadisticas> lista = q1.list();
        for (Estadisticas estad : lista) {
            System.out.println(estad.toString());
        }
        
        File f = new File("futbol.bin");
        
        String operacion, codEquipo;
        int ganados, perdidos, empatados, golesFav, golesCont;
        Estadisticas es;
        Scanner sc = new Scanner(System.in);
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(f));
            try {
                System.out.println("\n\n-------------- LECTURA DEL ARCHIVO BINARIO --------------");
                while(true){
                    operacion = dis.readUTF();
                    codEquipo = dis.readUTF();
                    ganados = dis.readInt();
                    perdidos = dis.readInt();
                    empatados = dis.readInt();
                    System.out.println(operacion + " " + codEquipo + " " + ganados + " " + perdidos + " " + empatados);
                    
                    t = s.beginTransaction();
                    
                    switch (operacion){
                        case "A":
                            es = (Estadisticas) s.get(Estadisticas.class, codEquipo);
                            if (es != null){
                                System.err.println("Ese registro ya existe");
                            } else{
                                System.out.println("INTRODUCE LOS PARTIDOS GANADOS: ");
                                ganados = sc.nextInt();
                                System.out.println("INTRODUCE LOS PARTIDOS PERDIDOS: ");
                                perdidos = sc.nextInt();
                                System.out.println("INTRODUCE LOS PARTIDOS EMPATADOS: ");
                                empatados = sc.nextInt();
                                System.out.println("INTRODUCE LOS GOLES A FAVOR: ");
                                golesFav = sc.nextInt();
                                System.out.println("INTRODUCE LOS GOLES EN CONTRA: ");
                                golesCont = sc.nextInt();
                                es = new Estadisticas(codEquipo, ganados, perdidos, empatados, golesFav, golesCont,  3*(ganados + empatados));
                                s.save(es);
                            }
                            break;
                        case "M":
                            es = (Estadisticas) s.get(Estadisticas.class, codEquipo);
                            if (es == null) {
                                System.err.println("No se puede modificar el registro pues no existe");
                            }else{
                                es.setPganados(ganados);
                                es.setPperdidos(perdidos);
                                es.setPempatados(empatados);
                                es.setPuntos(3*(ganados+empatados));
                                s.saveOrUpdate(es);
                            }
                            break;
                        case "B":
                          es = (Estadisticas) s.get(Estadisticas.class, codEquipo);
                            if (es == null) {
                                System.err.println("No se puede borrar el registro pues no existe");
                            }else{
                                s.delete(es);
                            }
                            break;
                    }
                    t.commit();
                }
            } catch (EOFException e) {
                System.err.println("END OF FILE");
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\n\n------------- LISTA DE ESTADISTICAS ACTUALIZADA -------------");
        Query q2 = s.createQuery("FROM Estadisticas");
        List<Estadisticas> lista2 = q1.list();
        for (Estadisticas estad : lista2) {
            System.out.println(estad.toString());
        }
        
        s.close();
        sf.close();
    }

}

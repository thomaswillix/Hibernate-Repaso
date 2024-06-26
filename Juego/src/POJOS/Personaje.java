package POJOS;
// Generated 06-may-2024 10:24:53 by Hibernate Tools 4.3.1



/**
 * Personaje generated by hbm2java
 */
public class Personaje  implements java.io.Serializable {


     private int id;
     private String nombre;
     private Integer vida;

    public Personaje() {
    }

	
    public Personaje(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Personaje(int id, String nombre, Integer vida) {
       this.id = id;
       this.nombre = nombre;
       this.vida = vida;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getVida() {
        return this.vida;
    }
    
    public void setVida(Integer vida) {
        this.vida = vida;
    }

    @Override
    public String toString() {
        return "Personaje{" + "id=" + id + ", nombre=" + nombre + ", vida=" + vida + '}';
    }
    
}



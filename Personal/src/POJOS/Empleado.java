package POJOS;
// Generated 02-may-2024 17:06:12 by Hibernate Tools 4.3.1



/**
 * Empleado generated by hbm2java
 */
public class Empleado  implements java.io.Serializable {


     private int id;
     private String nombre;
     private String fecha;
     private String profesion;

    public Empleado() {
    }

	
    public Empleado(int id) {
        this.id = id;
    }
    public Empleado(int id, String nombre, String fecha, String profesion) {
       this.id = id;
       this.nombre = nombre;
       this.fecha = fecha;
       this.profesion = profesion;
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
    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getProfesion() {
        return this.profesion;
    }
    
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", profesion=" + profesion + '}';
    }
    
}


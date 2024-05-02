package main;

/**
 *
 * @author Thomas Freitas
 */
public class Registro {
    //Esta clase es una clase de caracter meramente auxiliar para resolver que un empleado no tenga de tipo entero la profesión
     
     private int id;
     private String nombre;
     private String fecha;
     private int profesion;

    public Registro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getProfesion() {
        return profesion;
    }

    public void setProfesion(int profesion) {
        this.profesion = profesion;
    }

    @Override
    public String toString() {
        return "Registro{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", profesion=" + profesion + '}';
    } 
}

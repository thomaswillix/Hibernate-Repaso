package POJOS;
// Generated 29-abr-2024 10:09:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Productos generated by hbm2java
 */
public class Productos  implements java.io.Serializable {


     private String codProducto;
     private String nombre;
     private String lineaProducto;
     private Integer precioUnitario;
     private Integer stock;
     private Set ventases = new HashSet(0);

    public Productos() {
    }

	
    public Productos(String codProducto, String nombre) {
        this.codProducto = codProducto;
        this.nombre = nombre;
    }
    public Productos(String codProducto, String nombre, String lineaProducto, Integer precioUnitario, Integer stock, Set ventases) {
       this.codProducto = codProducto;
       this.nombre = nombre;
       this.lineaProducto = lineaProducto;
       this.precioUnitario = precioUnitario;
       this.stock = stock;
       this.ventases = ventases;
    }
   
    public String getCodProducto() {
        return this.codProducto;
    }
    
    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getLineaProducto() {
        return this.lineaProducto;
    }
    
    public void setLineaProducto(String lineaProducto) {
        this.lineaProducto = lineaProducto;
    }
    public Integer getPrecioUnitario() {
        return this.precioUnitario;
    }
    
    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public Integer getStock() {
        return this.stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Set getVentases() {
        return this.ventases;
    }
    
    public void setVentases(Set ventases) {
        this.ventases = ventases;
    }

    @Override
    public String toString() {
        return "Productos{" + "codProducto=" + codProducto + ", nombre=" + nombre + ", lineaProducto=" + lineaProducto + ", precioUnitario=" + precioUnitario + ", stock=" + stock +'}';
        
    }
    

}


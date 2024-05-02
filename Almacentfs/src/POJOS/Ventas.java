package POJOS;
// Generated 29-abr-2024 10:09:26 by Hibernate Tools 4.3.1



/**
 * Ventas generated by hbm2java
 */
public class Ventas  implements java.io.Serializable {


     private String codVenta;
     private Productos productos;
     private String fechaVenta;
     private Integer unidadesVendidas;

    public Ventas() {
    }

	
    public Ventas(String codVenta, Productos productos) {
        this.codVenta = codVenta;
        this.productos = productos;
    }
    public Ventas(String codVenta, Productos productos, String fechaVenta, Integer unidadesVendidas) {
       this.codVenta = codVenta;
       this.productos = productos;
       this.fechaVenta = fechaVenta;
       this.unidadesVendidas = unidadesVendidas;
    }
   
    public String getCodVenta() {
        return this.codVenta;
    }
    
    public void setCodVenta(String codVenta) {
        this.codVenta = codVenta;
    }
    public Productos getProductos() {
        return this.productos;
    }
    
    public void setProductos(Productos productos) {
        this.productos = productos;
    }
    public String getFechaVenta() {
        return this.fechaVenta;
    }
    
    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    public Integer getUnidadesVendidas() {
        return this.unidadesVendidas;
    }
    
    public void setUnidadesVendidas(Integer unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    @Override
    public String toString() {
        return "Ventas{" + "codVenta=" + codVenta + ", producto=" + productos.getNombre() + ", fechaVenta=" + fechaVenta + ", unidadesVendidas=" + unidadesVendidas.toString() + '}';
    }
    
}



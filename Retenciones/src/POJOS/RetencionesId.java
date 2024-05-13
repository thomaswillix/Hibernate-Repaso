package POJOS;
// Generated 11-may-2024 14:48:12 by Hibernate Tools 4.3.1



/**
 * RetencionesId generated by hbm2java
 */
public class RetencionesId  implements java.io.Serializable {


     private String nombreEmpleado;
     private String nombreDepartamento;

    public RetencionesId() {
    }

    public RetencionesId(String nombreEmpleado, String nombreDepartamento) {
       this.nombreEmpleado = nombreEmpleado;
       this.nombreDepartamento = nombreDepartamento;
    }
   
    public String getNombreEmpleado() {
        return this.nombreEmpleado;
    }
    
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    public String getNombreDepartamento() {
        return this.nombreDepartamento;
    }
    
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RetencionesId) ) return false;
		 RetencionesId castOther = ( RetencionesId ) other; 
         
		 return ( (this.getNombreEmpleado()==castOther.getNombreEmpleado()) || ( this.getNombreEmpleado()!=null && castOther.getNombreEmpleado()!=null && this.getNombreEmpleado().equals(castOther.getNombreEmpleado()) ) )
 && ( (this.getNombreDepartamento()==castOther.getNombreDepartamento()) || ( this.getNombreDepartamento()!=null && castOther.getNombreDepartamento()!=null && this.getNombreDepartamento().equals(castOther.getNombreDepartamento()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getNombreEmpleado() == null ? 0 : this.getNombreEmpleado().hashCode() );
         result = 37 * result + ( getNombreDepartamento() == null ? 0 : this.getNombreDepartamento().hashCode() );
         return result;
   }   


}


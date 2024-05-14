package POJOS;
// Generated 14-may-2024 13:39:47 by Hibernate Tools 4.3.1



/**
 * Partidos generated by hbm2java
 */
public class Partidos  implements java.io.Serializable {


     private String codPartido;
     private Equipos equiposByCodVisitante;
     private Equipos equiposByCodLocal;
     private Integer jornada;
     private Integer golLocal;
     private Integer golVisitante;

    public Partidos() {
    }

	
    public Partidos(String codPartido) {
        this.codPartido = codPartido;
    }
    public Partidos(String codPartido, Equipos equiposByCodVisitante, Equipos equiposByCodLocal, Integer jornada, Integer golLocal, Integer golVisitante) {
       this.codPartido = codPartido;
       this.equiposByCodVisitante = equiposByCodVisitante;
       this.equiposByCodLocal = equiposByCodLocal;
       this.jornada = jornada;
       this.golLocal = golLocal;
       this.golVisitante = golVisitante;
    }
   
    public String getCodPartido() {
        return this.codPartido;
    }
    
    public void setCodPartido(String codPartido) {
        this.codPartido = codPartido;
    }
    public Equipos getEquiposByCodVisitante() {
        return this.equiposByCodVisitante;
    }
    
    public void setEquiposByCodVisitante(Equipos equiposByCodVisitante) {
        this.equiposByCodVisitante = equiposByCodVisitante;
    }
    public Equipos getEquiposByCodLocal() {
        return this.equiposByCodLocal;
    }
    
    public void setEquiposByCodLocal(Equipos equiposByCodLocal) {
        this.equiposByCodLocal = equiposByCodLocal;
    }
    public Integer getJornada() {
        return this.jornada;
    }
    
    public void setJornada(Integer jornada) {
        this.jornada = jornada;
    }
    public Integer getGolLocal() {
        return this.golLocal;
    }
    
    public void setGolLocal(Integer golLocal) {
        this.golLocal = golLocal;
    }
    public Integer getGolVisitante() {
        return this.golVisitante;
    }
    
    public void setGolVisitante(Integer golVisitante) {
        this.golVisitante = golVisitante;
    }

    @Override
    public String toString() {
        return "Partidos{" + "codPartido=" + codPartido + ", equiposByCodVisitante=" + equiposByCodVisitante.getCodEquipo()+ ", equiposByCodLocal=" + equiposByCodLocal.getCodEquipo()+ ", jornada=" + jornada + ", golLocal=" + golLocal + ", golVisitante=" + golVisitante + '}';
    }

}



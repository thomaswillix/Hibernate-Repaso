package POJOS;
// Generated 14-may-2024 13:39:47 by Hibernate Tools 4.3.1



/**
 * Estadisticas generated by hbm2java
 */
public class Estadisticas  implements java.io.Serializable {


     private String codEquipo;
     private Integer pganados;
     private Integer pperdidos;
     private Integer pempatados;
     private Integer golFav;
     private Integer golCont;
     private Integer puntos;

    public Estadisticas() {
    }

	
    public Estadisticas(String codEquipo) {
        this.codEquipo = codEquipo;
    }
    public Estadisticas(String codEquipo, Integer pganados, Integer pperdidos, Integer pempatados, Integer golFav, Integer golCont, Integer puntos) {
       this.codEquipo = codEquipo;
       this.pganados = pganados;
       this.pperdidos = pperdidos;
       this.pempatados = pempatados;
       this.golFav = golFav;
       this.golCont = golCont;
       this.puntos = puntos;
    }
    
    public Estadisticas(String codEquipo, Integer pganados, Integer pperdidos, Integer pempatados, Integer puntos) {
       this.codEquipo = codEquipo;
       this.pganados = pganados;
       this.pperdidos = pperdidos;
       this.pempatados = pempatados;
       this.puntos = puntos;
    }
   
    public String getCodEquipo() {
        return this.codEquipo;
    }
    
    public void setCodEquipo(String codEquipo) {
        this.codEquipo = codEquipo;
    }
    public Integer getPganados() {
        return this.pganados;
    }
    
    public void setPganados(Integer pganados) {
        this.pganados = pganados;
    }
    public Integer getPperdidos() {
        return this.pperdidos;
    }
    
    public void setPperdidos(Integer pperdidos) {
        this.pperdidos = pperdidos;
    }
    public Integer getPempatados() {
        return this.pempatados;
    }
    
    public void setPempatados(Integer pempatados) {
        this.pempatados = pempatados;
    }
    public Integer getGolFav() {
        return this.golFav;
    }
    
    public void setGolFav(Integer golFav) {
        this.golFav = golFav;
    }
    public Integer getGolCont() {
        return this.golCont;
    }
    
    public void setGolCont(Integer golCont) {
        this.golCont = golCont;
    }
    public Integer getPuntos() {
        return this.puntos;
    }
    
    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Estadisticas{" + "codEquipo=" + codEquipo + ", pganados=" + pganados + ", pperdidos=" + pperdidos + ", pempatados=" + pempatados + ", golFav=" + golFav + ", golCont=" + golCont + ", puntos=" + puntos + '}';
    }

}


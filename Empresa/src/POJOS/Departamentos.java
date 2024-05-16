package POJOS;
// Generated 16-may-2024 21:23:39 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Departamentos generated by hbm2java
 */
public class Departamentos  implements java.io.Serializable {


     private int deptNo;
     private String nombre;
     private String loc;
     private Set empleadoses = new HashSet(0);

    public Departamentos() {
    }

	
    public Departamentos(int deptNo) {
        this.deptNo = deptNo;
    }
    public Departamentos(int deptNo, String nombre, String loc, Set empleadoses) {
       this.deptNo = deptNo;
       this.nombre = nombre;
       this.loc = loc;
       this.empleadoses = empleadoses;
    }
    public Departamentos(int deptNo, String nombre, String loc) {
       this.deptNo = deptNo;
       this.nombre = nombre;
       this.loc = loc;
    }
   
    public int getDeptNo() {
        return this.deptNo;
    }
    
    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getLoc() {
        return this.loc;
    }
    
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public Set getEmpleadoses() {
        return this.empleadoses;
    }
    
    public void setEmpleadoses(Set empleadoses) {
        this.empleadoses = empleadoses;
    }

    @Override
    public String toString() {
        return "Departamentos{" + "deptNo=" + deptNo + ", nombre=" + nombre + ", loc=" + loc + '}';
    }
    
}



package POJOS;
// Generated 18-may-2024 18:30:31 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Empleados generated by hbm2java
 */
public class Empleados  implements java.io.Serializable {


     private int empNo;
     private Departamentos departamentos;
     private Empleados empleados;
     private String apellido;
     private String oficio;
     private Date fechaAlt;
     private Float salario;
     private Float comision;
     private Set empleadoses = new HashSet(0);

    public Empleados() {
    }

	
    public Empleados(int empNo, Departamentos departamentos) {
        this.empNo = empNo;
        this.departamentos = departamentos;
    }
    
    public Empleados(int empNo, Departamentos departamentos, Empleados empleados, String apellido, String oficio, Date fechaAlt, Float salario, Float comision, Set empleadoses) {
       this.empNo = empNo;
       this.departamentos = departamentos;
       this.empleados = empleados;
       this.apellido = apellido;
       this.oficio = oficio;
       this.fechaAlt = fechaAlt;
       this.salario = salario;
       this.comision = comision;
       this.empleadoses = empleadoses;
    }
    
    public Empleados(int empNo, Departamentos departamentos, Empleados empleados, String apellido, String oficio, Date fechaAlt, Float salario, Float comision) {
       this.empNo = empNo;
       this.departamentos = departamentos;
       this.empleados = empleados;
       this.apellido = apellido;
       this.oficio = oficio;
       this.fechaAlt = fechaAlt;
       this.salario = salario;
       this.comision = comision;
    }
   
    public int getEmpNo() {
        return this.empNo;
    }
    
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }
    public Departamentos getDepartamentos() {
        return this.departamentos;
    }
    
    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }
    public Empleados getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getOficio() {
        return this.oficio;
    }
    
    public void setOficio(String oficio) {
        this.oficio = oficio;
    }
    public Date getFechaAlt() {
        return this.fechaAlt;
    }
    
    public void setFechaAlt(Date fechaAlt) {
        this.fechaAlt = fechaAlt;
    }
    public Float getSalario() {
        return this.salario;
    }
    
    public void setSalario(Float salario) {
        this.salario = salario;
    }
    public Float getComision() {
        return this.comision;
    }
    
    public void setComision(Float comision) {
        this.comision = comision;
    }
    public Set getEmpleadoses() {
        return this.empleadoses;
    }
    
    public void setEmpleadoses(Set empleadoses) {
        this.empleadoses = empleadoses;
    }

    @Override
    public String toString() {
        return "Empleados{" + "empNo=" + empNo + ", departamentos=" + departamentos.getNombre() + ", dir=" + empleados.getEmpNo() + ", apellido=" + apellido + ", oficio=" + oficio + ", fechaAlt=" + fechaAlt.toString() + ", salario=" + salario + ", comision=" + comision + '}';
    }


}



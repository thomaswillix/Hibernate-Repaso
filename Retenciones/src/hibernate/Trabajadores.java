package hibernate;

public class Trabajadores implements java.io.Serializable {

     private String empNo;
     private String nombre;
     private int salario;
     private int dept_no;
     private static final long serialVersionUID=1L;
     
    public Trabajadores() {
    }

    public Trabajadores(String empNo, String nombre, int salario, int dept_no) {
        this.empNo = empNo;
        this.nombre = nombre;
        this.salario = salario;
        this.dept_no = dept_no;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    @Override
    public String toString() {
        return "Empleado{" + "empNo=" + empNo + ", nombre=" + nombre + ", salario=" + salario + ", dept_no=" + dept_no + '}';
    }
    
}

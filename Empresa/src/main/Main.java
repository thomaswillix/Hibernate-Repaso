package main;

import POJOS.Departamentos;
import POJOS.Empleados;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Thomas
 */
public class Main {

    private static SessionFactory sf = HibernateUtil.sessionFactory();
    private static Session s = sf.openSession();
    private static Transaction t = s.beginTransaction();
    private static Departamentos d;
    private static Empleados e;
    
    public static void main(String[] args) {
        System.out.println("---------- INSERCIÓN DE UN DEPARTAMENTO ----------");
        insertarDepartamento();
        System.out.println("\n---------- INSERCIÓN DE UN EMPLEADO ----------");
        insertarEmpleado();
        System.out.println("\n---------- OPERACIONES ----------");
        operaciones();
        s.close();
        sf.close();
    }

    private static void insertarDepartamento() {
        d = new Departamentos(60, "MARKETING", "GUADALAJARA");
        s.save(d);
        System.out.println(d.toString());
    }

    private static void insertarEmpleado() {
        d = (Departamentos) s.get(Departamentos.class, 10);
        Empleados dir = (Empleados) s.get(Empleados.class, 7499);
        Date date = new Date("2020/02/15");
        e = new Empleados(4455, d, dir, "PÉREZ", "VENDEDOR", date, 1500.00f, 10.00f);
        s.save(e);
        System.out.println(e.toString());
    }
    
    private static void operaciones(){
        int operacion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\nINTRODUZCA LA SIGUIENTE OPERACIÓN A REALIZAR:\n1. Mostrar datos del departamento 20\n"
                    + "2. Comprueba si existe el departamento 11, si no existe mostrará un mensaje indicándolo.\n"
                    + "3. Muestra los datos del departamento 10: Nombre, Localidad, número de empleados y listado "
                    + "de los empleados. Mostrar apellido y salario\n"
                    + "4. Mostrar los empleados del departamento 30.\n"
                    + "5. Borra el empleado 7369.\n"
                    + "6. Modificar el salario y comisión del empleado 7499, cuyos valores se pedirán por teclado.\n"
                    + "7. Salir");
            operacion = sc.nextInt();
            sc.nextLine();

            if (operacion<1 || operacion>7) {
                System.err.println("\nNÚMERO DE OPERACIÓN MAL INTRODUCIDO, POR FAVOR INTRODÚZCALO DE NUEVO");
            }else{
                switch(operacion){
                    case 1:
                        d = (Departamentos) s.get(Departamentos.class, 20);
                        System.out.println(d.toString());
                        break;
                    case 2:
                        d = (Departamentos) s.get(Departamentos.class, 11);
                        if (d==null) System.err.println("EL DEPARTAMENTO CON NÚMERO DE CÓDIGO 11 NO EXISTE");
                        else System.out.println(d.toString());
                        break;
                    case 3:                    
                        d = (Departamentos) s.get(Departamentos.class, 10);
                        if (d!=null){
                            Set<Empleados> empleados = d.getEmpleadoses();
                            System.out.println("Nombre: " + d.getNombre() + ", Localización: " + d.getLoc() + ", Número de empleados: " + d.getEmpleadoses().size() +
                            "\n\nDATOS DE LOS EMPLEADOS: ");
                            for (Empleados empleado : empleados) {
                                System.out.println("Apellido: " + empleado.getApellido() + ", salario: " + empleado.getSalario());
                            }
                        } else System.err.println("EL DEPARTAMENTO NO EXISTE");
                        break;
                    case 4:
                        d = (Departamentos) s.get(Departamentos.class, 30);
                        Set<Empleados> empleados = d.getEmpleadoses();
                        System.out.println("\nDATOS DE LOS EMPLEADOS: ");
                        for (Empleados empleado : empleados) {
                            System.out.println(empleado.toString());
                        }
                        break;
                    case 5:
                        e = (Empleados) s.get(Empleados.class, 7369);
                        int cod = e.getEmpNo();
                        s.delete(e);
                        System.err.println("EMPLEADO CON CÓDIGO " +cod+ " BORRADO CON ÉXITO");
                        break;
                    case 6:
                        e = (Empleados) s.get(Empleados.class, 7499);
                        float salario, comision;
                        do {
                            System.out.println("Salario: ");
                            salario = sc.nextFloat();
                            if (salario<=0) System.err.println("El salario no puede ser cero o negativo");
                        } while (salario <= 0);
                        do {
                            System.out.println("Comision: ");
                            comision = sc.nextFloat();
                            if (comision<=0) System.err.println("La comisión no puede ser cero o negativa");
                        } while (comision <= 0);
                        e.setSalario(salario);
                        e.setComision(comision);
                        System.out.println("Datos actualizados con éxito");
                        s.saveOrUpdate(e);
                        break;
                    default:
                        break;
                }
            }
        } while (operacion!=7);
    }
    
}

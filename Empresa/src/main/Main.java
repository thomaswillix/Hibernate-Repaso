package main;

import POJOS.Departamentos;
import POJOS.Empleados;
import java.util.Date;
import java.util.Scanner;
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
        System.out.println("\n\n---------- INSERCIÓN DE UN EMPLEADO ----------");
        insertarEmpleado();
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
        e = new Empleados(4455, d, dir, "PÉREZ", "VENDEDOR", date, 1500, 10);
        s.save(e);
        System.out.println(e.toString());
    }
    
    private static void operaciones(){
        int operacion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("INTRODUZCA LA SIGUIENTE OPERACIÓN A REALIZAR:\n1. Mostrar datos del departamento 20\n"
                    + "2. Comprueba si existe el departamento 11, si no existe mostrará un mensaje indicándolo.\n"
                    + "3. Muestra los datos del departamento 10: Nombre, Localidad, número de empleados y listado "
                    + "de los empleados. Mostrar apellido y salario\n"
                    + "4. Mostrar los empleados del departamento 30.\n"
                    + "5. Borra el empleado 7369.\n"
                    + "6. Modificar el salario y comisión del empleado 7499, cuyos valores se pedirán por teclado.\n"
                    + "7. Salir");
            operacion = sc.nextInt();
            if (operacion<1 || operacion>7) {
                System.err.println("NÚMERO DE OPERACIÓN MAL INTRODUCIDO, POR FAVOR INTRODÚZCALO DE NUEVO");
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
                    System.out.println(d.getNombre() + " " + d.getLoc() + " " + d.getEmpleadoses().size());
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    break;
            }
            }
        } while (operacion!=7);
    }
    
}

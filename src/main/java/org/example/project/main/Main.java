package org.example.project.main;

import org.example.project.model.Employee;
import org.example.project.repository.EmployeeRepository;
import org.example.project.repository.Repository;
import org.example.project.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        /*try(Connection myConnect = DatabaseConnection.getInstance()){
            Repository<Employee> repo = new EmployeeRepository();

            System.out.println("----LISTANDO----");
            repo.findAll().forEach(System.out::println);//METODO PARA LISTAR.
            //System.out.println(repo.getById(2));

            /*System.out.println("-----INSERTANDO DATOS-----");
            Employee employee = new Employee();
            employee.setCorreo("juan@correo.com");
            employee.setNombre("Juan");
            employee.setApellido("Camargo");
            employee.setPassword("1234567");
            repo.save(employee);*/

            /*System.out.println("----ACTUALIZAR DATOS----");
            Employee employee = new Employee();
            employee.setId(4);
            employee.setCorreo("sebas@correo.com");
            employee.setNombre("Sebastian");
            employee.setApellido("Rodriguez");
            employee.setPassword("789456");
            repo.save(employee);

            System.out.println("----TABLA ACTUALIZADA----");
            repo.findAll().forEach(System.out::println);

            /*System.out.println("----ELIMINANDO----");
            repo.delete(2);

            System.out.println("----TABLA ACTUALIZADA----");
            repo.findAll().forEach(System.out::println);*/

        //---JAVA SWING
        /*SwingApp app = new SwingApp();
        app.setVisible(true);*/

        //TRANSACCIONES
        /*try(Connection myConn = DatabaseConnection.getInstance()){
            if (myConn.getAutoCommit()){
                myConn.setAutoCommit((false));
            }
            try{
                Repository<Employee> repo = new EmployeeRepository(myConn);

                System.out.println("----INSERTAR UN NUEVO REGISTRO----");
                Employee employee = new Employee();
                employee.setCorreo("humm@correo.com");
                employee.setNombre("humberto");
                employee.setApellido("Pereira");
                employee.setPassword("holamundo");
                employee.setCurp("osjduelfosjyefgtad");
                repo.save(employee);
                myConn.commit();
            }catch (SQLException e){
                myConn.rollback();
                throw new RuntimeException();
            }
        }*/

        System.out.println("----LISTANDO TODO----");
        Repository<Employee> repo = new EmployeeRepository();
        repo.findAll().forEach(System.out::println);

        System.out.println("BUSCANDO POR ID");
        System.out.println(repo.getById(5));

    }
}

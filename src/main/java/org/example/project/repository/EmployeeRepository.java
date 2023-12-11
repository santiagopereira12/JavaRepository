package org.example.project.repository;

import org.example.project.model.Employee;
import org.example.project.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee>{

    private Connection getConnection() throws SQLException{
        return DatabaseConnection.getConnection();
    }


    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(Connection myConn = getConnection();
            Statement myStamt = myConn.createStatement();
            ResultSet myResult = myStamt.executeQuery("SELECT * FROM registros")) {
            while (myResult.next()){
                Employee e = createEmployees(myResult);
                employees.add(e);
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try(Connection myConn = getConnection();
            PreparedStatement mystamt = myConn.prepareStatement("SELECT * FROM registros WHERE id = ?")){
            mystamt.setInt(1,id);
            try(ResultSet myRess = mystamt.executeQuery()){
                if (myRess.next()){
                    employee = createEmployees(myRess);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sql;
        if (employee.getId() != null && employee.getId() > 0){
            sql = "UPDATE registros SET correo = ?, nombre = ?, apellido = ?, password = ?, curp = ? WHERE id = ?";
        }else {
            sql = "INSERT INTO registros (correo, nombre, apellido, password, curp) VALUES (?,?,?,?,?)";
        }
        try (Connection myConn = getConnection();
             PreparedStatement myPrep = myConn.prepareStatement(sql)){

            myPrep.setString(1,employee.getCorreo());
            myPrep.setString(2,employee.getNombre());
            myPrep.setString(3,employee.getApellido());
            myPrep.setString(4,employee.getPassword());
            myPrep.setString(5,employee.getCurp());

            if (employee.getId() != null && employee.getId() > 0){
                myPrep.setInt(6,employee.getId());
            }
            myPrep.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try(Connection myConn = getConnection();
            PreparedStatement myPrep = myConn.prepareStatement("DELETE FROM registros WHERE id = ?")){
            myPrep.setInt(1, id);
            myPrep.executeUpdate();
        }
    }

    private Employee createEmployees(ResultSet myResult) throws SQLException {
        Employee e = new Employee();
        e.setId(myResult.getInt("id"));
        e.setCorreo(myResult.getString("correo"));
        e.setNombre(myResult.getString("nombre"));
        e.setApellido(myResult.getString("apellido"));
        e.setPassword(myResult.getString("password"));
        e.setCurp(myResult.getString("curp"));
        return e;
    }
}

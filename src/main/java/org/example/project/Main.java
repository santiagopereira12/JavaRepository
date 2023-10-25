package org.example.project;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        PreparedStatement myPrep = null;
        String url = "jdbc:mysql://localhost:3306/prueba_java";
        String user = "root";
        String password = "Dn09Sv04++";

        try(Connection myConnect = DriverManager.getConnection(url,user,password);
            Statement myStamt = myConnect.createStatement();
            ResultSet myResult = myStamt.executeQuery("SELECT * FROM registros");){

            while (myResult.next()){
                System.out.println(myResult.getString("nombre"));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Algo salio mal");
        }
    }
}

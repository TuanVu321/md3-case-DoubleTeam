package com.codegym.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    String jdbcURL = "jdbc:mysql://localhost:3306/c0220h1dbt";
    String jdbcUsername = "root";
<<<<<<< HEAD
    String jdbcPassword = "11100001";
    /*String jdbcPassword = "12345678";*/
=======
    String jdbcPassword = "123456";

>>>>>>> 066b7e780c6ebec20b4eef15c17920f8b229a236
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(connection!=null){
            System.out.println("Data connection successful");
        }else {
            System.out.println("Data connection failed");
        }
        return connection;
    }
}

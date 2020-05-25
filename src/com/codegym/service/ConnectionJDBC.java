package com.codegym.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    String jdbcURL = "jdbc:mysql://localhost:3306/c0220h1dbt";
    String jdbcUsername = "root";
    String jdbcPassword = "123456";

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

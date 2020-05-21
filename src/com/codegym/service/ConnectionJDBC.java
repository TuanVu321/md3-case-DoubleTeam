package com.codegym.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    String jdbcURL = "jdbc:mysql://localhost:3306/datareview";
    String jdbcUsername = "root";
    String jdbcPassword = "12345678";


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
            System.out.println("thanh cong");
        }else {
            System.out.println("that bai");
        }
        return connection;
    }
}

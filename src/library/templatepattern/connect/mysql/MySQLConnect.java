package library.templatepattern.connect.mysql;

import library.templatepattern.connect.ConnectionTemplate;

import java.sql.*;

public class MySQLConnect extends ConnectionTemplate {
    private static Connection conn;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String jdbcURL = "jdbc:mysql://localhost:3306/c0220h1dbt";

    private static String user = "root";
    private static String password = "123456";

    public MySQLConnect(String jdbcURL) {
        MySQLConnect.jdbcURL = jdbcURL;
    }

    public void setDBDriver() {
        System.out.println("Setting MySQL Database Driver ...");
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Setting MySQL Database Driver successful !");
        } catch (ClassNotFoundException ex) {
            System.err.println("ClassNotFound Exception has been thrown !!!");
            ex.printStackTrace();
        }
    }

    public void setCredentials(String user, String password) {
        System.out.println("Setting credentials for logging MySQL Database ...");
        MySQLConnect.user = user;
        MySQLConnect.password = password;
    }

    public Connection openConnection(String jdbcURL) {
        System.out.println("Connecting to MySQL Database ...");
        try {
            conn = DriverManager.getConnection(jdbcURL, user, password);
            System.out.println("Connection to MySQL Database successful !");
        } catch (SQLException ex) {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }
            System.err.println("Open Connection MySQL failed, try again !!!");
            ex.printStackTrace();
        }

        return conn;
    }
}

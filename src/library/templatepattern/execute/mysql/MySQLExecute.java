package library.templatepattern.execute.mysql;

import library.templatepattern.execute.ExecutionTemplate;
import java.sql.*;

public class MySQLExecute extends ExecutionTemplate {
    private static Statement stmt;
    private static Connection conn;
    private static ResultSet resultSet;

    public MySQLExecute(Connection conn) {
        MySQLExecute.conn = conn;
        try {
            stmt = conn.createStatement();
            System.out.println("Creating Statement successful !");
        } catch (SQLException ex) {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }
            System.err.println("Creating Statement failed, try again !!!");
        }
    }

    public ResultSet execute(String queryStr) {
        try {
            stmt = conn.createStatement();
            if (queryStr.contains("CREATE") | queryStr.contains("create") | queryStr.contains("DROP") | queryStr.contains("drop")
                    | queryStr.contains("INSERT") | queryStr.contains("insert")
                    | queryStr.contains("UPDATE") | queryStr.contains("update")) {
                stmt.executeUpdate(queryStr);
            } else {
                if (stmt.execute(queryStr)) {
                    resultSet = stmt.getResultSet();
                    return resultSet;
                } else {
                    resultSet = stmt.getResultSet();
                    if (!queryStr.contains("alter") && !queryStr.contains("ALTER"))
                        resultSet.close();
                }
            }
            System.out.println("Executing query successful !");
            stmt.close();
        } catch (SQLException ex) {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }
            System.err.println("Executing query failed, try another query !!!");
            ex.printStackTrace();
        }

        return resultSet;
    }
}

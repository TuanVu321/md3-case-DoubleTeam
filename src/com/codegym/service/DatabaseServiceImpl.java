package com.codegym.service;

import com.codegym.model.SignupAccount;

import java.util.List;
import java.util.ArrayList;

import java.sql.*;
import library.templatepattern.connect.mysql.MySQLConnect;
import library.templatepattern.execute.mysql.*;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DatabaseServiceImpl implements DatabaseService {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/c0220h1dbt";
    private String userDB = "root";
<<<<<<< HEAD
    private String passDB = "11100001";
    /*private String passDB = "12345678";*/
=======
    private String passDB = "123456";
>>>>>>> 066b7e780c6ebec20b4eef15c17920f8b229a236
    private static Connection conn;

    private static final String UPDATE_STATUS = "update c0220h1dbt.account set active = 1 where email = ?;";
    private static final String FOREIGN_KEY_CHECKS = "SET FOREIGN_KEY_CHECKS=0";

    public DatabaseServiceImpl(){}

    public Connection setCheckForeignKey() {
        Connection conn = createConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(FOREIGN_KEY_CHECKS);
            pstmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return conn;
    }

    public Connection createConnection() {
        MySQLConnect mysqlConnect = new MySQLConnect(jdbcURL);
        mysqlConnect.setDBDriver();
        mysqlConnect.setCredentials(userDB, passDB);
        conn = mysqlConnect.openConnection(jdbcURL);

        return conn;
    }

    public void registerAccountToDB(SignupAccount account, String emailAddress) {
        String username = account.getUsername();
        String password = account.getPassword();
        String fullname = account.getFullname();
        String email = account.getEmail();
        String address = account.getAddress();
        String phonenumber = account.getPhonenumber();

        try {
            Connection conn = createConnection();

            String sql_query = "insert into c0220h1dbt.account(id_role, username, password, fullname, phone, email, address, active, online)" +
                                        "values(" +
                                                    "2, '" +
                                                    username + "', '" +
                                                    password + "', '" +
                                                    fullname + "', '" +
                                                    phonenumber + "', '" +
                                                    email + "', '" +
                                                    address + "', " +
                                                    "0, 0" +
                                                  ");";

            System.out.println(sql_query);

            MySQLExecute executeObj = new MySQLExecute(conn);
            executeObj.execute(sql_query);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error can not close Exception !!!");
                ex.printStackTrace();
            }
        }

        SendingEmail sendingEmail = new SendingEmail(emailAddress);
        sendingEmail.sendEmail();
    }

    private class SendingEmail {
        private String userEmail;

        public SendingEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public void sendEmail(){
            String email = "cun.kin1985@gmail.com";
            String password = "quy21011985";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(properties, new Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(email, password);
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
                message.setSubject("Email kích hoạt tài khoản Website du lịch");
                message.setText("Để sử dụng hết chức năng Website hãy kích hoạt tài khoản vừa đăng kí ");
                message.setText("Bằng cách click vào đường link dưới đây: " +
                                       "http://localhost:8080/login?action=signin&userEmail=" + userEmail);
                Transport.send(message);
            } catch (Exception ex){
                System.out.println("Sending mail ....." + ex);
            }
        }
    }

    public void updateActiveStatus(String userEmail) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = createConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(UPDATE_STATUS);

            pstmt.setString(1, userEmail);
            System.out.println(pstmt);
            pstmt.executeUpdate();
            conn.commit();

            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex2) {
                System.err.println("Error can not roll back connection !!!");
                ex2.printStackTrace();
            }
        } finally {
            try{
                if(pstmt!=null) {
                    pstmt.close();
                }
            }catch(SQLException ex){
                System.err.println("Error can not close PrepareStatement !!!");
                ex.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException ex){
                System.err.println("Error can not close Connection !!!");
                ex.printStackTrace();
            }
        }
    }

    public List<String> checkAccountExists(String username, String password) {
        try {
            Connection conn = createConnection();
            String sql_query = "select role, username, password, fullname from c0220h1dbt.account " +
                                      "inner join c0220h1dbt.role using(id_role)" +
                                      "where username =  '" + username + "' and password = '" + password + "' and active = 1;";
            System.out.println(sql_query);

            String fullnameUser = "";
            String roleUser = "";

            MySQLExecute executeObj = new MySQLExecute(conn);
            ResultSet resultSet = executeObj.execute(sql_query);
            List<String> inforUser = new ArrayList<>();

            try {
                if (resultSet.next()) {
                    fullnameUser = resultSet.getString("fullname");
                    roleUser = resultSet.getString("role");
                    inforUser.add(fullnameUser);
                    inforUser.add(roleUser);

                    String sql_updateOnline = "update c0220h1dbt.account set online = 1 where username = '" + username + "';";
                    System.out.println(sql_updateOnline);
                    executeObj.execute(sql_updateOnline);

                    return inforUser;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error can not close Exception !!!");
                ex.printStackTrace();
            }
        }
    }

    public void updateOfflineStatus(String account) {
        Connection conn = createConnection();
        String sql_query = "update c0220h1dbt.account set online = 0 where username = '" + account + "';";
        MySQLExecute executeObj = new MySQLExecute(conn);
        executeObj.execute(sql_query);
    }
}

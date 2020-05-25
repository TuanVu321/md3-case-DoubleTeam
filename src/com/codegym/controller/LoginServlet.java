package com.codegym.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.codegym.model.SignupAccount;
import com.codegym.service.DatabaseServiceImpl;

@WebServlet(name="LoginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {
    private final DatabaseServiceImpl databaseService = new DatabaseServiceImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        if (action == null){
            action = "";
        }

        switch(action) {
            case "index":
                showIndexPage(request, response);
                break;
            case "signin":
                activeAccount(request, response);
                showSignInForm(request, response);
                break;
            case "welcome-temp":
                showWelcomeForm(request, response);
                break;
            case "signup":
                showSignUpForm(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "signin":
                signin(request, response);
                break;
            case "signup":
                signup(request, response);
                break;
            default:
                break;
        }
    }

    private void showIndexPage(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("account");
        request.setAttribute("username", username);
    }

    private void showSignUpForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/signup.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showSignInForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/signin.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showWelcomeForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/welcome-temp.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");

        SignupAccount signUpAccount = new SignupAccount();
        signUpAccount.setUsername(username);
        signUpAccount.setPassword(password);
        signUpAccount.setFullname(fullname);
        signUpAccount.setEmail(email);
        signUpAccount.setAddress(address);
        signUpAccount.setPhonenumber(phonenumber);

        databaseService.registerAccountToDB(signUpAccount, email);

        request.setAttribute("email", email);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/confirm-email.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }

    private void signin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<String> inforUser = databaseService.checkAccountExists(username, password);

        String fullnameUser = "";
        fullnameUser = (inforUser != null) ? inforUser.get(0) : "Tai Khoan nay khong ton tai !!!";
        try {
            RequestDispatcher dispatcher = null;
            String typeAccount = null;
            int id_account = 0;
            if (inforUser != null) {
                String typeAccount_sql = "select role from c0220h1dbt.account inner join c0220h1dbt.role using(id_role) where c0220h1dbt.account.username = ?";
                String id_account_query = "select id_account from c0220h1dbt.account where c0220h1dbt.account.username = ?;";
                Connection conn = databaseService.createConnection();
                try {
                    PreparedStatement pstmt = conn.prepareStatement(typeAccount_sql);
                    pstmt.setString(1, username);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        typeAccount = rs.getString("role");
                    }
                    pstmt = conn.prepareStatement(id_account_query);
                    pstmt.setString(1, username);
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        id_account  = rs.getInt("id_account");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                HttpSession session = request.getSession();
                session.setAttribute("usernameLogIn", username);
                session.setAttribute("typeAccountLogIn", typeAccount);
                session.setAttribute("fullname", fullnameUser);
                session.setAttribute("id_account", id_account);
                request.setAttribute("fullnameUser", fullnameUser);
                request.setAttribute("typeAccount", inforUser.get(1));
                dispatcher = request.getRequestDispatcher("view/welcome-to.jsp?action=welcome-to&account=" + fullnameUser + "&typeAccount=" + inforUser.get(1));
            } else {
                dispatcher = request.getRequestDispatcher("view/failed-signin.jsp");
            }
            dispatcher.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }

    private void activeAccount(HttpServletRequest request, HttpServletResponse response) {
        String userEmail = request.getParameter("userEmail");
        databaseService.updateActiveStatus(userEmail);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/signin.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }
}

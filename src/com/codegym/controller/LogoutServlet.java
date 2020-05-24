package com.codegym.controller;

import com.codegym.service.DatabaseServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="LogoutServlet", urlPatterns="/logout")
public class LogoutServlet extends HttpServlet {
    private final DatabaseServiceImpl databaseService = new DatabaseServiceImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        offline(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewservlet");
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    private void offline(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String usernameLogIn = (String)session.getAttribute("usernameLogIn");
        databaseService.updateOfflineStatus(usernameLogIn);
        session.invalidate();
    }
}

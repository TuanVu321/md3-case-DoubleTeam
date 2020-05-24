package com.codegym.controller;

import com.codegym.model.Review;
import com.codegym.service.IndexService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "/viewservlet")
public class IndexServlet extends HttpServlet {
    private IndexService indexService;

    public void init() {
        indexService = new IndexService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        hotReview1(request, response);
    }

    private void hotReview1(HttpServletRequest request, HttpServletResponse response) {
        List<Review> hotReview = indexService.selectTableUsers();
        ArrayList<Review> getTop6 = indexService.getTop6Review(hotReview);
        request.setAttribute("getTop6", getTop6);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

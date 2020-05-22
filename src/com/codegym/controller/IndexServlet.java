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
    public void init(){
        indexService = new IndexService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        hotReview1(request, response);
        hotReview2(request, response);
        hotReview3(request, response);
        hotReview4(request, response);
        hotReview5(request, response);
        hotReview6(request, response);

    }

    private void hotReview1(HttpServletRequest request, HttpServletResponse response) {
        List<Review> hotReview1 = indexService.selectTableUsers(1);
        request.setAttribute("hotReview1", hotReview1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hotReview2(HttpServletRequest request, HttpServletResponse response) {
        List<Review> hotReview2 = indexService.selectTableUsers(2);
        request.setAttribute("hotReview2", hotReview2);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hotReview3(HttpServletRequest request, HttpServletResponse response) {
        List<Review> hotReview3 = indexService.selectTableUsers(3);
        request.setAttribute("hotReview3", hotReview3);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hotReview4(HttpServletRequest request, HttpServletResponse response) {
        List<Review> hotReview4 = indexService.selectTableUsers(4);
        request.setAttribute("hotReview4", hotReview4);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hotReview5(HttpServletRequest request, HttpServletResponse response) {
        List<Review> hotReview5 = indexService.selectTableUsers(5);
        request.setAttribute("hotReview5", hotReview5);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hotReview6(HttpServletRequest request, HttpServletResponse response) {
        List<Review> hotReview6 = indexService.selectTableUsers(6);
        request.setAttribute("hotReview6", hotReview6);
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

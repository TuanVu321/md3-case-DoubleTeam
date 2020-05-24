package com.codegym.controller;

import com.codegym.model.Review;
import com.codegym.service.ReviewDetails;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    ReviewDetails reviewDetails = new ReviewDetails();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
         findByName(request,response);
    }



    private void findByName(HttpServletRequest request, HttpServletResponse response) {
        String name = ""+ request.getParameter("inputName");
        List<Review> listReview = reviewDetails.selectByName(name);
        request.setAttribute("reviewList",listReview);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/search-name.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listByPage(request,response);


    }

    private void findByDate(HttpServletRequest request, HttpServletResponse response) {
        List<Review> listReview = reviewDetails.selectByDay();
        request.setAttribute("listReview",listReview);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/search.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void listByPage(HttpServletRequest request, HttpServletResponse response) {
        String spageid= request.getParameter("pageid");
        if(spageid==null){
            spageid="1";
        }
        int pageid= Integer.parseInt(spageid);
        int total = 10;
        int start;
        int stop;
        if(pageid==1){
            start =0;
            stop = 10;

        }else{
            start = (pageid - 1)*total;
            stop = start+total ;
        }


        List<Review> reviewList = reviewDetails.createReviewListPagnigation(start, stop);
        request.setAttribute("reviewList", reviewList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/search.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }


    }
}

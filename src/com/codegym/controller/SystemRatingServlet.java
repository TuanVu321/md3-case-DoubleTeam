package com.codegym.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.codegym.model.Review;
import com.codegym.model.SignupAccount;
import com.codegym.service.DatabaseServiceImpl;
import com.codegym.service.ReviewServletImpl;
import com.sun.jmx.snmp.internal.SnmpAccessControlModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

@WebServlet(name="SystemRatingServlet", urlPatterns="/rating")
public class SystemRatingServlet extends HttpServlet {
    DatabaseServiceImpl databaseService = new DatabaseServiceImpl();
    private static final String UPDATE_RESULT_RATING = "insert into c0220h1dbt.result_rating(id_review, id_account, name_account, messageClient, rating) " +
                                                                             "values(?, ?, ?, ?, ?); ";
    private static final String UPDATE_POSTS_REVIEW = "update c0220h1dbt.postsreview set id_account = ?, pointevaluate = ? where id_review = ?;";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "showTop10Star":
                showtop10(request, response);
                break;
            default:
                break;
        }
    }

    private void showtop10(HttpServletRequest request, HttpServletResponse response) {
        String query_top10 = "select id_review, name_review, titleposts, pointevaluate from postsreview where pointevaluate > 0.0 order by pointevaluate desc limit 10;";
        Connection conn = databaseService.setCheckForeignKey();
        List<Review> topList = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(query_top10);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id_review = rs.getInt("id_review");
                String name_review = rs.getString("name_review");
                String titleposts = rs.getString("titleposts");
                float pointevaluate = rs.getFloat("pointevaluate");
                Review review = new Review(id_review, name_review, titleposts, pointevaluate);
                topList.add(review);
            }

            request.setAttribute("topList", topList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/sumary-top-10-star.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "rate":
                processRating(request, response);
                break;
            default:
                break;
        }
    }

    private void processRating(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = databaseService.setCheckForeignKey();
        HttpSession session = request.getSession();
        String name_account_review = (String)session.getAttribute("fullname");
        if (name_account_review == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/not-logged-in.jsp");
            try {
                dispatcher.forward(request, response);
                return;
            } catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
        }

        int id_account = (Integer)session.getAttribute("id_account");
        float start = Float.parseFloat(request.getParameter("hdrating"));
        String messageClient = request.getParameter("message");
        int id_review = Integer.parseInt(request.getParameter("reviewId"));

        try {
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_RESULT_RATING);
            pstmt.setInt(1, id_review);
            pstmt.setInt(2, id_account);
            pstmt.setString(3, name_account_review);
            pstmt.setString(4, messageClient);
            pstmt.setFloat(5, start);

            pstmt.execute();

            String getTotalStartReviewString = "select sum(rating) as total_Star from c0220h1dbt.result_rating where id_review = ? group by id_review;";
            pstmt = conn.prepareStatement(getTotalStartReviewString);
            pstmt.setInt(1, id_review);
            ResultSet rs = pstmt.executeQuery();
            float totalStar = 0.0f;
            while (rs.next()) {
                totalStar += rs.getFloat("total_Star");
            }

            pstmt = conn.prepareStatement(UPDATE_POSTS_REVIEW);
            pstmt.setInt(1, id_account);
            pstmt.setFloat(2, totalStar);
            pstmt.setInt(3, id_review);
            pstmt.executeUpdate();

            RequestDispatcher dispatcher = request.getRequestDispatcher("view/confirm-rating.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }
}

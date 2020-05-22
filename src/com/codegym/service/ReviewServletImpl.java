package com.codegym.service;

import com.codegym.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewServletImpl implements ReviewServlet {
    DatabaseServiceImpl databaseService = new DatabaseServiceImpl();
    private static final String SELECT_ALL_POST_REVIEW = "select id_review, name_review, titleposts, content, dateposts, pointevaluate, picture from datareview.postsreview;";
    private static final String SELECT_REVIEW_PAGINATION = "select id_review, name_review, titleposts, content, dateposts, pointevaluate, picture from datareview.postsreview " +
                                                                                    "limit ?, ?";
    private static final String CREATE_NEW_REVIEW = "insert into datareview.postsreview( " +
            "id_destinations, id_account, name_review, titleposts, picture, content, pointevaluate, dateposts" +
            ") values (?,?,?,?,?,?,?,?);";

    @Override
    public Review createNewReview(int destination, int id_account, String name_review, String titleposts, String content, String picture, int pointevalue, java.util.Date dateposts) {
        Connection conn = databaseService.setCheckForeignKey();
        Review review = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(CREATE_NEW_REVIEW);
            pstmt.setInt(1, destination);
            pstmt.setInt(2, id_account);
            pstmt.setString(3, name_review);
            pstmt.setString(4, titleposts);
            pstmt.setString(5, content);
            pstmt.setString(6, picture);
            pstmt.setInt(7, pointevalue);

            java.sql.Date dateSQL = new java.sql.Date(dateposts.getTime());
            pstmt.setDate(8, dateSQL);

            review = new Review(destination, id_account, name_review, titleposts, content, dateposts, pointevalue, picture);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return review;
    }

    @Override
    public List<Review> createReviewList() {
        List<Review> reviewList = new ArrayList<>();
        int id_review = 0;
        String name = "";
        String title = "";
        String content = "";
        Date datePost = null;
        int star = 0;
        String picture = "";
        Connection conn = databaseService.createConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_POST_REVIEW);
            ResultSet rs = pstmt.executeQuery();
            Review review = null;
            while (rs.next()) {
                id_review = rs.getInt("id_review");
                name = rs.getString("name_review");
                title = rs.getString("titleposts");
                content = rs.getString("content");
                datePost = rs.getDate("dateposts");
                star = rs.getInt("pointevaluate");
                picture = rs.getString("picture");

                review = new Review(id_review, name, title, content, datePost, star, picture);
                reviewList.add(review);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reviewList;
    }

    @Override
    public List<Review> createReviewListPagnigation(int start, int total) {
        List<Review> reviewList = new ArrayList<>();
        int id_review = 0;
        String name = "";
        String title = "";
        String content = "";
        Date datePost = null;
        int star = 0;
        String picture = "";
        Connection conn = databaseService.createConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SELECT_REVIEW_PAGINATION);
            pstmt.setInt(1, (start - 1));
            pstmt.setInt(2, total);

            ResultSet rs = pstmt.executeQuery();
            Review review = null;
            while (rs.next()) {
                id_review = rs.getInt("id_review");
                name = rs.getString("name_review");
                title = rs.getString("titleposts");
                content = rs.getString("content");
                datePost = rs.getDate("dateposts");
                star = rs.getInt("pointevaluate");
                picture = rs.getString("picture");

                review = new Review(id_review, name, title, content, datePost, star, picture);
                reviewList.add(review);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reviewList;
    }
}

package com.codegym.service;


import com.codegym.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndexService extends ConnectionJDBC implements IndexITF {

    @Override
    public List<Review> selectTableUsers() {

        List<Review> selectReview = new ArrayList<>();
        String query = "{call selectReviewindex()}";
        Connection connection = getConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id_review = rs.getInt("id_review");
                String nameUser = rs.getString("name_review");
                int poinReview = rs.getInt("pointevaluate");
                String destinations = rs.getString("destinations");
                String title = rs.getString("titleposts");
                String picture = rs.getString("picture");
                selectReview.add(new Review(id_review, nameUser, destinations, title, poinReview, picture));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return selectReview;
    }

    @Override
    public ArrayList<Review> getTop6Review(List<Review> list) {
        ArrayList<Review> getTop6 = new ArrayList<>();
        for (int i = 0 ; i<6; i++) {
            getTop6.add(list.get(i));
        }

        return getTop6;
    }

}

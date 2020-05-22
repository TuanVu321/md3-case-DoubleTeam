package com.codegym.service;


import com.codegym.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndexService extends ConnectionJDBC implements IndexITF {

    @Override
    public List<Review> selectTableUsers(int id) {

        List<Review> selectReview = new ArrayList<>();
        String query = "{call selectNewReview(?)}";
        Connection connection = getConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(query);
                callableStatement.setInt(1,id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String nameUser = rs.getString("name_review");
                int poinReview = rs.getInt("pointevaluate");
                String destinations = rs.getString("destinations");
                String title = rs.getString("titleposts");
                String picture = rs.getString("picture");
                selectReview.add(new Review(id, nameUser, destinations, title, poinReview, picture));
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return selectReview;
    }
}

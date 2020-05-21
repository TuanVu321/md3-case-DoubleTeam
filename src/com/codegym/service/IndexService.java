package com.codegym.service;


import com.codegym.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndexService extends ConnectionJDBC implements IndexITF {

    @Override
    public List<Review> selectTableUsers() {

        List<Review> selectReview = new ArrayList<>();
        String query = "{call select_Review()}";
        Connection connection = getConnection();
        System.out.println(connection);

        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int idReview = rs.getInt("id_review");
                String nameUser = rs.getString("name_review");
                int poinReview = rs.getInt("pointevaluate");
                String destinations = rs.getString("destinations");
                String title = rs.getString("titleposts");
                String img = rs.getString("img");
                selectReview.add(new Review(idReview, nameUser, destinations, title, poinReview, img));
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return selectReview;
    }
}

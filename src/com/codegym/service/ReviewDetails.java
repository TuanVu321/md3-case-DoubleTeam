package com.codegym.service;


import com.codegym.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDetails extends ConnectionJDBC implements IReviewDetails {
    @Override
    public Review getReviewById(int id) {
        Review review = null;
        Connection connection = null;

        try {
            connection = getConnection();
            System.out.println("thanh cong ket noi voi trang");
            String sql = "select*from postsreview where id_review=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name_review");
                String title = rs.getString("titleposts");
                String content = rs.getString("content");
                Date datepost = rs.getDate("dateposts");
                int star = rs.getInt("pointevaluate");
                String picture = rs.getString("picture");
                review = new Review(id, name, title, content, datepost, star, picture);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return review;
    }

    @Override
    public List<Review> selectByName(String name) {
        Connection connection=null;
        List<Review> listReview = new ArrayList<>();
        try{
            connection = getConnection();
            String sql = "select * from postsreview where name_review like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            String nameSearch = "%"+name+"%";
            preparedStatement.setString(1,nameSearch);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String fullname = rs.getString("name_review");
                int id=rs.getInt("id_review");
                String title = rs.getString("titleposts");
                String content = rs.getString("content");
                Date datepost = rs.getDate("dateposts");
                int star = rs.getInt("pointevaluate");
                String picture = rs.getString("picture");
                listReview.add(new Review(id, fullname, title, content, datepost, star, picture));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return listReview;
    }

    @Override
    public List<Review> selectByDay() {
        Connection connection=null;
        List<Review> listReview = new ArrayList<>();
        try{
            connection = getConnection();
//            MySQLConnect connObj = new MySQLConnect("jdbc:mysql://localhost:3306/datareview");
//            connObj.setDBDriver();
//            connObj.setCredentials("root", "123456");
//            connection = connObj.openConnection("jdbc:mysql://localhost:3306/datareview");

            String sql="select * from postsreview order by dateposts desc";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String fullname = rs.getString("name_review");
                int id=rs.getInt("id_review");
                String title = rs.getString("titleposts");
                String content = rs.getString("content");
                Date datepost = rs.getDate("dateposts");
                int star = rs.getInt("pointevaluate");
                String picture = rs.getString("picture");
                listReview.add(new Review(id, fullname, title, content, datepost, star, picture));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return listReview;
    }
}

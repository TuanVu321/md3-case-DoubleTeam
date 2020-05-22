package com.codegym.service;

import com.codegym.model.Review;

import java.util.List;

public interface IReviewDetails {
    Review getReviewById(int id);
    List<Review> selectByName(String name);
    List<Review> selectByDay();
    List<Review> getListByPage(List<Review> List, int first, int last);

}

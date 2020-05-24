package com.codegym.service;

import com.codegym.model.Review;

import java.util.Date;
import java.util.List;

public interface ReviewServlet {
    List<Review> createReviewList();
    List<Review> createReviewListPagnigation(int start, int total);
    Review createNewReview(int destination, int id_account, String name_review, String titleposts, String content, String picture, int pointevalue, java.util.Date datepost);
}

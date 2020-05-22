package com.codegym.service;

import com.codegym.model.Review;

import java.util.ArrayList;
import java.util.List;

public interface IndexITF {
    public List<Review> selectTableUsers();
    public ArrayList<Review> getTop6Review(List<Review> list);
}

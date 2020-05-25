package com.codegym.model;

import java.util.Date;

public class    Review {
    int id_review;
    int id_destination;
    int id_account;
    String destinations;
    String name;
    String title;
    String content;
    Date datePost;
    int star;
    float scoreStar;
    String picture;

    public Review(int id_account) {
        this.id_account = id_account;
    }

    public Review(int id_review, String name, String destinations,  String title, int star, String picture) {
        this.id_review = id_review;
        this.name = name;
        this.destinations = destinations;
        this.title = title;
        this.star = star;
        this.picture = picture;
    }

    public Review(int id_destination, int id_account, String name, String title, String picture, String content, int point) {
        this.id_destination = id_destination;
        this.id_account = id_account;
        this.name = name;
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.star = point;
    }

    public Review(int id_destination, int id_account, String name, String title, String content, Date datePost, int star, String picture) {
        this.id_destination = id_destination;
        this.id_account = id_account;
        this.name = name;
        this.title = title;
        this.content = content;
        this.datePost = datePost;
        this.star = star;
        this.picture = picture;
    }

    public Review(int id_review, String name, String title, String content, Date datePost, int star, String picture) {
        this.id_review = id_review;
        this.name = name;
        this.title = title;
        this.content = content;
        this.datePost = datePost;
        this.star = star;
        this.picture = picture;
    }

    public Review(int id_review, String name_review, String titleposts, float pointevaluate) {
        this.id_review = id_review;
        this.name = name_review;
        this.title = titleposts;
        this.scoreStar = pointevaluate;
    }

    public int getId_destination() {
        return id_destination;
    }

    public void setId_destination(int id_destination) {
        this.id_destination = id_destination;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public int getId_review() {
        return id_review;
    }

    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public float getScoreStar() {
        return this.scoreStar;
    }

    public void setScoreStar(float score) {
        this.scoreStar = score;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDestinations() {
        return destinations;
    }public void setDestinations(String destinations) {
        this.destinations = destinations;
    }
}

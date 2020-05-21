package com.codegym.model;

import java.util.Date;

public class Review {
    int id_review;
    String name;
    String title;
    String content;
    Date datePost;
    int star;
    String picture;

    public Review() {
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

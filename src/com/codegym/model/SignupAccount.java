package com.codegym.model;

public class SignupAccount {
    private int id_role;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String address;
    private String phonenumber;
    private int actived;

    public SignupAccount(){}

    public SignupAccount(int id_role, String username, String password, String fullname, String phone, String email, String address, int actived) {
        this.id_role = id_role;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phonenumber = phone;
        this.actived = actived;
    }

    public SignupAccount(String username, String password, String fullname, String email, String address, String phonenumber) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public int isActived() {
        return actived;
    }

    public void setActived(int actived) {
        this.actived = actived;
    }

    public SignupAccount(int id_role, String username, String password, String fullname, String phone, String email, String address) {
        this.id_role = id_role;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phonenumber = phone;
    }

    public int getId_role(){
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}

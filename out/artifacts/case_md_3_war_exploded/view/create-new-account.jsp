<%--
  Created by IntelliJ IDEA.
  User: harrynguyen
  Date: 17/05/2020
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Tạo Tài Khoản Mới</title>
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/bootstrap-grid.css">
    <link rel="stylesheet" href="../css/bootstrap-reboot.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../css/confirm-email.css">
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/dashboard.css">
</head>
<body>
<%
    session = request.getSession();
    String fullname = (String)session.getAttribute("fullname");
    String typeAccount = (String)session.getAttribute("typeAccountLogIn");
%>
<nav class="navbar navbar-expand-md navbar-light bg-primary sticky-top justify-content-left">
    <div class="container-fluid">
        <a class="navbar-branch" id="logo" href="#">
            <img src="../img/logoDBT2.png" height="40"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarResponsive">
            <samp class="navbar-toggler-icon"></samp>
        </button>
        <div class="collapse navbar-collapse " id="navbarResponsive">
            <div class="search-box">
                <input class="form-control" placeholder="Search" type="text">
                <button class="btn btn-link search-btn"><i class="fa fa-search"></i>
                </button>
            </div>
            <ul class="navbar-nav mr-auto ">
                <li class="nav-item active">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="/viewservlet">Trang Chủ
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="/search" methods="get">Review</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="https://tago.vn/">Combo Siêu Rẻ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="https://www.vntrip.vn/ve-may-bay">Vé Máy Bay</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="https://www.booking.com/">Khách Sạn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="https://dichungtaxi.com/">Di Chuyển</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <%
                        if (fullname == null) {
                    %>
                    <a class="nav-link" style="color: white; font-size: 19px" href="/login?action=signin">Đăng Nhập</a>
                    <%
                    } else {
                    %>
                    <p id="fullname" style="color: white; font-size: 19px"><span id="name"><%=fullname%></span><br/>
                        <a id="role" href="#" style="color: red; font-size: 17px"><%=typeAccount%></a>
                    </p>
                    <a id="dangxuat" class="nav-link" style="color: white; font-size: 19px" href="/logout">Đăng Xuất</a>
                    <%
                        }
                    %>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div id="create-new" class="formConfirm dashboard">
        <h1>Tạo Tài Khoản Mới</h1>
        <a href="/admin_dashboard?action=showAccountsList&account=<%=fullname%>&role=${role}">Quay Về Trang Dashboard Admin</a>
        <form method="post">
            <label>Fullname:</label>
            <input type="text" name="fullname"/><br/>
            <label>Username:</label>
            <input type="text" name="username"/><br/>
            <label>Password:</label>
            <input type="text" name="password"/><br/>
            <label>Kiểu Tài Khoản:</label>
            <input type="text" name="role"/><br/>
            <label>Phone:</label>
            <input type="text" name="phone"/><br/>
            <label>Email:</label>
            <input type="text" name="email"/><br/>
            <label>Address:</label>
            <input type="text" name="address"/><br/>
            <input type="submit" value="Tạo Account Mới"/>
        </form>
    </div>
</div>
<div class="container-fluid" style="background: black; height: 500px; margin-top: 20px">
    <div style=" width: 700px; height: 450px; margin: 25px auto; text-align: center">
        <img src="../img/logoDBT.png" style="margin: 70px auto 30px auto">
        <p style="font-size: 60px;color: white ">Luôn đồng hành cùng bạn</p>
    </div>
</div>
</body>
</html>

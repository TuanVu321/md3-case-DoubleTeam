<%--
  Created by IntelliJ IDEA.
  User: harrynguyen
  Date: 17/05/2020
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.codegym.model.Review" %>
<%@ page import="com.codegym.service.ReviewServletImpl" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Dashboard Admin</title>
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/bootstrap-grid.css">
    <link rel="stylesheet" href="../css/bootstrap-reboot.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../css/confirm-email.css">
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/dashboard.css">
    <link rel="stylesheet" href="../css/dashboard-admin.css">
    <link rel="stylesheet" href="../css/post-review.css">
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
                       href="#">Trang chủ
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="#">Review</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="#">Hot tour</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="#">Vé máy bay</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="#">Khách sạn</a>
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
<div id="contain-dashboard-review" class="formConfirm dashboard">
    <h1>Dashboard Admin</h1>
    <button><a href="/admin_dashboard?action=showAccountsList&account=<%=fullname%>&role=<%=typeAccount%>">Account
        Dashboard</a></button>
    <button><a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&page=1">PostReview
        Dashboard</a></button>
    <br/>
    <h2>Quản Lý Bài Viết Review</h2>
    <a href="/admin_dashboard?action=showNewReviewForm&account=<%=fullname%>&role=<%=session.getAttribute("typeAccountLogIn")%>">Tạo
        Bài Viết Mới</a>
    <table>
        <thead>
        <tr>
            <th id="stt">STT</th>
            <th id="tbv">Tên Bài Viết</th>
            <th id="td">Tiêu Đề Bài Viết</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reviewList}" var="review">
            <tr>
                <td></td>
                <td>${review.getName()}</td>
                <td>${review.getTitle()}</td>
                <td>
                    <button>
                        <a href="/admin_dashboard?action=editPostReview&id_review=${review.getId_review()}">Edit</a>
                    </button>
                </td>
                <td>
                    <button><a
                            href="/admin_dashboard?action=delete&usernameAcc=${review.getName()}">Delete</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&page=1">1</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&page=2">2</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&page=3">3</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&page=4">4</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&page=5">5</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&page=6">6</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&page=7">7</a>
</div>
<div class="container-fluid" style="background: black; height: 500px; margin-top: 20px">
    <div style=" width: 700px; height: 450px; margin: 25px auto; text-align: center">
        <img src="../img/logoDBT.png" style="margin: 70px auto 30px auto">
        <p style="font-size: 60px;color: white ">Luôn đồng hành cùng bạn</p>
    </div>
</div>
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/toggle-dashboard.js"></script>
</body>
</html>




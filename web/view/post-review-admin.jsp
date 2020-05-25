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
    int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    int count = 1;
%>
<nav id="navigation" class="navbar navbar-expand-md navbar-light bg-primary sticky-top justify-content-left">
    <div class="container-fluid">
        <%--<a class="navbar-branch" id="logo" href="#">--%>
        <a class="navbar-branch" id="logo" href="/viewservlet">
            <img src="img/logoDBT2.png" height="40"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarResponsive">
            <samp class="navbar-toggler-icon"></samp>
        </button>
        <div class="collapse navbar-collapse  " id="navbarResponsive">
            <div style="width: 350px; height: 40px; margin-right: 20px">
                <form method="post" action="/search">
                    <div class="search-box input-group form-group">
                        <div class="input-group-prepend " style="height: 40px">
                            <span class="input-group-text search-btn">
                                <button type="submit" style="border: 0; background: 0px"><img src="/img/ic_search.png"
                                                                                              width="20"
                                                                                              height="20"></button>
                                <%--<button type="submit" style="border: 0; background: 0px"><img src="/img/ic_search.png" width="20" height="20"></button>--%>
                            </span>
                        </div>
                        <input name="inputName" class="form-control" placeholder="Tìm kiếm: Địa điểm, Lịch trình..." type="text">
                    </div>
                </form>
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
                <li class="nav-item" style="margin-top: 9px; margin-right: 10px">
                    <a href="#" style="color: white; font-size: 19px">
                    </a>
                </li>
                <li class="nav-item">
                    <%
                        if (fullname == null) {
                    %>
                    <a class="nav-link" style="color: white; font-size: 19px" href="/login?action=signin">Đăng Nhập</a>
                    <%
                    } else {
                    %>
                </li>
                <li>
                    <p id="fullname" style="color: white; font-size: 19px"><span id="name"><%=fullname%></span><br/>
                        <%
                            if (typeAccount.equals("admin")) {
                        %>
                        <a id="role" href="/admin_dashboard?action=showAccountsList&account=<%=fullname%>&role=<%=typeAccount%>" style="color: red; font-size: 17px"><%=typeAccount%></a>
                        <%
                        } else {
                        %>
                        <a id="role" href="/admin_dashboard?action=404Error&account=<%=fullname%>&role=<%=typeAccount%>" style="color: red; font-size: 17px"><%=typeAccount%></a>
                        <%
                            }
                        %>
                    </p>
                </li>
                <li>
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
    <button><a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&pageNo=1">PostReview
        Dashboard</a></button>
    <br/>
    <h2>Quản Lý Bài Viết Review</h2>
    <a href="/admin_dashboard?action=showNewReviewForm&account=<%=fullname%>&role=<%=session.getAttribute("typeAccountLogIn")%>">Tạo
        Bài Viết Mới</a>
    <form method="post" action="/admin_dashboard?action=searchReview">
        <label>Nhập Tên Địa Điểm Để Tìm Kiếm Bài Viết Review:</label><input type="text" name="searchName"/><br/>
        <input type="submit" value="Tìm Kiếm"/>
    </form>
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
                <td><%=count++%></td>
                <td>${review.getName()}</td>
                <td id="titleReview">${review.getTitle()}</td>
                <td>
                    <button>
                        <a href="/admin_dashboard?action=editPostReview&id_review=${review.getId_review()}">Edit</a>
                    </button>
                </td>
                <td>
                    <button><a
                            href="/admin_dashboard?action=deleteReview&id_review=${review.getId_review()}&pageNo=<%=pageNo%>">Delete</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&pageNo=1">1</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&pageNo=2">2</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&pageNo=3">3</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&pageNo=4">4</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&pageNo=5">5</a>
    <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&pageNo=6">6</a>
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




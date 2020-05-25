<%@ page import="com.codegym.model.SignupAccount" %>
<%@ page import="java.util.List" %><%--
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
</head>
<body>
<%
    session = request.getSession();
    String fullname = (String)session.getAttribute("fullname");
    String typeAccount = (String)session.getAttribute("typeAccountLogIn");
%>
<nav id="navigation" class="navbar navbar-expand-md navbar-light bg-primary sticky-top justify-content-left">
    <div class="container-fluid">
        <a class="navbar-branch" id="logo" href="#">
            <img src="img/logoDBT2.png" height="40"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarResponsive">
            <samp class="navbar-toggler-icon"></samp>
        </button>
        <div class="collapse navbar-collapse  " id="navbarResponsive">
            <div style="width: 300px; height: 40px; margin-right: 20px">
                <form method="post" action="/search">
                    <div class="search-box input-group form-group">
                        <div class="input-group-prepend " style="height: 40px">
                            <span class="input-group-text search-btn">
                                <button type="submit" style="border: 0; background: 0px"><img src="/img/ic_search.png"
                                                                                              width="20"
                                                                                              height="20"></button>
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
                    <a id="dangxuat" class="nav-link" style="color: white; font-size: 19px" href="/logout">Đăng Xuất</a>
                    <%
                        }
                    %>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div style="width: auto; height: auto; text-align: center; background: white; margin-top: 30px">
        <h1 style="padding-top: 30px">Dashboard Admin</h1>
        <button><a style="color: black" href="/admin_dashboard?action=showAccountsList&account=<%=fullname%>&role=<%=typeAccount%>">Account Dashboard</a></button>
        <button><a style="color: black" href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&pageNo=1">PostReview Dashboard</a></button>
        <div style="padding: 30px 30px">
            <h2>Quản Lý Tài Khoản Thành Viên</h2>
            <form method="get">
                <label>Nhập Họ Tên để tìm kiếm:</label>
                <input type="text" id="search" name="usernameSearch"/>
                <input type="submit" value="search"/>
            </form>
            <table class="table table-hover">
                <thead class="thead-light">
                <tr style="text-align: center">
                    <th scope="col" id="stt">STT</th>
                    <th scope="col" id="ht">Họ Tên</th>
                    <th scope="col" id="us">Username</th>
                    <th scope="col" id="pass">Password</th>
                    <th scope="col" id="phone">Số Điện Thoại</th>
                    <th scope="col" id="email">Email</th>
                    <th scope="col" id="address">Địa Chỉ</th>
                    <th scope="col" id="account">Kiểu Tài Khoản</th>
                    <th scope="col" id="update">Trạng Thái</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listAccount}" var="account">
                    <tr>
                        <td></td>
                        <td style="text-align: center">${account.getFullname()}</td>
                        <td>${account.getUsername()}</td>
                        <td style="text-align: center">${account.getPassword()}</td>
                        <td style="text-align: center">${account.getPhonenumber()}</td>
                        <td>${account.getEmail()}</td>
                        <td>${account.getAddress()}</td>
                        <td style="text-align: center">${account.getId_role()}</td>
                        <c:choose>
                            <c:when test="${account.isActived() == 1}">
                                <td>
                                    "Tài Khoản đang kích hoạt"
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    "Tài Khoản đã bị khóa"
                                </td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <button><a href="/admin_dashboard?action=actived&usernameAcc=${account.getUsername()}">Actived</a></button>
                        </td>
                        <td>
                            <button><a style="color: red" href="/admin_dashboard?action=blocked&usernameAcc=${account.getUsername()}">Blocked</a></button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
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

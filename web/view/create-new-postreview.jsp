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
    <title>Tạo PostReview Mới</title>
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
    String fullname = (String) session.getAttribute("fullname");
    String typeAccount = (String) session.getAttribute("typeAccountLogIn");
%>
<nav id="navigation" class="navbar navbar-expand-md navbar-light bg-primary sticky-top justify-content-left">
    <div class="container-fluid">
        <a class="navbar-branch" id="logo" href="/viewservlet">
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
                        <input name="inputName" class="form-control" placeholder="Tìm kiếm: Địa điểm, Lịch trình..."
                               type="text">
                    </div>
                </form>
            </div>
            <ul class="navbar-nav mr-auto ">
                <li class="nav-item active">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="/viewservlet">Trang chủ
<<<<<<< HEAD
                       <%--href="/viewservlet">Trang Chủ--%>
=======
                        <%--href="/viewservlet">Trang Chủ--%>
>>>>>>> 066b7e780c6ebec20b4eef15c17920f8b229a236
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="/search">Review</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="https://tago.vn/">Hot tour</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="https://www.vntrip.vn/ve-may-bay">Vé máy bay</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="https://www.booking.com/">Khách sạn</a>
<<<<<<< HEAD
                       <%--href="/search" methods="get">Review</a>--%>
=======
                    <%--href="/search" methods="get">Review</a>--%>
>>>>>>> 066b7e780c6ebec20b4eef15c17920f8b229a236
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
                </li>
                <li>
                    <p id="fullname" style="color: white; font-size: 19px"><span id="name"><%=fullname%></span><br/>
                        <%
                            if (typeAccount.equals("admin")) {
                        %>
                        <a id="role"
                           href="/admin_dashboard?action=showAccountsList&account=<%=fullname%>&role=<%=typeAccount%>"
                           style="color: red; font-size: 17px"><%=typeAccount%>
                        </a>
                        <%
                        } else {
                        %>
                        <a id="role" href="/admin_dashboard?action=404Error&account=<%=fullname%>&role=<%=typeAccount%>"
                           style="color: red; font-size: 17px"><%=typeAccount%>
                        </a>
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
<div class="container-fluid">
    <div style="width: auto; height: auto; text-align: center; background: white; margin-top: 30px">
        <h1 style="padding-top: 30px">Tạo Bài Post Review mới</h1>
        <button>
            <a href="/admin_dashboard?action=showReviewList&account=<%=fullname%>&role=<%=typeAccount%>&pageNo=1">PostReview
                Dashboard</a>
        </button>
        <div style=" width: 485px; height: 340px; padding: 30px 30px; margin: 10px auto">
            <form method="post">
                <table>
                    <tr style="padding-top: 10px">
                        <td style="text-align: right; padding-right: 10px"><label>id_Destination:</label></td>
                        <td><input type="text" name="destination"/></td>
                    </tr>
                    <tr style="padding-top: 10px">
                        <td style="text-align: right; padding-right: 10px"><label>Name Review:</label></td>
                        <td><input type="text" name="nameReview"/></td>
                    </tr>
                    <tr style="padding-top: 10px">
                        <td style="text-align: right; padding-right: 10px"><label>Title Posts:</label></td>
                        <td><textarea name="title" rows="5" cols="40"></textarea></td>
                    </tr>
                    <tr style="padding-top: 10px">
                        <td style="text-align: right; padding-right: 10px"><label>Picture:</label></td>
                        <td><input type="text" name="picture"/></td>
                    </tr>
                    <tr style="padding-top: 10px">
                        <td style="text-align: right; padding-right: 10px"><label>Content:</label></td>
                        <td><textarea name="content" rows="5" cols="40"></textarea></td>
                    </tr>
                    <tr style="padding-top: 10px">
                        <td style="text-align: right; padding-right: 10px"><label>Point values:</label></td>
                        <td><input type="text" name="point"/></td>
                    </tr>
                    <tr style="text-align: center; padding-top: 20px">
                        <td colspan="2"><input type="submit" value="Tạo PostReview"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div style="height: 3px; padding-bottom: 30px"></div>
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

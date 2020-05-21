<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/05/2020
  Time: 11:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/bootstrap-grid.css">
    <link rel="stylesheet" href="../css/bootstrap-reboot.css">
    <link rel="stylesheet" href="../css/search.css">
    <title>Tìm kiếm</title>
</head>
<body>

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
                    <a class="nav-link" style="color: white; font-size: 19px"
                       href="/login?action=signin">Đăng Nhập</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div id="demo" class="carousel slide" data-ride="carousel">
    <ul class="carousel-indicators">
        <li data-target="#demo" data-slide-to="0" class="active"></li>
        <li data-target="#demo" data-slide-to="1"></li>
    </ul>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="../img/banner2.png">
        </div>
        <div class="carousel-item">
            <img src="../img/banner3.png">
        </div>
    </div>
</div>
<%--<div class="container">--%>
<%--    <h3 style="margin-top: 20px; margin-bottom: 20px">HOT Tour </h3>--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-4 hot-tour">--%>
<%--            <div style="background: white; float: left">--%>
<%--                <div style="width:110px; height: 110px; margin-left: 0; margin-right: 0">--%>
<%--                    <img src="../img/background.jpg" height="70px" width="70px">--%>
<%--                </div>--%>

<%--                <div style="margin: 0 auto">--%>
<%--                    <p>Kham pha du lich da nang</p>--%>
<%--                    <p><img src="../img/money.png" height="15" width="15"> 10000000 <span>VND</span></p>--%>
<%--                    <p><img src="../img/time.png" height="15" width="15"> 3 <span>Ngay</span></p>--%>

<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="hot-tour">1</div>--%>

<%--        </div>--%>
<%--        <div class="col-md-4">--%>

<%--        </div>--%>
<%--        <div class="col-md-4">--%>

<%--        </div>--%>
<%--    </div>--%>
    <h3 style="margin-top: 20px; margin-bottom: 20px">Địa điểm nổi bật</h3>
    <% int i=0; %>;

    <div>
       <table>
           <tr>
               <td>Stt</td>
               <td>Tên bài viết</td>
               <td>Ngày đăng</td>
           </tr>
           <C:forEach var="review" items="${listReview}">
               <tr>
                   <td>++i</td>
                   <td><a href="">${review.getName()}</a></td>
                   <td><a href="">${review.getDatePost()}</a></td>
               </tr>
           </C:forEach>
       </table>
    </div>

</div>
<div class="container">

    <div class="container-fluid" style="background: black; height: 500px; margin-top: 20px">
        <div style=" width: 700px; height: 450px; margin: 25px auto; text-align: center">
            <img src="../img/logoDBT.png" style="margin: 70px auto 30px auto">
            <p style="font-size: 60px;color: white ">Luôn đồng hành cùng bạn</p>
        </div>
    </div>
    <div>

    </div>

    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/utilities.js"></script>
    <script type="text/javascript" src="js/validate-form.js"></script>
    <script type="text/javascript" src="js/validate-register.js"></script>
</div>
</body>
</html>

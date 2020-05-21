<%--
  Created by IntelliJ IDEA.
  User: harrynguyen
  Date: 15/05/2020
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/bootstrap-grid.css">
    <link rel="stylesheet" href="../css/bootstrap-reboot.css">
    <link rel="stylesheet" href="../css/sign-in.css">
    <title>Đăng Nhập</title>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-center h-100">
        <div class="card-signin">
            <div class="card-header">
                <h3>Đăng Nhập</h3>
            </div>
            <div class="card-body">
                <form method="post">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text ">
                                <img src="../img/ic_user.png" width="25" height="25">
                            </span>
                        </div>
                        <input id="usrInput" type="text" name="username" class="form-control" placeholder="username">
                        <br/>
                        <div id="feedbackUsername" class="tip"></div>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text ">
                                <img src="../img/ic_key.png" width="25" height="25">
                            </span>
                        </div>
                        <input id="pwd" type="password" name="password" class="form-control" placeholder="password">
                        <br/>
                        <div id="feedbackPassword" class="tip"></div>
                    </div>
                    <div class="row align-items-center remember">
                        <input type="checkbox">Remember
                    </div>
                    <div class="form-group">
                        <input id="submitBtn" type="submit" name="submit" value="Đăng Nhập" class="btn float-right signin_btn">
                    </div>
                </form>
            </div>
            <div id="footer" class="card-footer">
                <div class="d-flex justify-content-center links">
                    Bạn Không Có Tài Khoản ?<a id="signup" style="color: #FFC312" href="/login?action=signup">Đăng Ký</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../js/bootstrap.bundle.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="../js/utilities.js"></script>
<script type="text/javascript" src="../js/validate-form.js"></script>
</body>
</html>

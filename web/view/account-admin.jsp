<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 5/21/20
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Admin</title>
</head>
<body>
<%
    session = request.getSession();
    String fullname = (String)session.getAttribute("fullname");
    String typeAccount = (String)session.getAttribute("typeAccountLogIn");
    int count = 1;
%>
<div class="container">
    <div id="contain-dashboard" class="formConfirm dashboard">
            <h2>Quản Lý Tài Khoản Thành Viên</h2>
            <a href="/admin_dashboard?action=create&account=<%=fullname%>&role=<%=session.getAttribute("typeAccountLogIn")%>">Tạo
                Tài Khoản Mới</a>
            <table>
                <tr>
                    <th id="stt">STT</th>
                    <th id="ht">Họ Tên</th>
                    <th id="us">Username</th>
                    <th id="pass">Password</th>
                    <th id="phone">Số Điện Thoại</th>
                    <th id="email">Email</th>
                    <th id="address">Địa Chỉ</th>
                    <th id="account">Kiểu Tài Khoản</th>
                    <th id="update">Cập Nhật Thông Tin</th>
                </tr>
                <c:forEach items="${accountList}" var="account">
                    <tr>
                        <td><%=count++%></td>
                        <td>${account.getFullname()}</td>
                        <td>${account.getUsername()}</td>
                        <td>${account.getPassword()}</td>
                        <td>${account.getPhonenumber()}</td>
                        <td>${account.getEmail()}</td>
                        <td>${account.getAddress()}</td>
                        <td>${account.getId_role()}</td>
                        <td>
                            <button><a href="/admin_dashboard?action=actived&usernameAcc=${account.getUsername()}">Actived</a>
                            </button>
                        </td>
                        <td>
                            <button><a
                                    href="/admin_dashboard?action=blocked&usernameAcc=${account.getUsername()}">Blocked</a>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>

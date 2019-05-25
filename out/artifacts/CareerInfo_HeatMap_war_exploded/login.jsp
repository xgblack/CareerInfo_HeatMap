<%--
  User: 小光
  Date: 2019/5/25 15:50
  ***************************************************************************
  Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
  ***************************************************************************
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link rel="shortcut icon" href="img/favicon.ico"/>
    <link rel="bookmark" href="img/favicon.ico"/>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="static/bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="css/registlogin.css">
    <script src="js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function () {

        });

        function refreshCode() {
            var vcode = document.getElementById("vcode");
            vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();
        }
    </script>
</head>
<body>
<div class="rg_layout">
    <div class="rg_left">
        <p>用户登录</p>
        <p>UESR LOGIN</p>
    </div>
    <div class="tg_center">
        <div class="rg_form">
                <div class="container" style="width: 400px;">
                    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
                        <div class="form-group">
                            <label for="user">用户名：</label>
                            <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名" required/>
                        </div>

                        <div class="form-group">
                            <label for="pass">密码：</label>
                            <input type="password" name="password" class="form-control" id="pass" placeholder="请输入密码" required/>
                        </div>

                        <div class="form-inline">
                            <label for="verifycode">验证码：</label>
                            <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码" style="width: 120px;" required/>
                            <a href="javascript:refreshCode()"><img
                                    src="${pageContext.request.contextPath}/checkCodeServlet"
                                    title="看不清点击刷新" id="vcode"/></a>
                        </div>
                        <hr/>
                        <div class="form-group" style="text-align: center;">
                            <input class="btn btn btn-primary" type="submit" value="登录">
                        </div>
                    </form>

                    <!-- 出错显示的信息框 -->
                    <c:if test="${not empty login_msg}">
                        <div class="alert alert-warning alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert">
                                <span>&times;</span></button>
                            <strong>${login_msg}</strong>
                        </div>
                    </c:if>

                </div>
        </div>
    </div>
    <div class="rg_right">
        <p>没有账号?<a href="regist.jsp">点击注册</a></p>
    </div>
</div>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</body>
</html>
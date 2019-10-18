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
    <title>用户注册</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
    <link rel="bookmark" href="${pageContext.request.contextPath}/favicon.ico"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registlogin.css">
    <script>
        window.onload = function () {

            document.getElementById("form").onsubmit = function () {
                return checkUsername() && checkPassword() && checkDoublePassword() && checkEmail() && checkTel();
            };
            //给输入框绑定离焦时间
            document.getElementById("username").onblur = checkUsername;
            document.getElementById("password").onblur = checkPassword;
            document.getElementById("double_password").onblur = checkDoublePassword;
            document.getElementById("email").onblur = checkEmail;
            document.getElementById("tel").onblur = checkTel;
        };

        //校验用户名
        function checkUsername() {
            //获取用户名的值
            var username = document.getElementById("username").value;
            //定义正则表达式,数字字母下划线，6-12位
            var reg_username = /^\w{6,12}$/;
            //判断是否符合规则
            var flag = reg_username.test(username);
            //提示信息
            var s_username = document.getElementById("s_username");
            if (flag) {
                //合格
                s_username.innerHTML = "<img width='25' height='25' src='${pageContext.request.contextPath}/images/right.png'/>"
            }else {
                //格式错误
                s_username.innerHTML = "<img width='25' height='25' src='${pageContext.request.contextPath}/images/error.png'/>6-12位，字母、数字、下划线"
            }
            return flag;
        }

        //校验密码
        function checkPassword() {
            //获取用户名的值
            var password = document.getElementById("password").value;
            //定义正则表达式,数字字母下划线，6-12位
            var reg_password = /^\w{6,12}$/;
            //判断是否符合规则
            var flag = reg_password.test(password);
            //提示信息
            var s_password = document.getElementById("s_password");
            if (flag) {
                //合格
                s_password.innerHTML = "<img width='25' height='25' src='${pageContext.request.contextPath}/images/right.png'/>"
            }else {
                //格式错误
                s_password.innerHTML = "<img width='25' height='25' src='${pageContext.request.contextPath}/images/error.png'/>6-12位，字母、数字、下划线"
            }
            return flag;
        }
        function checkDoublePassword() {
            var password = document.getElementById("password").value;
            var doublePassword = document.getElementById("double_password").value;
            //提示信息
            var s_doublepassword = document.getElementById("s_doublepassword");
            //判断两次密码是否一致
            var flag = password == doublePassword;
            if (flag) {
                //合格
                s_doublepassword.innerHTML = "<img width='25' height='25' src='${pageContext.request.contextPath}/images/right.png'/>";
            } else {
                //两次密码不一致
                s_doublepassword.innerHTML = "<img width='25' height='25' src='${pageContext.request.contextPath}/images/error.png'/>两次密码不一致";
            }
            return flag;
        }
        function checkEmail() {
            var reg_email = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
            var email = document.getElementById("email").value;
            var s_email = document.getElementById("s_email");
            var flag = reg_email.test(email);
            if (flag) {
                //邮箱合格
                s_email.innerHTML = "<img width='25' height='25' src='${pageContext.request.contextPath}/images/right.png'/>";
            } else {
                //两次密码不一致
                s_email.innerHTML = "<img width='25' height='25' src='${pageContext.request.contextPath}/images/error.png'/>邮箱格式不正确";
            }
            return flag;
        }
        function checkTel() {
            var reg_tel = /(13\d|14[579]|15[^4\D]|17[^49\D]|18\d)\d{8}/;
            var tel = document.getElementById("tel").value;
            var s_tel = document.getElementById("s_tel");
            var flag = reg_tel.test(tel);
            if (flag) {
                //邮箱合格
                s_tel.innerHTML = "<img width='25' height='25' src='${pageContext.request.contextPath}/images/right.png'/>";
            } else {
                //两次密码不一致
                s_tel.innerHTML = "<img width='25' height='25' src='/images/error.png'/>手机号格式不正确";
            }
            return flag;
        }
        function refreshCode() {
            var vcode = document.getElementById("vcode");
            vcode.src = "${pageContext.request.contextPath}/checkCode?time=" + new Date().getTime();
        }
    </script>
</head>
<body>
<div class="rg_layout">
    <div class="rg_left">
        <p>新用户注册</p>
        <p>UESR REGISTER</p>
    </div>
    <div class="rg_center">
        <div class="rg_form">
            <form id="form" action="${pageContext.request.contextPath}/user/registerCheck" method="post">
                <table>
                    <tr>
                        <td class="td_left"><lable for="username">用户名</lable></td>
                        <td class="td_right"><input type="text" name="username" id="username" placeholder="请输入用户名" required>
                            <span id="s_username" class="error"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left"><lable for="password">密码</lable></td>
                        <td class="td_right"><input type="password" name="password" id="password" placeholder="请输入密码" required>
                            <span id="s_password" class="error"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left"><lable for="password">重复密码</lable></td>
                        <td class="td_right"><input type="password" name="doublePassword" id="double_password" placeholder="请再输入一次密码" required>
                            <span id="s_doublepassword" class="error"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left"><lable for="email">Email</lable></td>
                        <td class="td_right">
                            <input type="email" name="email" id="email" placeholder="请输入邮箱" required>
                            <span id="s_email" class="error"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left"><lable for="tel">手机号</lable></td>
                        <td class="td_right">
                            <input type="text" name="phone" id="tel" placeholder="请输入手机号码" required>
                            <span id="s_tel" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><lable>性别</lable></td>
                        <td class="td_right">
                            <input type="radio" name="gender" value="男" checked>男
                            <input type="radio" name="gender" value="女">女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><lable for="birthday">出生日期</lable></td>
                        <td class="td_right"><input type="date" name="birthday" id="birthday" required></td>
                    </tr>
                    <tr>
                        <td class="td_left"><lable for="checkcode">验证码</lable></td>
                        <td class="td_right">
                            <input type="text" name="verifycode" id="checkcode" placeholder="请输入验证码" required>
                            <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/checkCode" title="看不清点击刷新" id="vcode"/></a>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" id="td_sub"><input id="btn_sub" type="submit" value="注册"/> </td>
                    </tr>

                </table>
            </form>
            <c:if test="${not empty regist_msg}">
                <span id="regist_msg" class="error">${regist_msg}</span>
            </c:if>
        </div>
    </div>
    <div class="rg_right">
        <p>已有账号?<a href="${pageContext.request.contextPath}/user/login">点击登录</a> </p>
    </div>
</div>
</body>
</html>

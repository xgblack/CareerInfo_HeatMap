<%--
  User: 小光
  Date: 2019/5/25 15:50
  ***************************************************************************
  Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
  ***************************************************************************
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="css/registlogin.css">
    <script>
        window.onload = function () {

            document.getElementById("form").onsubmit = function () {
                return checkUsername() && checkPassword() && checkDoublePassword();
            };
            //给用户名和密码框绑定离焦时间
            document.getElementById("username").onblur = checkUsername;
            document.getElementById("password").onblur = checkPassword;
            document.getElementById("double_password").onblur = checkDoublePassword;
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
                s_username.innerHTML = "<img width='25' height='25' src='img/right.png'/>"
            }else {
                //格式错误
                s_username.innerHTML = "<img width='25' height='25' src='img/error.png'/>6-12位，字母、数字、下划线"
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
                s_password.innerHTML = "<img width='25' height='25' src='img/right.png'/>"
            }else {
                //格式错误
                s_password.innerHTML = "<img width='25' height='25' src='img/error.png'/>6-12位，字母、数字、下划线"
            }
            return flag;
        }
        function checkDoublePassword() {
            var password = document.getElementById("password").value;
            var doubleDassword = document.getElementById("double_password").value;
            //提示信息
            var s_doublepassword = document.getElementById("s_doublepassword");
            //判断两次密码是否一致
            var flag = password == doubleDassword;
            if (flag) {
                //合格
                s_doublepassword.innerHTML = "<img width='25' height='25' src='img/right.png'/>";
            } else {
                //两次密码不一致
                s_doublepassword.innerHTML = "<img width='25' height='25' src='img/error.png'/>两次密码不一致"
            }
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
            <form id="form" action="" method="post">
                <table>
                    <tr>
                        <td class="td_left"><lable for="username">用户名</lable></td>
                        <td class="td_right"><input type="text" name="username" id="username" placeholder="请输入用户名">
                            <span id="s_username" class="error"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left"><lable for="password">密码</lable></td>
                        <td class="td_right"><input type="password" name="password" id="password" placeholder="请输入密码">
                            <span id="s_password" class="error"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left"><lable for="password">重复密码</lable></td>
                        <td class="td_right"><input type="password" name="doublePassword" id="double_password" placeholder="请再输入一次密码">
                            <span id="s_doublepassword" class="error"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left"><lable for="email">Email</lable></td>
                        <td class="td_right"><input type="email" name="email" id="email" placeholder="请输入邮箱"></td>
                    </tr>
                    <tr>
                        <td class="td_left"><lable for="tel">手机号</lable></td>
                        <td class="td_right"><input type="text" name="tel" id="tel" placeholder="请输入手机号码"></td>
                    </tr>
                    <tr>
                        <td class="td_left"><lable>性别</lable></td>
                        <td class="td_right">
                            <input type="radio" name="gender" value="male" checked>男
                            <input type="radio" name="gender" value="female">女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><lable for="birthday">出生日期</lable></td>
                        <td class="td_right"><input type="date" name="birthday" id="birthday"></td>
                    </tr>
                    <tr>
                        <td class="td_left"><lable for="checkcode">验证码</lable></td>
                        <td class="td_right">
                            <input type="text" name="checkcode" id="checkcode" placeholder="请输入验证码">
                            <img id="img_check" src="img/verif_code.jpg">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" id="td_sub"><input id="btn_sub" type="submit" value="注册"/> </td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
    <div class="rg_right">
        <p>已有账号?<a href="#">点击登录</a> </p>
    </div>
</div>
</body>
</html>

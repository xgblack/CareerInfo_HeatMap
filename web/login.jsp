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
    <title>用户登录</title>
    <link rel="stylesheet" href="css/registlogin.css">
</head>
<body>
<div class="rg_layout">
    <div class="rg_left">
        <p>用户登录</p>
        <p>UESR LOGIN</p>
    </div>
    <div class="tg_center">
        <div class="rg_form">
            <form action="" method="post">
                <table>
                    <tr>
                        <td class="td_left"><lable for="username">用户名</lable></td>
                        <td class="td_right"><input type="text" name="username" id="username" placeholder="请输入用户名"></td>
                    </tr>
                    <tr>
                        <td class="td_left"><lable for="password">密码</lable></td>
                        <td class="td_right"><input type="text" name="password" id="password" placeholder="请输入密码"></td>
                    </tr>

                    <tr>
                        <td class="td_left"><lable for="checkcode">验证码</lable></td>
                        <td class="td_right">
                            <input type="text" name="checkcode" id="checkcode" placeholder="请输入验证码">
                            <img id="img_check" src="img/verif_code.jpg">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input id="btn_sub" type="submit" value="登录"/> </td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
    <div class="rg_right">
        <p>没有账号?<a href="#">点击注册</a> </p>
    </div>
</div>
</body>
</html>

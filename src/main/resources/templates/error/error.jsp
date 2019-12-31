<%--
  User: 小光
  Date: 2019/8/2 17:01
  ***************************************************************************
  Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
  ***************************************************************************
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>5xx</h1>
<table border="1">
    <tr>
        <td>path</td>
        <td th:text="${path}"></td>
    </tr>
    <tr>
        <td>error</td>
        <td th:text="${error}"></td>
    </tr>
    <tr>
        <td>message</td>
        <td th:text="${message}"></td>
    </tr>
    <tr>
        <td>timestamp</td>
        <td th:text="${timestamp}"></td>
    </tr>
    <tr>
        <td>status</td>
        <td th:text="${status}"></td>
    </tr>
</table>
</body>
</html>

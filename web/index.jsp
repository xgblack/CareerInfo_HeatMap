
<%--
  User: 小光
  Date: 2019/5/23 23:01
  ***************************************************************************
  Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
  ***************************************************************************
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>全国招聘信息热力图展示</title>
  <link rel="shortcut icon" href="img/favicon.ico"/>
  <link rel="bookmark" href="img/favicon.ico"/>

  <!-- Bootstrap -->
  <link rel="stylesheet" href="static/bootstrap-3.3.7-dist/css/bootstrap.css">

  <link rel="stylesheet" href="css/mapstyle.css">

  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.4.0/dist/leaflet.css" />
  <script src="https://unpkg.com/leaflet@1.4.0/dist/leaflet.js"></script>

  <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
  <script src="js/jquery-3.4.1.min.js"></script>

  <script src="js/heatmap.js"></script>
  <script src="js/leaflet-heatmap.js"></script>


  <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
  <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
  <!--[if lt IE 9]>
  <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
  <![endif]-->
  <script>
    $(function () {
      $.get("${pageContext.request.contextPath}/findJobsByPage",{currentPage:2,rows:10},function (data,index) {

        var totalCount = data["totalCount"];
        var totalPage = data["totalPage"];
        var currentPage = data["currentPage"];
        var rows = 10;
        var list = data["list"];

        var htmlstr = "";
        $.each(list, function (index, value) {
          alert(value.cname);

          var cname = value.cname;
          var janme = value.jname;
          var wage = value.minwage + "-" + value.maxwage;

          htmlstr += '<tr>';
          htmlstr += '<td>' + (index + 1) + '</td>';
          htmlstr += '<td>' + cname + '</td>';
          htmlstr += '<td>' + janme + '</td>';
          htmlstr += '<td>' + wage + '</td>';
          htmlstr += '</tr>';
        });
        $("#table").append(htmlstr);
      });


      <%--$.get("${pageContext.request.contextPath}/findJobsServlet",function (data,index) {--%>
      <%--  var htmlstr = "";--%>
      <%--  $.each(data, function (index, value) {--%>
      <%--    var cname = value.cname;--%>
      <%--    var janme = value.jname;--%>
      <%--    var wage = value.minwage + "-" + value.maxwage;--%>

      <%--    htmlstr += '<tr>';--%>
      <%--    htmlstr += '<td>' + (index + 1) + '</td>';--%>
      <%--    htmlstr += '<td>' + cname + '</td>';--%>
      <%--    htmlstr += '<td>' + janme + '</td>';--%>
      <%--    htmlstr += '<td>' + wage + '</td>';--%>
      <%--    htmlstr += '</tr>';--%>
      <%--  });--%>
      <%--  $("#table").append(htmlstr);--%>
      <%--});--%>
    });
  </script>
</head>
<body>
<!--页面布局容器-->
<div class="container-fluid">
  <!--    导航-->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

          <a class="navbar-brand" href="#">全国招聘信息热力图展示</a>
          <p class="navbar-text"><strong><a href="" class="navbar-link">${user.username}</a></strong>，欢迎登录</p>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
            <li><a href="#">Link</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Separated link</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
          <form class="navbar-form navbar-right">
<%--            <button type="submit" class="btn btn-default">Submit</button>--%>
            <div class="form-group">
              <label for="search_cname">公司名称</label>
              <input type="text" class="form-control" id="search_cname" name="cname" value="" placeholder="阿里巴巴">
            </div>
            <div class="form-group">
              <label for="search_jname">职位名称</label>
              <input type="text" class="form-control" id="search_jname" name="jname" value="" placeholder="java开发工程师">
            </div>
            <div class="form-group">
              <label for="search_minwage">最低工资</label>
              <select class="form-control"  id="search_minwage" name="minwage">
                <option>0</option>
                <option>4000</option>
                <option>5000</option>
                <option>6000</option>
                <option>7000</option>
                <option>8000</option>
                <option>9000</option>
                <option>10000</option>
                <option>12000</option>
                <option>15000</option>
                <option>20000</option>
                <option>30000</option>
              </select>
            </div>
            <button type="submit" class="btn btn-default" id="search_btn">查找</button>
          </form>

        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
</div><!-- /.container-fluid -->
<!--    主体-->
<div class="container-fluid">
  <div class="row">
    <!--        地图-->
    <div class="col-md-9" id="col_map">
      <div id="mapid">
        <div class="mswitch" onclick="switchlayer()" onmouseover="swover()" onmouseout="swout()">
          <span style=""><b>卫星</b></span>
        </div>
      </div>
    </div><!--地图end-->
    <div class="col-md-3" id="col_tab">
      <div class="container-fluid" id="div_tab">


        <div class="table-responsive">
          <table class="table table-condensed table-bordered table-hover" id="table">
            <tr class="success">
              <th colspan="4" class="text-center">全国招聘信息</th>
            </tr>
            <tr class="success">
              <th class="text-center">序号</th>
              <th class="text-center">公司名称</th>
              <th class="text-center">职位名称</th>
              <th class="text-center">工资</th>
            </tr>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--              <td>1</td>--%>
<%--              <td>阿里巴巴</td>--%>
<%--              <td>Java开发工程师</td>--%>
<%--              <td>5000-6000</td>--%>
<%--            </tr>--%>
          </table>
        </div>


        <nav aria-label="Page navigation">
          <ul class="pagination">
            <li>
              <a href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li class="active"><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li>
              <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
          <form class="form-inline">
            <div class="form-group">
              <label for="inp_skipPage">1500条记录，共100页</label>
              <input type="text" class="form-control" id="inp_skipPage" name="skipPage" placeholder="请输入要跳转的页码">
            </div>
            <button type="submit" class="btn btn-default">跳转</button>
          </form>

        </nav>

      </div>
    </div>

  </div>
</div>

<!--    底部-->
<div class="container-fluid">
  <div class="row">
    <nav class="navbar navbar-inverse navbar-default navbar-fixed-bottom" id="nav_bottom">
      <div class="container-fluid">
        <p class="text-center" id="p_bottom">Copyright © 2019 <a href="https://blog.xgblack.cn" class="navbar-link">小光&臾离blog</a></p>

      </div><!-- /.container-fluid -->
    </nav>
  </div>
</div>

<script src="js/mapfunction.js"></script>

<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

</body>
</html>

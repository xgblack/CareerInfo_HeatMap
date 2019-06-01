<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: 小光
  Date: 2019/5/23 23:01
  ***************************************************************************
  Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
  ***************************************************************************
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery-3.4.1.min.js"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="static/bootstrap-3.3.7-dist/css/bootstrap.css">

    <link rel="stylesheet" href="css/mapstyle.css">


    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=zKfGbrgNomYkFrUEy8a09Tw1PLudhUnU">
        //v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
    </script>


    <script src="js/heatmap_baidu.js"></script>


    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->

    <script>
        //总记录数
        var totalCount ;
        //总页码
        var totalPage ;

        //当前页码
        var currentPage;

        //每页记录数，默认为15 （且一般不会更改）
        var rows = 15;

        //存放标记点
        var myMarker = [];
        var myHeatMapOverlay = [];

        //配置
        var cfg = {
            "radius": 20,
            "maxOpacity": 1,
            "minOpacity": 0.55,
            // "blur":0.95,
            "gradient":{
                0.03:'rgb(82,159,233)',
                0.04:'rgb(14, 246, 243)',
                0.059:'rgb(0, 255, 0)',
                0.06:'rgb(252, 255, 0)',
                0.1:'rgb(255, 0, 0)'
            }
        };

        //入口函数
        $(function () {

            //首次打开页面,加载列表
            var searchTotalPage = searchJob(1);
            refreshPages(searchTotalPage,1);
            //打开页面，加载全部热力图的点
            initAllPoints(cfg);




            /**
             * 搜索按钮绑定单击事件
             */
            $("#search_btn").click(function () {
                var searchTotalPage = searchJob(1);
                refreshPages(searchTotalPage,1);

            });


            /**
             * 跳转按钮绑定单击事件
             */
            $("#btn_skipPage").click(function () {
                var skipCurrentPage = Number($("#inp_skipPage").val());
                //安全判断
                if (skipCurrentPage < 1) {
                    $("#inp_skipPage").val(1);
                    skipCurrentPage = 1;
                }
                //查询
                var searchTotalPage = searchJob(skipCurrentPage);
                //安全判断
                if (skipCurrentPage > searchTotalPage) {
                    $("#inp_skipPage").val(searchTotalPage);
                }
                //刷新页码
                refreshPages(searchTotalPage,skipCurrentPage);
            });

        });

        /**
         * 初始化页面，列表/热力图
         */
        function initAllPoints(cfg) {
            $.get(
                "${pageContext.request.contextPath}/findAllJobsPoints",
                function (allData) {
                    var heatmapData = {
                        max : allData.length + 1,
                        data: JSON.parse(allData)
                    };
                    if(!isSupportCanvas()){
                        alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
                    }
                    var heatmapOverlay = new BMapLib.HeatmapOverlay(cfg);
                    map.addOverlay(heatmapOverlay);
                    heatmapOverlay.setDataSet(heatmapData);
                    myHeatMapOverlay.push(heatmapOverlay);
                }
            );
        }


        /**
         * 加载部分点的方法
         * 不可用
         * 数据量过大时，易造成浏览器卡死
         */
        function searchSomePoints(cfg) {
            $.get(
                "${pageContext.request.contextPath}/findJobsPoints",
                {
                    cname:$("#search_cname").val() ,
                    jname:$("#search_jname").val(),
                    province:$("#search_province").val(),
                    minwage:$("#search_minwage").val()
                },
                function (allData) {
                    var heatmapData = {
                        max : allData.length + 1,
                        data: JSON.parse(allData)
                    };
                    if(!isSupportCanvas()){
                        alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
                    }
                    var heatmapOverlay = new BMapLib.HeatmapOverlay(cfg);
                    map.addOverlay(heatmapOverlay);
                    heatmapOverlay.setDataSet(heatmapData);

                    myHeatMapOverlay.push(heatmapOverlay);
                }
            );
        }



        //搜索方法
        function searchJob(currentPage) {
            var searchTotalPage = 0;
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/findJobsByPage",
                data:{
                    currentPage: currentPage,
                    rows: 15,
                    cname:$("#search_cname").val() ,
                    jname:$("#search_jname").val(),
                    province:$("#search_province").val(),
                    minwage:$("#search_minwage").val()
                },
                async : false,
                success : function(data){
                    totalCount = data["totalCount"];
                    totalPage = data["totalPage"];
                    currentPage = data["currentPage"];
                    rows = 15;
                    var list = data["list"];

                    var htmlstr = "";
                    var sumLon = 0;
                    var sumLat = 0;
                    var count = 0;
                    $.each(list, function (index, value) {

                        //公司名称
                        var cname = value.cname;
                        //职位名称
                        var janme = value.jname;
                        //最低工资
                        var minwage = value.minwage;
                        //最高工资
                        var maxwage = value.maxwage;


                        //公司省市位置
                        var province = value.province;
                        var lon = value.lon;
                        var lat = value.lat;
                        //职业亮点
                        var highlights = isEmpty(value.highlights)?"暂无":value.highlights;
                        //工作经验要求
                        var erequir = isEmpty(value.erequir)?"暂无":value.erequir;

                        htmlstr += "<tr class='thejob' data-toggle='popover' data-placement='top' data-trigger='hover' data-title='其他信息' data-content='职业亮点："
                            + highlights + "&工作经验要求："+erequir+"' >";
                        htmlstr += "<td>" + (index + 1) + "</td>";
                        htmlstr += "<td>" + cname + "</td>";
                        htmlstr += "<td>" + janme + "</td>";
                        htmlstr += "<td>" + province + "</td>";
                        //最低工资 - 最高工资
                        htmlstr += "<td>" + minwage + "-" + maxwage + "</td>";
                        htmlstr += "<td class='hidden' id='td_lon' >" + lon + "</td>";
                        htmlstr += "<td class='hidden' id='td_lat'>" + lat + "</td>";
                        htmlstr += "</tr>";

                        sumLon += lon;
                        sumLat += lat;
                        count++;

                    });
                    // map.centerAndZoom(new BMap.Point(sumLon/count, sumLat/count), 11);
                    map.panTo(new BMap.Point(sumLon/count, sumLat/count));
                    $(".thejob").remove();
                    $("#table").append(htmlstr);


                    //页码下方提示信息
                    $("#label_sinfo").html(totalCount + "条记录，共" + totalPage + "页");
                    searchTotalPage = totalPage;
                }
            });
            //初始化popover
            $('[data-toggle="popover"]').popover();

            //列表每行绑定单击事件
            //刷新列表删除之前添加的点
            deleteMyOverlay(myMarker);
            $(".thejob").on('click',function () {
                //删除之前添加的点
                deleteMyOverlay(myMarker);
                var lon = $(this).children("#td_lon").html();
                var lat = $(this).children("#td_lat").html();
                var markPoint = new BMap.Point(lon, lat);
                // 创建标注
                var marker = new BMap.Marker(markPoint);
                //跳转到位置
                map.panTo(markPoint);
                // 将标注添加到地图中
                map.addOverlay(marker);
                //跳动的动画
                marker.setAnimation(BMAP_ANIMATION_BOUNCE);
                myMarker.push(marker);

            });

            return searchTotalPage;
        }

        //删除之前添加的点
        function deleteMyOverlay(obj) {
            for (i = 0; i <= obj.length; i++) {
                map.removeOverlay(obj[i]);
            }
        }

        /**
         * 获取分页
         * @param totalPage  页码总量
         * @param currentPage 当前页码
         * @returns {String}
         */
        function getPagination(totalPage, currentPage){

            var paginationInfo = "<ul class='pagination .pagination-sm' >";
            if (currentPage == 1) {
                paginationInfo += "<li class='disabled'><a href='javascript:void(0);' onclick='refreshPages("+totalPage+" , "+(currentPage-1) + ");searchJob(" + (currentPage-1) + ")'"+">«</a></li>";
            }else {
                //前一页
                paginationInfo += "<li><a href='javascript:void(0);' onclick='refreshPages("+totalPage+" , "+(currentPage-1) + ");searchJob(" + (currentPage-1) + ")'"+">«</a></li>";

            }

            if(totalPage<=10){
                //totalPage<=10
                for(var i=1; i<=totalPage; i++){
                    if (i == currentPage) {
                        paginationInfo += "<li class='active'> <a href='javascript:void(0);' onclick='refreshPages(" + totalPage + " , " + i + " );searchJob(" + i + ")'>" + i + " </a></li>";
                    }else {
                        paginationInfo += "<li><a href='javascript:void(0);' onclick='refreshPages(" + totalPage + " , " + i + " );searchJob(" + i + ")'>" + i + " </a></li>";
                    }
                }
            }
            else{
                //totalPage > 10
                if(currentPage<=3){
                    for(var i=1; i<=currentPage+2; i++){
                        //页码1、2
                        if (i == currentPage) {
                            paginationInfo += "<li class='active'> <a href='javascript:void(0);' onclick='refreshPages(" + totalPage + " , " + i + ");searchJob(" + i + ")'>" + i + "</a></li>";
                        } else {
                            paginationInfo += "<li > <a href='javascript:void(0);' onclick='refreshPages("+ totalPage + " , " + i + ");searchJob(" + i + ")'>" + i + "</a></li>";
                        }
                    }
                    paginationInfo += "<li><a href='javascript:void(0);'>...</a></li>";
                    //最后一页的页码
                    paginationInfo += "<li><a href='javascript:void(0);' onclick='refreshPages(" + totalPage + " , " + totalPage + ");searchJob(" + totalPage + ")'>" + totalPage + "</a></li>";
                }else if(currentPage<=totalPage-5){
                    //totalPage > 10   currentPage > 3 currentPage<=totalPage-5，  页码在中间部分
                    //页码为1的代码
                    paginationInfo += "<li><a href='javascript:void(0);' onclick='refreshPages("+totalPage+" , "+ 1 +");searchJob(1)'>" + 1 + "</a></li>";

                    //页码1后面的省略号
                    paginationInfo += "<li><a href='javascript:void(0);'>...</a></li>";

                    //中间部分代码
                    for(var i=currentPage-1; i<=currentPage+2; i++){
                        if (i == currentPage) {
                            paginationInfo += "<li class='active'> <a href='javascript:void(0);' onclick='refreshPages(" + totalPage + " , " + i + ");searchJob(" + i + ")'>" + i + "</a></li>";
                        } else {
                            paginationInfo += "<li> <a href='javascript:void(0);' onclick='refreshPages(" + totalPage + " , " + i + ");searchJob(" + i + ")'>" + i + "</a></li>";
                        }
                    }
                    //后面的省略号
                    paginationInfo += "<li><a href='javascript:void(0);'>...</a></li>";
                    //最后一个页码
                    paginationInfo += "<li><a href='javascript:void(0);' onclick='refreshPages("+totalPage+" , "+totalPage+");searchJob(" + totalPage + ")'>"+totalPage+"</a></li>";
                }else{
                    //totalPage > 10  并且currentPage > totalPage-5 显示后面的页码

                    //页码1
                    paginationInfo += "<li><a href='javascript:void(0);' onclick='refreshPages("+totalPage+" , "+1+");searchJob(1)'>"+1+"</a></li>";
                    //省略号
                    paginationInfo += "<li><a href='javascript:void(0);'>...</a></li>";
                    //最后几位页码
                    for(var i=currentPage-1; i<=totalPage; i++){
                        if (i == currentPage) {
                            paginationInfo += "<li class='active'> <a href='javascript:void(0);' onclick='refreshPages("+totalPage+" , "+i+");searchJob(" + i + "'>"+i+"</a></li>";
                        }else {
                            paginationInfo += "<li> <a href='javascript:void(0);' onclick='refreshPages("+totalPage+" , "+i+");searchJob(" + i + ")'>"+i+"</a></li>";
                        }
                    }
                }
            }

            //下一页
            if (currentPage == totalPage) {
                paginationInfo += "<li class='disabled'> <a href='javascript:void(0);' onclick='refreshPages(" + totalPage + " , " + (currentPage + 1) + ");searchJob(" + (currentPage + 1) + ")'" + ">»</a></li>";
            } else {
                paginationInfo += "<li><a href='javascript:void(0);' onclick='refreshPages("+totalPage+" , "+(currentPage+1)+");searchJob(" + (currentPage+1) + ")'"+">»</a></li>";
            }
            paginationInfo += "</ul>";
            //返回结果
            return paginationInfo;
        }

        /**
         * 刷新页码方法
         * @param totalPage
         * @param currentPage
         */
        function refreshPages(totalPage, currentPage) {

            //安全判断
            if (currentPage < 1 ) {
                currentPage = 1;
            }
            if (currentPage > totalPage) {
                currentPage = totalPage;
            }
            var paginationInfo = getPagination(totalPage, currentPage);
            $("#nav_navigation").html(paginationInfo);


        }

        /**
         * 休眠方法
         * @param n
         */
        function sleep(n) {

            var start = new Date().getTime();

            while(true) {
                if(new Date().getTime()-start > n);
                break;
            }

        }


        //判断字符是否为空的方法
        function isEmpty(obj){
            if(typeof obj == "undefined" || obj == null || obj == ""){
                return true;
            }else{
                return false;
            }
        }


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

                <%--显示登录信息--%>
                <c:if test="${not empty user.username}">
                    <img src="img/XG003.png" width="20px" height="20px" class="navbar-text" title="${user.username}">
                    <p class="navbar-text"><a href="javascript:void(0);" class="navbar-link"><span class="label label-success">${user.username}</span></a>，欢迎登录</p>
                </c:if>

            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
<%--                    <li><a href="#">Link <span class="sr-only">(current)</span></a></li>--%>
                    <li><a href="javascript:locationNow()">定位</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">底图切换 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="javascript:changeMapType('BMAP_NORMAL_MAP')">普通街道地图</a></li>
                            <li><a href="javascript:changeMapType('BMAP_SATELLITE_MAP')">卫星地图</a></li>
                            <li><a href="javascript:changeMapType('BMAP_HYBRID_MAP')">卫星和路网的混合视图</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a id="search_btn" href="javascript:void(0);">查找</a></li>
                </ul>
                <form class="navbar-form navbar-right" id="search_form">
                    <div class="form-group">
                        <label for="search_cname">公司名称</label>
                        <input type="text" class="form-control" id="search_cname" name="cname"
                               placeholder="阿里巴巴">
                    </div>
                    <div class="form-group">
                        <label for="search_jname">职位名称</label>
                        <input type="text" class="form-control" id="search_jname" name="jname"
                               placeholder="java开发工程师">
                    </div>
                    <div class="form-group">
                         <label for="search_province">省市位置</label>
                         <input type="text" class="form-control" id="search_province" name="province"
                                   placeholder="北京">
                    </div>
                    <div class="form-group">
                        <label for="search_minwage">最低工资</label>
                        <select class="form-control" id="search_minwage" name="minwage">
                            <option>0</option>
                            <option>3000</option>
                            <option>4000</option>
                            <option>5000</option>
                            <option>6000</option>
                            <option>7000</option>
                            <option>10000</option>
                            <option>15000</option>
                            <option>20000</option>
                            <option>30000</option>
                        </select>
                    </div>
                </form>

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div><!-- /.container-fluid -->
<!--    主体-->
<div class="container-fluid">
    <div class="row">
        <!--        地图-->
        <div class="col-md-8" id="col_map">
            <div id="mapid">

            </div>
        </div><!--地图end-->

        <div class="col-md-4" id="col_tab">
            <div class="container-fluid" id="div_tab">

                <div class="table-responsive">
                    <table class="table table-condensed table-bordered table-hover" id="table">
                        <tr class="success">
                            <th colspan="5" class="text-center">全国招聘信息</th>
                        </tr>
                        <tr class="success">
                            <th class="text-center">序号</th>
                            <th class="text-center">公司名称</th>
                            <th class="text-center">职位名称</th>
                            <th class="text-center">公司省市</th>
                            <th class="text-center">工资</th>
                        </tr>

                    </table>
                </div>

                <%--页码部分--%>
                <nav aria-label="Page navigation" id="nav_navigation">

                </nav>

                <div class="form-group form-inline">
                    <label for="inp_skipPage" id="label_sinfo">(加载中...)条记录，共(加载中...)页</label>
                    <input type="text" class="form-control" id="inp_skipPage" name="skipPage"
                           placeholder="跳转页码">
                    <button  class="btn btn-default" id="btn_skipPage">跳转</button>
                </div>

            </div>
        </div>

    </div>
</div>

<!--    底部-->
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-inverse navbar-default navbar-fixed-bottom" id="nav_bottom">
            <div class="container-fluid">
                <p class="text-center" id="p_bottom">Copyright © 2019 <a href="https://blog.xgblack.cn"
                                                                         class="navbar-link">小光&臾离blog</a><br>苏ICP备18045687号-2</p>


            </div><!-- /.container-fluid -->
        </nav>
    </div>
</div>

<script src="js/mapfunction.js"></script>

<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

</body>
</html>

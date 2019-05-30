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

    <!-- Bootstrap -->
    <link rel="stylesheet" href="static/bootstrap-3.3.7-dist/css/bootstrap.css">

    <link rel="stylesheet" href="css/mapstyle.css">


    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=zKfGbrgNomYkFrUEy8a09Tw1PLudhUnU">
        //v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
    </script>

    <script src="js/heatmap_baidu.js"></script>
<%--    <script src="js/heatmap-origin.js"--%>
<%--    <script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>--%>


    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery-3.4.1.min.js"></script>




    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <script>

        //入口函数
        $(function () {
            //总记录数
            var totalCount ;
            //总页码
            var totalPage ;
            //默认当前页码为1
            var currentPage = 1;

            //每页记录数，默认为10 （且一般不会更改）
            var rows = 10;

            //分页栏显示的页数，默认为10 （且一般不会更改）
            var paginationmax = 10;

            //首次打开页面,加载列表
            search(currentPage);
            //首次打开页面，加载全部热力图的点
            //优化查询显示性能
            // TODO
            $.get(
                "${pageContext.request.contextPath}/findJobsServlet",
                function (allData) {
                    var heatmapData = {
                        max : allData.length + 1,
                        data: JSON.parse(allData)
                    };
                    //配置
                    var cfg = {
                        "radius": 20,
                        "maxOpacity": 0.99,
                        "minOpacity": 0.55,
                        "blur":0.95,
                        "gradient":{
                            0.03:'rgb(82,159,233)',
                            0.04:'rgb(14, 246, 243)',
                            0.059:'rgb(0, 255, 0)',
                            0.06:'rgb(252, 255, 0)',
                            0.1:'rgb(255, 92, 10)',
                            1:'rgb(255, 0, 0)'
                        }
                    };

                    if(!isSupportCanvas()){
                        alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
                    }

                    heatmapOverlay = new BMapLib.HeatmapOverlay(cfg);
                    map.addOverlay(heatmapOverlay);
                    heatmapOverlay.setDataSet(heatmapData);
                }
            );



            //搜索按钮绑定单击事件
            $("#search_btn").click(function () {
                search(currentPage);
            });


            //搜索方法
            function search(currentPage) {
                //调用分页查询
                pageTurningSearch(currentPage);
                //生成分页
                paginationInit(totalCount,totalPage,currentPage,paginationmax);


            }

            //翻页查询
            function pageTurningSearch(currentPage) {
                $.get("${pageContext.request.contextPath}/findJobsByPage",
                    {
                        currentPage: currentPage,
                        rows: 10,
                        cname:$("#search_cname").val() ,
                        jname:$("#search_jname").val(),
                        province:$("#search_province").val(),
                        minwage:$("#search_minwage").val()
                    },
                    function (data, index) {
                        totalCount = data["totalCount"];
                        totalPage = data["totalPage"];
                        currentPage = data["currentPage"];
                        rows = 10;
                        var list = data["list"];

                        var htmlstr = "";
                        $.each(list, function (index, value) {

                            //公司名称
                            var cname = value.cname;
                            //职位名称
                            var janme = value.jname;
                            //最低工资
                            var minwage = value.minwage;
                            //最高工资
                            var maxwage = value.maxwage;
                            //最低工资 - 最高工资
                            var wage = minwage + "-" + maxwage;

                            //公司省市位置
                            var province = value.province;
                            //职业亮点
                            var highlights = value.highlights;
                            //工作经验要求
                            var erequir = value.erequir;
                            //公司简介
                            var cintroduction = value.cintroduction;
                            //职业描述
                            var jintroduction = value.jintroduction;

                            htmlstr += '<tr class="thejob">';
                            htmlstr += '<td>' + (index + 1) + '</td>';
                            htmlstr += '<td>' + cname + '</td>';
                            htmlstr += '<td>' + janme + '</td>';
                            htmlstr += '<td>' + wage + '</td>';
                            htmlstr += '</tr>';
                        });
                        $(".thejob").remove();
                        $("#table").append(htmlstr);
                    });
            }



            //凡是带有pagination = pagination_new属性的元素，都会生成分页，这样设计方便一个页面中有多个不同的分页
            function paginationInit(totalCount,totalPage,currentPage,paginationmax){
                $('[pagination = pagination_new]').each(function(){
                    initPagination($(this),totalCount,totalPage,currentPage,paginationmax)
                })
            }

            //  生成页码
            function initPagination(element,totalCount,totalPage,currentPage,paginationmax){
                var content = "";
                //页码不同情况
                if (totalPage < 1) {
                    //没有数据
                    content = "<ul class='pagination'>" +
                        "<li value='pre' class='disabled'" +
                        "a href='javascript:void(0);'>«</a>" +
                        "</li>" +
                        "<li value='next' class='disabled'>" +
                        "<a href='javascript:void(0);'>»</a>" +
                        "</li>" +
                        "</ul>";

                }else if (totalPage >= 1 && totalPage < paginationmax) {
                    //当总页数小于最大显示的页码数(页码一行可以显示)
                    content =
                        "<ul class='pagination'>" +
                        "<li value='pre'>" +
                        "<a href='javascript:void(0);'>«</a>" +
                        "</li>";
                    for (var i = 0; i < totalPage; i++) {
                        content +=
                            "<li value='"+ (i + 1) +"'>" +
                            "<a href='javascript:void(0);'>" + (i + 1) +
                            "</a>" +
                            "</li>"
                    }
                    content +=
                        "<li value='next'>" +
                        "<a href='javascript:void(0);'>»</a>" +
                        "</li>" +
                        "</ul>";

                }else if(totalPage >= 1 && currentPage <= totalPage && paginationmax <= totalPage){
                    //当总页数大于最大显示的页码数(页码一行不可以显示，需要隐藏)
                    content =
                        "<ul class='pagination'>" +
                        "<li value='pre'>" +
                        "<a href='javascript:void(0);'>«</a>" +
                        "</li>";
                    for (var i = 0; i < totalPage; i++) {
                        content +=
                            "<li value='"+ (i + 1) +"'>" +
                            "<a href='javascript:void(0);'>" + (i + 1) +
                            "</a>" +
                            "</li>"
                    }
                    content +=
                        "<li value='next'>" +
                        "<a href='javascript:void(0);'>»</a>" +
                        "</li>" +
                        "</ul>";


                }else{
                    console.log('分页自定义属性不合理');
                }

                //先清空
                element.empty();
                element.append(content);

                //为设置为当前页的页签添加样式active
                element.children('ul').children('li[value = '+ currentPage +']').addClass('active');
                element.children('ul').children('li').click(clickChange);
                element.children('ul').children('li').click(processData);
                //显示那几个页签 传入任意li元素即可
                // pageShow(element.children('ul').children('li[value = '+ currentPage +']'));

                //页码下方提示信息
                $("#label_sinfo").html(totalCount + "条记录，共" + totalPage + "页");




            }

            //点击页签时候样式的变化
            function clickChange(ev){
                ev = event || window.event;

                //展示哪些页码
                pageShow($(ev.target).parent());




                $(ev.target).parent().parent().children('li').each(function(index,item){
                    if($(item).hasClass('active')){
                        $(item).removeClass('active');
                    }
                });

                //点击页码页签
                if($(ev.target).parent().attr('value') != 'pre' && $(ev.target).parent().attr('value') != 'next'){
                    currentPage = Number($(ev.target).parent().attr('value'));
                    $(ev.target).parent().addClass('active');
                    if (currentPage <= 1) {
                        $(ev.target).parent().parent().children('li[value = pre]').addClass('disabled');
                    }else {
                        $(ev.target).parent().parent().children('li[value = pre]').removeClass('disabled');
                    }
                    if (currentPage >= totalPage) {
                        $(ev.target).parent().parent().children('li[value = next]').addClass('disabled');
                    } else {
                        $(ev.target).parent().parent().children('li[value = next]').removeClass('disabled');
                    }

                //点击上一页页签
                }else if($(ev.target).parent().attr('value') == 'pre'){
                    currentPage -= 1;
                    if(currentPage <= 1){
                        currentPage = 1;
                        $(ev.target).parent().parent().children('li[value = 1]').addClass('active');
                        $(ev.target).parent().parent().children('li[value = pre]').addClass('disabled');
                    }else{
                        $(ev.target).parent().parent().children('li[value = '+ currentPage.toString() +']').addClass('active');
                        $(ev.target).parent().parent().children('li[value = pre]').removeClass('disabled');
                        $(ev.target).parent().parent().children('li[value = next]').removeClass('disabled');
                    }
//                点击下一页页签
                }else if($(ev.target).parent().attr('value') == 'next'){
                    currentPage += 1;
                    if(currentPage >= totalPage){
                        currentPage = totalPage;
                        $(ev.target).parent().parent().children('li[value = '+ totalPage +']').addClass('active');
                        $(ev.target).parent().parent().children('li[value = next]').addClass('disabled');
                    }else{
                        $(ev.target).parent().parent().children('li[value = '+ currentPage.toString() +']').addClass('active');
                        $(ev.target).parent().parent().children('li[value = next]').removeClass('disabled');
                        $(ev.target).parent().parent().children('li[value = pre]').removeClass('disabled');
                    }
                }
            }
            //展示哪些页码 要用一个实际的分页找规律
            function pageShow(element){
                if(Number(currentPage) >= 1 && Number(currentPage) <= parseInt(.5 * Number(paginationmax))){
                    element.parent().children('li').each(function(index,item){
                        if(Number($(item).attr('value')) >= 1 + Number(paginationmax) && Number($(item).attr('value')) <= Number(totalPage)){
                            $(item).css('display','none')
                        }else{
                            $(item).css('display','inline-block')
                        }
                    });
                }else if(Number(currentPage) > parseInt(.5 * Number(paginationmax)) && Number(currentPage) <= Number(totalPage) - parseInt(.5 * Number(paginationmax))){
                    element.parent().children('li').each(function(index,item){
                        if((Number($(item).attr('value')) >= 1 && Number($(item).attr('value')) <= Number(currentPage) - parseInt(.5 * Number(paginationmax))) || (Number($(item).attr('value')) > Number(currentPage) + parseInt(.5 * Number(paginationmax)) && Number($(item).attr('value')) <= Number(totalPage))){
                            $(item).css('display','none')
                        }else{
                            $(item).css('display','inline-block')
                        }
                    });
                }else if(Number(currentPage) > Number(totalPage) - parseInt(.5 * Number(paginationmax))){
                    element.parent().children('li').each(function(index,item){
                        if(Number($(item).attr('value')) >= 1 && Number($(item).attr('value')) <= Number(totalPage) - Number(paginationmax)){
                            $(item).css('display','none')
                        }else{
                            $(item).css('display','inline-block')
                        }
                    });
                }
            }

            //页面切换时候的处理函数。比如发ajax根据不同页码获取不同数据展示数据等，用户自行配置。
            function processData(){
                //console.log('当前页码',currentPage);
                pageTurningSearch(currentPage);
            }



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

                <%--显示登录信息--%>
                <c:if test="${not empty user.username}">
                    <img src="img/XG003.png" width="20px" height="20px" class="navbar-text" title="${user.username}">
                    <p class="navbar-text"><a href="javascript:void(0);" class="navbar-link"><span class="label label-success">${user.username}</span></a>，欢迎登录</p>
                </c:if>

            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Dropdown <span class="caret"></span></a>
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
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a id="search_btn" href="javascript:void(0);">查找</a></li>
                </ul>
                <form class="navbar-form navbar-right" id="search_form">
                    <%--            <button type="submit" class="btn btn-default">Submit</button>--%>
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


                    </table>
                </div>

                <%--页码部分--%>
                <nav aria-label="Page navigation"  pagination="pagination_new" >
<%--                    <ul class="pagination">--%>
<%--                        <li id="li_upPage">--%>
<%--                            <a href="#" aria-label="Previous">--%>
<%--                                <span aria-hidden="true">&laquo;</span>--%>
<%--                            </a>--%>
<%--                        </li>--%>

<%--                        <li class="active"><a href="#">1</a></li>--%>
<%--                        <li><a href="#">2</a></li>--%>
<%--                        <li><a href="#">3</a></li>--%>
<%--                        <li><a href="#">4</a></li>--%>
<%--                        <li><a href="#">5</a></li>--%>

<%--                        <li><a href="#">2</a></li>--%>
<%--                        <li><a href="#">3</a></li>--%>
<%--                        <li><a href="#">4</a></li>--%>
<%--                        <li><a href="#">5</a></li>--%>
<%--                        <li><a href="#">2</a></li>--%>
<%--                        <li><a href="#">3</a></li>--%>

<%--                        <li id="li_downPage">--%>
<%--                            <a href="#" aria-label="Next">--%>
<%--                                <span aria-hidden="true">&raquo;</span>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                    </ul>--%>

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
                                                                         class="navbar-link">小光&臾离blog</a></p>

            </div><!-- /.container-fluid -->
        </nav>
    </div>
</div>

<script src="js/mapfunction.js"></script>

<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

</body>
</html>

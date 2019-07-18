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
package cn.xgblack.heatmap.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录检测
 */
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        //判断是否为登录相关的资源，排除掉css，js等资源
        String requestURI = request.getRequestURI();
        if (
                requestURI.contains("/login.jsp")||
                        requestURI.contains("/loginServlet") ||
                        requestURI.contains("/css/") ||
                        requestURI.contains("/js/") ||
                        requestURI.contains("/fonts/") ||
                        requestURI.contains("/img/") ||
                        requestURI.contains("/checkCodeServlet")||
                        requestURI.contains("/regist.jsp")||
                        requestURI.contains("/registerServlet")
        ){
            //包含，用户想要登陆，直接放行
            chain.doFilter(req, resp);
        }else {
            //不包含，判断用户是否已经登录
            Object user = request.getSession().getAttribute("user");
            if (null != user){
                //已经登录
                chain.doFilter(req, resp);
            }else {
                //未登录，跳转登录页面
                request.setAttribute("login_msg","您尚未登录，请先登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}

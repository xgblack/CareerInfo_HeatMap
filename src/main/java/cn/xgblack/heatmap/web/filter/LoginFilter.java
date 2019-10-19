package cn.xgblack.heatmap.web.filter;

import cn.xgblack.heatmap.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        //判断是否为登录相关的资源，排除掉css，js等资源
        String requestURI = request.getRequestURI();
        if (
                requestURI.contains("/user") ||
                        requestURI.contains("/css/") ||
                        requestURI.contains("/js/") ||
                        requestURI.contains("/fonts/") ||
                        requestURI.contains("/images/") ||
                        requestURI.contains("/static/") ||
                        requestURI.contains("/checkCode") ||
                        requestURI.contains(".ico")
        ){
            //包含，用户想要登陆，直接放行
            chain.doFilter(req, resp);
        }else {
            //不包含，判断用户是否已经登录
            User user = (User) request.getSession().getAttribute("user");
            if (null != user){
                //已经登录
                chain.doFilter(req, resp);
            }else {
                //未登录，跳转登录页面
                request.setAttribute("login_msg","您尚未登录，请先登录");
                request.getRequestDispatcher("/user/login").forward(request,resp);
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}

package cn.xgblack.heatmap.web.servlet;

import cn.xgblack.heatmap.domain.User;
import cn.xgblack.heatmap.service.UserService;
import cn.xgblack.heatmap.service.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 小光
 * @date 2019/5/26
 * className: RegisterServlet
 * description: 验证注册信息
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@WebServlet(urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session
        HttpSession session = request.getSession();

        //获取用户输入的验证码
        String verifycode = request.getParameter("verifycode");
        //获取生成的验证码
        String checkcode_session = (String)session.getAttribute("checkcode_session");
        //去除生成的验证码，以防止验证码被重复使用的bug
        session.removeAttribute("checkcode_session");
        //先判断验证码是否正确
        if (!verifycode.equalsIgnoreCase(checkcode_session)) {
            //验证码不对，存储错误信息，并跳转
            request.setAttribute("regist_msg","验证码输入错误！");
            request.getRequestDispatcher("regist.jsp").forward(request,response);

            //结束方法
            return;
        }

        User registerUser = new User();
        //获取map数据，并封装对象
        try {
            BeanUtils.populate(registerUser,request.getParameterMap());
        } catch (IllegalAccessException e) {
            System.out.println("错误003:" + e);
            request.setAttribute("regist_msg","注册错误！，请联系管理员（错误003）");
            request.getRequestDispatcher("regist.jsp").forward(request,response);
        } catch (InvocationTargetException e) {
            System.out.println("错误004:" + e);
            request.setAttribute("regist_msg","注册错误！，请联系管理员（错误004）");
            request.getRequestDispatcher("regist.jsp").forward(request,response);
        }

        //调用service层方法
        UserService service = new UserServiceImpl();

        // 调用方法查看用户名是否已经被注册
        if (service.usernameIsExist(registerUser)) {
            //用户名已存在
            request.setAttribute("regist_msg", "用户名已存在");
            request.getRequestDispatcher("regist.jsp").forward(request, response);

            return;
        }

        if (service.regist(registerUser)) {
            //注册成功
            request.setAttribute("login_msg", "注册成功，请登录");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            //注册失败
            request.setAttribute("regist_msg", "注册失败，请重试");
            request.getRequestDispatcher("regist.jsp").forward(request, response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

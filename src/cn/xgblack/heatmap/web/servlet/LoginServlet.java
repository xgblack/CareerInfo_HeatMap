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

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
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
            request.setAttribute("login_msg","验证码输入错误！");
            request.getRequestDispatcher("login.jsp").forward(request,response);

            //结束方法
            return;
        }



        //封装对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,request.getParameterMap());
        } catch (IllegalAccessException e) {
            System.out.println("错误001:" + e);
            request.setAttribute("login_msg","登录错误！，请联系管理员（错误001）");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        } catch (InvocationTargetException e) {
            System.out.println("错误002:" + e);
            request.setAttribute("login_msg","登录错误！，请联系管理员（错误002）");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

        UserService service = new UserServiceImpl();
        //调用service层的方法，查询登录是否成功
        User user = service.login(loginUser);
        if (user == null) {
            //用户名或密码错误
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else {
            //登陆成功
            session.setAttribute("user",user);
            //跳转页面（直接重定向）
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

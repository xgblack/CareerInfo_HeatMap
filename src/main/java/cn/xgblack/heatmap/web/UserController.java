package cn.xgblack.heatmap.web;

import cn.xgblack.heatmap.entity.User;
import cn.xgblack.heatmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 跳转注册页面
     * @return 注册
     */
    @RequestMapping("/register")
    public String toRegister(){
        return "user/regist";
    }

    /**
     * 跳转登录页面
     * @return 登录
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "user/login";
    }

    /**
     * 注册验证
     * @param request 请求
     * @param response 响应
     * @param session 会话
     * @param verifycode 验证码
     * @param registerUser 注册用户信息
     * @throws ServletException 异常//TODO
     * @throws IOException 异常//TODO
     */
    @RequestMapping("/registerCheck")
    public void register(HttpServletRequest request, HttpServletResponse response, HttpSession session , String verifycode , User registerUser) throws ServletException, IOException {

        //获取生成的验证码
        String checkcode_session = (String)session.getAttribute("checkcode_session");

        //去除生成的验证码，以防止验证码被重复使用的bug
        session.removeAttribute("checkcode_session");

        //先判断验证码是否正确
        if (verifycode != null && !"".equals(verifycode) && !verifycode.equalsIgnoreCase(checkcode_session)) {

            //验证码不对，存储错误信息，并跳转
            request.setAttribute("regist_msg","验证码输入错误！");
            request.getRequestDispatcher("/user/register").forward(request,response);

            //结束方法
            return;

        }

        // 调用方法查看用户名是否已经被注册
        if (service.usernameIsExist(registerUser.getUsername())) {
            //用户名已存在
            request.setAttribute("regist_msg", "用户名已存在");
            request.getRequestDispatcher("/user/register").forward(request, response);

            return;
        }

        if (service.regist(registerUser)) {
            //注册成功
            request.setAttribute("login_msg", "注册成功，请登录");
            request.getRequestDispatcher("/user/login").forward(request, response);
        } else {
            //注册失败
            request.setAttribute("regist_msg", "注册失败，请重试");
            request.getRequestDispatcher("/user/register").forward(request, response);
        }

    }

    /**
     * 登录验证
     * @param request 请求
     * @param response 响应
     * @param session 会话
     * @param verifycode 验证码
     * @param loginUser 登录用户信息
     * @throws ServletException //TODO
     * @throws IOException //TODO
     */
    @RequestMapping("/loginCheck")
    public void login(HttpServletRequest request,HttpServletResponse response,HttpSession session, String verifycode ,User loginUser) throws ServletException, IOException {

        //获取生成的验证码
        String checkcode_session = (String)session.getAttribute("checkcode_session");

        //去除生成的验证码，以防止验证码被重复使用的bug
        session.removeAttribute("checkcode_session");

        //先判断验证码是否正确
        if (verifycode != null && !"".equals(verifycode) && !verifycode.equalsIgnoreCase(checkcode_session)) {

            //验证码不对，存储错误信息，并跳转
            request.setAttribute("login_msg","验证码输入错误！");
            request.getRequestDispatcher("/user/login").forward(request,response);

            //结束方法
            return;

        }
        //调用service层的方法，查询登录是否成功
        User user = service.login(loginUser);
        if (user == null) {
            //用户名或密码错误
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/user/login").forward(request,response);
        }else {
            //登陆成功
            session.setAttribute("user",user);
            //跳转页面（直接重定向）
            response.sendRedirect(request.getContextPath());
        }

    }


}

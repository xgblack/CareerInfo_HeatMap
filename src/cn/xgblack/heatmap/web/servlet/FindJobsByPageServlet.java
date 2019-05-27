package cn.xgblack.heatmap.web.servlet;

import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.domain.PageBean;
import cn.xgblack.heatmap.service.JobService;
import cn.xgblack.heatmap.service.JobServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findJobsByPage")
public class FindJobsByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前页码
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        //判断是否为空

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "10";
        }

        //调用service层查询
        JobService service = new JobServiceImpl();
        PageBean<Job> pb = service.findUserByPage(currentPage, rows);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pb);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
        //System.out.println(json);



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

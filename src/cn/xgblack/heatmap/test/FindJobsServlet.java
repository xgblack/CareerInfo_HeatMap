package cn.xgblack.heatmap.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findJobsServlet")
public class FindJobsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //JobDaoImpl dao = new JobDaoImpl();
        //List<Job> jobs = dao.showAllJobs();

        //ObjectMapper mapper = new ObjectMapper();
        //String json = mapper.writeValueAsString(jobs);

        //System.out.println(json);

        //response.setContentType("text/json;charset=utf-8");

        //response.getWriter().write(json);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

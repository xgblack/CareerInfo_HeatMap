package cn.xgblack.heatmap.web.servlet;

import cn.xgblack.heatmap.domain.JobHeatmapData;
import cn.xgblack.heatmap.service.JobService;
import cn.xgblack.heatmap.service.JobServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/findAllJobsPoints")
public class FindAllJobsPoints extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JobService service = new JobServiceImpl();

        //调用JobService方法，查询Job全部数据（不分页）便于展示所有点
        List<JobHeatmapData> jobHeatmapDatas = service.findAllJobPonits();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(jobHeatmapDatas);


        response.setContentType("text/html;charset=utf-8");

        response.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

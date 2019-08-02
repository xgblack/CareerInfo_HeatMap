package cn.xgblack.heatmap.web;

import cn.xgblack.heatmap.dto.PageBean;
import cn.xgblack.heatmap.dto.SearchCondition;
import cn.xgblack.heatmap.entity.Job;
import cn.xgblack.heatmap.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 小光
 * @date 2019/8/1 23:48
 * className: JobController
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService service;

    @RequestMapping("/findJobs")
    @ResponseBody
    public PageBean<Job> findJobs(SearchCondition searchCondition){
        //获取当前页码
        Integer currentPage = searchCondition.getCurrentPage();
        Integer rows = searchCondition.getRows();

        //判断是否不大于0
        if (currentPage == null || currentPage <= 0) {
            searchCondition.setCurrentPage(1);
        }
        if (rows == null || rows <= 0) {
            searchCondition.setRows(15);
        }

        //调用service层查询
        return service.findJobByPage(searchCondition);

    }


}

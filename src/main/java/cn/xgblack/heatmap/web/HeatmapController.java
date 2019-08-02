package cn.xgblack.heatmap.web;

import cn.xgblack.heatmap.dto.JobHeatmapData;
import cn.xgblack.heatmap.dto.SearchCondition;
import cn.xgblack.heatmap.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 小光
 * @date 2019/8/1 23:49
 * className: HeatmapController
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Controller
@RequestMapping("/heatmap")
public class HeatmapController {

    @Autowired
    private JobService service;

    @RequestMapping("/findJobsPoints")
    @ResponseBody
    public List<JobHeatmapData> findJobsPoints(SearchCondition condition){
        //通过条件搜索符合条件的点
        List<JobHeatmapData> jobHeatmapDatas = service.findSomePoints(condition);

        return jobHeatmapDatas;
    }


}

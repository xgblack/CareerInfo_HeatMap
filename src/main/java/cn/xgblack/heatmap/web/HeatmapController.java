package cn.xgblack.heatmap.web;

import cn.xgblack.heatmap.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


}

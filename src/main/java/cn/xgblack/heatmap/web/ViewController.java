package cn.xgblack.heatmap.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 小光
 * @date 2019/10/17 15:14
 * className: ViewController
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Controller
public class ViewController {
    @RequestMapping("/")
    public String index(){
        return "success";
    }
}

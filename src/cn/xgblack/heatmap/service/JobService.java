package cn.xgblack.heatmap.service;

import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.domain.PageBean;

import java.util.Map;

/**
 * @author 小光
 * @date 2019/5/26 15:01
 * Copyright(C),2018-2019,https://blog.xgblack.cn
 * interfaceName: JobService
 * description:
 */
public interface JobService {

    /**
     *
     * @param currentPage 当前页码
     * @param rows 每页显示条数
     * @param condition 分页+查询的条件
     * @return PageBean<User>
     */
    PageBean<Job> findJobByPage(String currentPage, String rows, Map<String, String[]> condition);
}

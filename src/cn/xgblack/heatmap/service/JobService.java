package cn.xgblack.heatmap.service;

import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.domain.PageBean;

/**
 * @author 小光
 * @date 2019/5/26 15:01
 * Copyright(C),2018-2019,https://blog.xgblack.cn
 * interfaceName: JobService
 * description:
 */
public interface JobService {

    /**
     * 根据页码查找
     * @param currentPage 当前页码
     * @param rows 每页显示条数
     * @return PageBean<User>
     */
    PageBean<Job> findUserByPage(String currentPage, String rows);
}

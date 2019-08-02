package cn.xgblack.heatmap.service;

import cn.xgblack.heatmap.dto.JobHeatmapData;
import cn.xgblack.heatmap.dto.PageBean;
import cn.xgblack.heatmap.dto.SearchCondition;
import cn.xgblack.heatmap.entity.Job;

import java.util.List;

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
     * @param searchCondition 分页+查询的条件
     * @return PageBean<User>
     */
    PageBean<Job> findJobByPage(SearchCondition searchCondition);

    /**
     * 直接查找所有Job数据,只包含jid,lat,lon,minwage,maxwage
     * @return List<Job>
     */
    List<JobHeatmapData> findAllJobPonits();

    /**
     * 按照索引和条数查询热力图的点
     * @param start 开始索引
     * @param eachPoints 每次查询热力图点的个数
     * @return List<JobHeatmapData>
     */
    List<JobHeatmapData> findSomeJob(String start, String eachPoints);

    /**
     * 根据查找条件加载热力图点
     * @param condition 查找条件
     * @return List<JobHeatmapData>
     */
    List<JobHeatmapData> findSomePoints(SearchCondition condition);

}

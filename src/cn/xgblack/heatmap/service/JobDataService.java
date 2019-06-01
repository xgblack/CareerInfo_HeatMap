package cn.xgblack.heatmap.service;

import cn.xgblack.heatmap.domain.JobHeatmapData;

import java.util.List;

/**
 * @author 小光
 * @date 2019/6/1 14:29
 * Copyright(C),2018-2019,https://blog.xgblack.cn
 * interfaceName: JobDataService
 * description:
 */
public interface JobDataService {

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

}

package cn.xgblack.heatmap.mapper;


import cn.xgblack.heatmap.dto.JobHeatmapData;
import cn.xgblack.heatmap.dto.SearchCondition;
import cn.xgblack.heatmap.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小光
 * @date 2019/5/26 15:00
 * Copyright(C),2018-2019,https://blog.xgblack.cn
 * interfaceName: JobMapper
 * description:
 */
@Mapper
@Repository
public interface JobMapper {

    /**
     * 查询总记录数
     * @param condition 查询的条件
     * @return totalCount总记录数
     */
    int findTotalCount(SearchCondition condition);


    /**
     * 查询分页的数据
     * @param condition 查询的条件
     * @return List<User>
     */
    List<Job> findByPage(SearchCondition condition);

    /**
     * 直接查找所有Job数据,只包含jid,lat,lon,(minwage,maxwage)/2
     * @return List<Job>
     */
    List<JobHeatmapData> findAllJobPonits();


    /**
     * 根据查找条件加载热力图点
     * @param condition 查找条件
     * @return List<JobHeatmapData>
     */
    List<JobHeatmapData> findSomePoints(SearchCondition condition);


    /**
     * 按照索引和条数查询热力图的点
     *   没有使用
     * @param start 开始索引
     * @param eachPoints 每次查询热力图点的个数
     * @return List<JobHeatmapData>
     */
    List<JobHeatmapData> findSomeJob(int start, int eachPoints);



}

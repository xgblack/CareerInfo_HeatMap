package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.domain.JobHeatmapData;

import java.util.List;
import java.util.Map;

/**
 * @author 小光
 * @date 2019/5/26 15:00
 * Copyright(C),2018-2019,https://blog.xgblack.cn
 * interfaceName: JobDao
 * description:
 */
public interface JobDao {

    /**
     * 直接查找所有Job数据,只包含jid,lat,lon,minwage,maxwage
     * @return List<Job>
     */
    List<JobHeatmapData> findAllJob();

    /**
     * 查询总记录数
     * @param condition 查询的条件
     * @return totalCount总记录数
     */
    int findTotalCount(Map<String, String[]> condition);


    /**
     * 查询分页的数据
     * @param start 开始缩阴
     * @param rows 每页条数
     * @param condition 查询的条件
     * @return List<User>
     */
    List<Job> findByPage(int start, int rows, Map<String, String[]>condition);
}

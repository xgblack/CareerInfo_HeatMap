package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.Job;

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
     * 查询总记录数
     * @param condition 查询的条件
     * @return totalCount总记录数
     */
    int findTotalCount(Map<String, Object> condition);


    /**
     * 查询分页的数据
     * @param condition 查询的条件
     * @return List<User>
     */
    List<Job> findByPage(Map<String, Object> condition);
}

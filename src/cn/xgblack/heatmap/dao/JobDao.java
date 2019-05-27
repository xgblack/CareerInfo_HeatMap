package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.Job;

import java.util.List;

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
     * @return totalCount总记录数
     */
    int findTotalCount();

    /**
     * 查询分页的数据
     * @param start 开始缩阴
     * @param rows 每页条数
     * @return List<User>
     */
    List<Job> findByPage(int start, int rows);
}

package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.JobHeatmapData;
import cn.xgblack.heatmap.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 小光
 * @date 2019/6/1 14:28
 * className: JobDataDaoImpl
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class JobDataDaoImpl implements JobDataDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 直接查找所有Job数据,只包含jid,lat,lon,minwage,maxwage
     * @return List<Job>
     */
    @Override
    public List<JobHeatmapData> findAllJobPonits() {
        //String sql = "SELECT jid,lat,lon,minwage,maxwage FROM job ;";
        String sql = "SELECT lat ,lon AS lng,minwage AS count FROM job ;";

        try {
            //查询
            List<JobHeatmapData> list = template.query(sql, new BeanPropertyRowMapper<JobHeatmapData>(JobHeatmapData.class));
            return list;
        } catch (DataAccessException e) {
            return null;
        }

    }


    /**
     * 按照索引和条数查询热力图的点
     * @param start 开始索引
     * @param eachPoints 每次查询热力图点的个数
     * @return List<JobHeatmapData>
     */
    @Override
    public List<JobHeatmapData> findSomeJob(int start, int eachPoints) {
        String sql = " lat ,lon AS lng,minwage AS count FROM job  LIMIT ? , ? ;";
        try {
            List<JobHeatmapData> points = template.query(sql, new BeanPropertyRowMapper<>(JobHeatmapData.class), start, eachPoints);
            return points;
        } catch (DataAccessException e) {
            return null;
        }
    }
}

package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 小光
 * @date 2019/5/26 15:01
 * className: JobDaoImpl
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class JobDaoImpl implements JobDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //public List<Job> showAllJobs(){
    //    String sql = "SELECT * FROM job LIMIT 5 , 10; ";
    //    List<Job> list = template.query(sql, new BeanPropertyRowMapper<Job>(Job.class));
    //    return list;
    //
    //}

    @Override
    public int findTotalCount() {
        String sql = "SELECT COUNT(*) FROM job WHERE 1 = 1;";

        Integer totalCount = template.queryForObject(sql, Integer.class);
        return totalCount;
    }

    @Override
    public List<Job> findByPage(int start, int rows) {
        String sql = "SELECT * FROM job WHERE 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);


        sb.append(" LIMIT ? , ? ;");

        sql = sb.toString();
        List<Job> list = template.query(sql, new BeanPropertyRowMapper<Job>(Job.class), start, rows);
        return list;
    }
}

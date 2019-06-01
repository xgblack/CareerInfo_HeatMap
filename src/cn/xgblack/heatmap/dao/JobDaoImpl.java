package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.util.ConditionUtils;
import cn.xgblack.heatmap.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

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



    /**
     * 查询总记录数
     * @param condition 查询的条件
     * @return totalCount总记录数
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "SELECT COUNT(*) FROM job WHERE 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List<Object> params = ConditionUtils.getPagingCondition(sb, condition);

        sb.append(" ;");
        sql = sb.toString();

        try {
            Integer totalCount = template.queryForObject(sql, Integer.class,params.toArray());
            return totalCount;
        } catch (DataAccessException e) {
            return 0 ;
        }
    }


    /**
     * 查询分页的数据
     * @param start 开始缩阴
     * @param rows 每页条数
     * @param condition 查询的条件
     * @return List<Job>
     */
    @Override
    public List<Job> findByPage(int start, int rows, Map<String, String[]>condition) {
        String sql = "SELECT * FROM job WHERE 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);


        List<Object> params = ConditionUtils.getPagingCondition(sb, condition);

        sb.append(" LIMIT ? , ? ;");
        params.add(start);
        params.add(rows);

        sql = sb.toString();

        try {
            List<Job> list = template.query(sql, new BeanPropertyRowMapper<>(Job.class),params.toArray());
            return list;
        } catch (DataAccessException e) {
            return null;
        }
    }




}

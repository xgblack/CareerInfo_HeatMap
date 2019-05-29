package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.domain.JobHeatmapData;
import cn.xgblack.heatmap.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * 直接查找所有Job数据,只包含jid,lat,lon,minwage,maxwage
     * @return List<Job>
     */
    @Override
    public List<JobHeatmapData> findAllJob() {
        //String sql = "SELECT jid,lat,lon,minwage,maxwage FROM job ;";
        String sql = "SELECT lat,lon,minwage FROM job ;";

        try {
            //查询
            List<JobHeatmapData> list = template.query(sql, new BeanPropertyRowMapper<JobHeatmapData>(JobHeatmapData.class));
            return list;
        } catch (DataAccessException e) {
            return null;
        }

    }

    /**
     * 查询总记录数
     * @param condition 查询的条件
     * @return totalCount总记录数
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "SELECT COUNT(*) FROM job WHERE 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List<Object> params = getPagingCondition(sb, condition);

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
     * @return List<User>
     */
    @Override
    public List<Job> findByPage(int start, int rows, Map<String, String[]>condition) {
        String sql = "SELECT * FROM job WHERE 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);


        List<Object> params = getPagingCondition(sb, condition);

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

    /**
     * 封装方法，获取SQL语句执行所需要的参数
     * @param sb 存储SQL语句后半部分
     * @param condition 查询条件
     * @return List<Object>SQL语句执行所需要的参数
     */
    private List<Object> getPagingCondition(StringBuilder sb,Map<String, String[]>condition){
        //定义一个集合存储SQL语句的参数
        List<Object> params = new ArrayList<>();

        //遍历map
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {
            //排除掉分页条件
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            //获取key对应的值
            String value = condition.get(key)[0];

            if ("minwage".equals(key)) {
                sb.append(" AND " + key + " >= ? ");
                params.add(value);
                continue;
            }

            if (value != null && !"".equals(value)) {
                sb.append(" AND " + key + " LIKE ? ");
                params.add("%" + value + "%");
            }
        }
        return params;
    }


}

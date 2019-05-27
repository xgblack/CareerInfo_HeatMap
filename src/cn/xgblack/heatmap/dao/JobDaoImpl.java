package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.util.JDBCUtils;
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
     * 查询总记录数
     * @param condition 查询的条件
     * @return totalCount总记录数
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "SELECT COUNT(*) FROM job WHERE 1 = 1 ";

        StringBuffer sb = new StringBuffer(sql);

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
                sb.append(" AND " + key + " > ? ");
                params.add(value);
                continue;
            }

            if (value != null && !"".equals(value)) {
                sb.append(" AND " + key + " LIKE ? ");
                params.add("%" + value + "%");
            }
        }

        sb.append(" ;");
        sql = sb.toString();

        Integer totalCount = template.queryForObject(sql, Integer.class,params.toArray());
        return totalCount;
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
                sb.append(" AND " + key + " > ? ");
                params.add(value);
                continue;
            }

            if (value != null && !"".equals(value)) {
                sb.append(" AND " + key + " LIKE ? ");
                params.add("%" + value + "%");
            }
        }

        sb.append(" LIMIT ? , ? ;");
        params.add(start);
        params.add(rows);

        sql = sb.toString();
        List<Job> list = template.query(sql, new BeanPropertyRowMapper<Job>(Job.class),params.toArray());
        return list;
    }
}

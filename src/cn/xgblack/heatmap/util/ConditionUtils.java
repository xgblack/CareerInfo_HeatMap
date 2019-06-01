package cn.xgblack.heatmap.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 小光
 * @date 2019/6/1 17:50
 * className: ConditionUtils
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class ConditionUtils {

    /**
     * 封装方法，获取SQL语句执行所需要的参数
     * @param sb 存储SQL语句后半部分
     * @param condition 查询条件
     * @return List<Object>SQL语句执行所需要的参数
     */
    public static List<Object> getPagingCondition(StringBuilder sb, Map<String, String[]> condition){
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

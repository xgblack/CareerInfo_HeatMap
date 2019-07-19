package cn.xgblack.heatmap.util;

import java.util.*;

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
     * @param condition 查询条件
     * @return List<Object>SQL语句执行所需要的参数
     */
    public static Map<String, Object> getSearchCodition(Map<String, String[]> condition){
        //定义一个map存储真正的查询条件
        Map<String, Object> pagingCodition = new HashMap<>();

        //遍历condition
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {
            //先排除掉分页条件
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }

            //获取有用的key的值
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                //将条件存入pagingCodition

                if (!"minwage".equals(key)) {
                    pagingCodition.put(key,value);
                } else {
                    int minwage = Integer.parseInt(value);
                    if (minwage > 0) {
                        pagingCodition.put(key, minwage);
                    }
                }
            }
        }
        return pagingCodition;
    }
}

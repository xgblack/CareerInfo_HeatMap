package cn.xgblack.heatmap.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author 小光
 * @date 2019/7/18 10:33
 * className: MyBatisUtils
 * description: Mybatis工具类
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class MyBatisUtils {
    private static SqlSessionFactory factory = null;
    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        } catch (IOException e) {
            System.out.println("静态初始化代码块异常错误");
            // 静态初始化代码块异常错误
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SqlSession getSqlSession() {
        return factory.openSession(true);
    }

}

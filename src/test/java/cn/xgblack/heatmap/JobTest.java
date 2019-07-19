package cn.xgblack.heatmap;

import cn.xgblack.heatmap.dao.JobDao;
import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.util.MyBatisUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小光
 * @date 2019/7/19 17:13
 * className: JobTest
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class JobTest {

    @Test
    public void testSearch(){
        JobDao dao = MyBatisUtils.getSqlSession().getMapper(JobDao.class);

        Map<String, Object> condition = new HashMap<>();
        condition.put("province", "北京");
        condition.put("minwage", 3000);

        int totalCount = dao.findTotalCount(condition);
        System.out.println(totalCount);


    }

    @Test
    public void testSearchJobs(){
        JobDao dao = MyBatisUtils.getSqlSession().getMapper(JobDao.class);

        Map<String, Object> condition = new HashMap<>();
        condition.put("province", "北京");
        condition.put("minwage", 3000);

        condition.put("start", 0);
        condition.put("rows", 15);

        List<Job> jobs = dao.findByPage(condition);
        System.out.println(jobs);

    }

}

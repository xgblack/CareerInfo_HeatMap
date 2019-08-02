package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.dto.JobHeatmapData;
import cn.xgblack.heatmap.dto.SearchCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class JobDaoTest {

    @Autowired
    private JobDao dao;

    @Test
    public void testFindTotalCount(){
        SearchCondition condition = new SearchCondition(1,1,15,null,"java","北京",3000.0);
        int totalCount = dao.findTotalCount(condition);
        System.out.println(totalCount);
    }

    @Test
    public void testFindJobPoints(){
        //SearchCondition condition = new SearchCondition();
        SearchCondition condition = new SearchCondition(1,1,15,null,"java","北京",3000.0);
        List<JobHeatmapData> points = dao.findSomePoints(condition);
        System.out.println(points);
    }
}

package cn.xgblack.heatmap.mapper;

import cn.xgblack.heatmap.HeatmapApplication;
import cn.xgblack.heatmap.dto.JobHeatmapData;
import cn.xgblack.heatmap.dto.SearchCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeatmapApplication.class)
public class JobMapperTest {

    @Autowired
    private JobMapper mapper;

    @Test
    public void testFindTotalCount(){
        SearchCondition condition = new SearchCondition(1,1,15,null,"java","北京",3000.0);
        int totalCount = mapper.findTotalCount(condition);
        System.out.println(totalCount);
    }

    @Test
    public void testFindJobPoints(){
        //SearchCondition condition = new SearchCondition();
        SearchCondition condition = new SearchCondition(1,1,15,null,"java","北京",3000.0);
        List<JobHeatmapData> points = mapper.findSomePoints(condition);
        System.out.println(points);
    }
}

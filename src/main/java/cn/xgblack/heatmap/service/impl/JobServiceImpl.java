package cn.xgblack.heatmap.service.impl;

import cn.xgblack.heatmap.dto.JobHeatmapData;
import cn.xgblack.heatmap.dto.PageBean;
import cn.xgblack.heatmap.dto.SearchCondition;
import cn.xgblack.heatmap.entity.Job;
import cn.xgblack.heatmap.mapper.JobMapper;
import cn.xgblack.heatmap.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小光
 * @date 2019/5/26 15:01
 * className: JobServiceImpl
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Service("jobService")
@Transactional(propagation = Propagation.SUPPORTS)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    /**
     *
     * @param condition 分页+查询的条件
     * @return PageBean<User>
     */
    @Override
    public PageBean<Job> findJobByPage(SearchCondition condition) {


        //当前页码
        int currentPage = condition.getCurrentPage();

        if (currentPage <= 0) {
            currentPage = 1;
        }

        //每页展示页码数
        int rows = condition.getRows();

        //调用dao查询总记录数
        int totalCount = jobMapper.findTotalCount(condition);

        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;

        if (totalCount < 1) {
            totalPage = 1;
        }

        if (currentPage > totalPage){
            currentPage = totalPage;
        }

        //计算开始的记录索引
        int start = (currentPage - 1) * rows;

        //搜索条件中增加分页条件
        condition.setStart(start);

        //调用dao查询List集合
        List<Job> jobs = jobMapper.findByPage(condition);

        //创建空的PageBean对象
        PageBean<Job> pb = new PageBean<>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        pb.setTotalCount(totalCount);
        pb.setList(jobs);
        pb.setTotalPage(totalPage);

        return pb;
    }


    /**
     * 直接查找所有Job数据,只包含jid,lat,lon,minwage,maxwage
     * @return List<Job>
     */
    @Override
    public List<JobHeatmapData> findAllJobPonits() {
        return jobMapper.findAllJobPonits();
    }

    /**
     * 根据查找条件加载热力图点
     * @param condition 查找条件
     * @return List<JobHeatmapData>
     */
    @Override
    public List<JobHeatmapData> findSomePoints(SearchCondition condition) {

        return jobMapper.findSomePoints(condition);
    }

    /**
     * 按照索引和条数查询热力图的点
     * @param startStr 开始索引
     * @param eachPointsStr 每次查询热力图点的个数
     * @return List<JobHeatmapData>
     */
    @Override
    public List<JobHeatmapData> findSomeJob(String startStr, String eachPointsStr) {
        int start = Integer.parseInt(startStr);
        int eachPoint = Integer.parseInt(eachPointsStr);

        return jobMapper.findSomeJob(start, eachPoint);
    }



}

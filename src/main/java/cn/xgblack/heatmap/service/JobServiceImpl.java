package cn.xgblack.heatmap.service;

import cn.xgblack.heatmap.dao.JobDao;
import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.domain.JobHeatmapData;
import cn.xgblack.heatmap.domain.PageBean;
import cn.xgblack.heatmap.util.ConditionUtils;
import cn.xgblack.heatmap.util.MyBatisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 小光
 * @date 2019/5/26 15:01
 * className: JobServiceImpl
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class JobServiceImpl implements JobService {
    private JobDao dao = MyBatisUtils.getSqlSession().getMapper(JobDao.class);

    /**
     *
     * @param currentPageStr 当前页码
     * @param rowsStr 每页显示条数
     * @param condition 分页+查询的条件
     * @return PageBean<User>
     */
    @Override
    public PageBean<Job> findJobByPage(String currentPageStr, String rowsStr, Map<String, String[]>condition) {

        //搜索条件map
        Map<String, Object> searchCodition = ConditionUtils.getSearchCodition(condition);

        //当前页码
        int currentPage = Integer.parseInt(currentPageStr);

        if (currentPage <= 0) {
            currentPage = 1;
        }

        //每页展示页码数
        int rows = Integer.parseInt(rowsStr);


        //调用dao查询总记录数
        int totalCount = dao.findTotalCount(searchCodition);

        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;

        if (currentPage > totalPage){
            currentPage = totalPage;
        }

        //计算开始的记录索引
        int start = (currentPage - 1) * rows;

        //新建分页条件map,先将搜索条件复制过来，再增加条件
        Map<String, Object> pageCondition = new HashMap<>();
        Set<String> keySet = searchCodition.keySet();
        for (String key : keySet) {
            pageCondition.put(key, searchCodition.get(key));
        }

        pageCondition.put("start", start);
        pageCondition.put("rows", rows);


        //调用dao查询List集合
        List<Job> jobs = dao.findByPage(pageCondition);

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
        return dao.findAllJobPonits();
    }

    /**
     * 根据查找条件加载热力图点
     * @param condition 查找条件
     * @return List<JobHeatmapData>
     */
    @Override
    public List<JobHeatmapData> findSomePoints(Map<String, String[]> condition) {
        //搜索条件map
        Map<String, Object> searchCodition = ConditionUtils.getSearchCodition(condition);

        return dao.findSomePoints(searchCodition);
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

        return dao.findSomeJob(start, eachPoint);
    }



}

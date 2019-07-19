package cn.xgblack.heatmap.service;

import cn.xgblack.heatmap.dao.JobDao;
import cn.xgblack.heatmap.domain.Job;
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
}

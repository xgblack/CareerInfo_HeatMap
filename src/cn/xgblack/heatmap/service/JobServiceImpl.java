package cn.xgblack.heatmap.service;

import cn.xgblack.heatmap.dao.JobDao;
import cn.xgblack.heatmap.dao.JobDaoImpl;
import cn.xgblack.heatmap.domain.Job;
import cn.xgblack.heatmap.domain.PageBean;

import java.util.List;
import java.util.Map;

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
    private JobDao dao = new JobDaoImpl();

    /**
     *
     * @param currentPageStr 当前页码
     * @param rowsStr 每页显示条数
     * @param condition 分页+查询的条件
     * @return PageBean<User>
     */
    @Override
    public PageBean<Job> findUserByPage(String currentPageStr, String rowsStr, Map<String, String[]>condition) {
        int currentPage = Integer.parseInt(currentPageStr);
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int rows = Integer.parseInt(rowsStr);



        //调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);

        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;

        if (currentPage > totalPage){
            currentPage = totalPage;
        }

        //创建空的PageBean对象
        PageBean<Job> pb = new PageBean<>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);


        pb.setTotalCount(totalCount);

        //计算开始的记录索引
        int start = (currentPage - 1) * rows;

        //调用dao查询List集合
        List<Job> jobs = dao.findByPage(start, rows,condition);

        pb.setList(jobs);
        pb.setTotalPage(totalPage);

        return pb;

    }
}
package cn.xgblack.heatmap.dto;

import lombok.*;

import java.util.List;

/**
 * @author 小光
 * @date 2019/5/26 16:58
 * className: PageBean
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 总页码
     */
    private Integer totalPage;
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页显示的的记录数
     */
    private Integer rows;
    /**
     * 每页的数据
     */
    private List<T> list;


}

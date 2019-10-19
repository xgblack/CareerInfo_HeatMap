package cn.xgblack.heatmap.dto;

import lombok.*;

/**
 * @author 小光
 * @date 2019/8/2 10:38
 * className: SearchCondition
 * description: 搜索条件类
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Data
@AllArgsConstructor
public class SearchCondition {
    private Integer currentPage;
    private Integer start;
    private Integer rows;
    private String cname;
    private String jname;
    private String province;
    private Double minwage;

}

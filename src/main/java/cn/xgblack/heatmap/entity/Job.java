package cn.xgblack.heatmap.entity;

import lombok.*;

/**
 * @author 小光
 * @date 2019/5/25 15:54
 * className: Job
 * description: job数据表的Bean对象
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    /**
     * 主键id
     */
    private Integer jid;
    /**
     * 公司名称
     */
    private String cname;
    /**
     * 职业名称
     */
    private String jname;
    /**
     * 经度
     */
    private double lon;
    /**
     * 纬度
     */
    private double lat;
    /**
     * 最低工资
     */
    private double minwage;
    /**
     * 最高工资
     */
    private double maxwage;
    /**
     * 公司省市位置
     */
    private String province;
    /**
     * 职业亮点
     */
    private String highlights;
    /**
     * 工作经验要求
     */
    private String erequir;
    /**
     * 公司简介
     */
    private String cintroduction;
    /**
     * 职业描述
     */
    private String jintroduction;


}

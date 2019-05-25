package cn.xgblack.heatmap.domain;

/**
 * @author 小光
 * @date 2019/5/25 15:54
 * className: Job
 * description: job数据表的Bean对象
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class Job {
    /**
     * 主键id
     */
    private int jid;
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

    public Job() {
    }

    public Job(int jid, String cname, String jname, double lon, double lat, double minwage, double maxwage, String province, String highlights, String erequir, String cintroduction, String jintroduction) {
        this.jid = jid;
        this.cname = cname;
        this.jname = jname;
        this.lon = lon;
        this.lat = lat;
        this.minwage = minwage;
        this.maxwage = maxwage;
        this.province = province;
        this.highlights = highlights;
        this.erequir = erequir;
        this.cintroduction = cintroduction;
        this.jintroduction = jintroduction;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getMinwage() {
        return minwage;
    }

    public void setMinwage(double minwage) {
        this.minwage = minwage;
    }

    public double getMaxwage() {
        return maxwage;
    }

    public void setMaxwage(double maxwage) {
        this.maxwage = maxwage;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getErequir() {
        return erequir;
    }

    public void setErequir(String erequir) {
        this.erequir = erequir;
    }

    public String getCintroduction() {
        return cintroduction;
    }

    public void setCintroduction(String cintroduction) {
        this.cintroduction = cintroduction;
    }

    public String getJintroduction() {
        return jintroduction;
    }

    public void setJintroduction(String jintroduction) {
        this.jintroduction = jintroduction;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jid=" + jid +
                ", cname='" + cname + '\'' +
                ", jname='" + jname + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", minwage=" + minwage +
                ", maxwage=" + maxwage +
                ", province='" + province + '\'' +
                ", highlights='" + highlights + '\'' +
                ", erequir='" + erequir + '\'' +
                ", cintroduction='" + cintroduction + '\'' +
                ", jintroduction='" + jintroduction + '\'' +
                '}';
    }
}

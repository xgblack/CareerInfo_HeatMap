package cn.xgblack.heatmap.domain;

/**
 * @author 小光
 * @date 2019/5/29 20:24
 * className: JobHeatmapData
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class JobHeatmapData {
    private double lat;
    private double lon;
    private double minwage;

    public JobHeatmapData() {
    }

    public JobHeatmapData(double lat, double lon, double minwage) {
        this.lat = lat;
        this.lon = lon;
        this.minwage = minwage;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getMinwage() {
        return minwage;
    }

    public void setMinwage(double minwage) {
        this.minwage = minwage;
    }
}

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
    private double lng;
    private double count;

    public JobHeatmapData() {
    }

    public JobHeatmapData(double lat, double lng, double count) {
        this.lat = lat;
        this.lng = lng;
        this.count = count;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}

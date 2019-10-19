package cn.xgblack.heatmap.dto;

import lombok.*;

/**
 * @author 小光
 * @date 2019/5/29 20:24
 * className: JobHeatmapData
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHeatmapData {
    private Double lat;
    private Double lng;
    private Double count;
}

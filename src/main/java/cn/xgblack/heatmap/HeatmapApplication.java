package cn.xgblack.heatmap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.xgblack.heatmap.mapper")
public class HeatmapApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeatmapApplication.class, args);
    }

}

package cn.xgblack.heatmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class CareerInfoHeatMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerInfoHeatMapApplication.class, args);
	}

}

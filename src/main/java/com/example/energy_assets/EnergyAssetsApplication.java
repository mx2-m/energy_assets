package com.example.energy_assets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// FOR TESTING
//http://localhost:8080/swagger-ui.html#/energy45asset45controller
@EnableSwagger2
@SpringBootApplication
@EnableScheduling
public class EnergyAssetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergyAssetsApplication.class, args);
    }

}

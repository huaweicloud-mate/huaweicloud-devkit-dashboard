package com.huaweicloud.devkit.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DevkitPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevkitPortalApplication.class, args);
    }
}

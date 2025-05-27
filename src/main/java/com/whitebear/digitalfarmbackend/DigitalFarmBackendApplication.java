package com.whitebear.digitalfarmbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whitebear.digitalfarmbackend.mapper")
public class DigitalFarmBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalFarmBackendApplication.class, args);
    }

}

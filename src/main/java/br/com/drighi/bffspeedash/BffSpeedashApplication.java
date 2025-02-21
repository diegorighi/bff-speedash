package br.com.drighi.bffspeedash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "br.com.drighi.bffspeedash.infrastructure.adapters.out.http")
public class BffSpeedashApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffSpeedashApplication.class, args);
    }

}

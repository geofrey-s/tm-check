package edu.mum.tmcheck;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TmCheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmCheckApplication.class, args);
    }

}

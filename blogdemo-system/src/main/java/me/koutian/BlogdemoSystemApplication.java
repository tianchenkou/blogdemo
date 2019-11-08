package me.koutian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("me.koutian.mapper")
@SpringBootApplication
public class BlogdemoSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogdemoSystemApplication.class, args);
    }

}

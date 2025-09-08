package com.lianji;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目启动类
 */
@SpringBootApplication
@MapperScan("com.lianji.mapper")
//@MapperScan("com.lianji.mapper") // 指定 Mapper 接口所在的包路径
public class LianjiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LianjiApplication.class, args);
    }

}
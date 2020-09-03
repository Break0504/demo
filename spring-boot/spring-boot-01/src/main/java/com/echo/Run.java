package com.echo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 9:10 2020/8/31
 *
 * @SpringBootApplication spring boot程序启动类
 * @ImportResource 导入spring配置文件,让配置文件内容生效
 */
@SpringBootApplication
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

}

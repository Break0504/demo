package com.echo.alipay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 14:04 2020/7/31
 * @description:
 */
@Configuration
@EnableWebMvc //表示这个类是SpringMVC的配置文件
@ComponentScan("com.echo.alipay")//注意扫描的包
public class MvcConfig implements WebMvcConfigurer {

    //配置视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }



}

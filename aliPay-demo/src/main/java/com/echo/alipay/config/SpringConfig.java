package com.echo.alipay.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;


/**
 * @author: Created by zhangsf
 * @datetime: Created in 14:05 2020/7/31
 * @description:
 */
@Configuration
@ComponentScan(value = "com.echo.alipay",excludeFilters = {@Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})
public class SpringConfig {



}

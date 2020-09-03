package com.echo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 9:22 2020/8/31
 * @description:
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("sayHello")
    public String sayHello() {
        return "Hello world!";
    }

}

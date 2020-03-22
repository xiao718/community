package com.community.xiao.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;


@Controller
public class testControl {
    @RequestMapping("/test")
    public String index() {
        return "index";
    }
}

package com.community.xiao.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PubulishController {
    @GetMapping("publish")
    public String publish(){
        return "publish";
    }
}

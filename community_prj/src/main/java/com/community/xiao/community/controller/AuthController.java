package com.community.xiao.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    public String CallBack(@RequestParam(name = "code") String code){

        return null;
    }
}

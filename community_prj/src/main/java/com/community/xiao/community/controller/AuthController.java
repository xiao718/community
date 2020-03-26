package com.community.xiao.community.controller;

import com.alibaba.fastjson.JSON;
import com.community.xiao.community.domain.User;
import com.community.xiao.community.dto.AccessTokenDto;
import com.community.xiao.community.dto.GiteeUser;
import com.community.xiao.community.dto.RebackGitee;
import com.community.xiao.community.mapper.UserMapper;
import com.community.xiao.community.provide.GiteeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    GiteeProvider giteeProvider;
    @Value("${gitee.client_id}")
    private String clientId;
    @Value("${gitee.client_secret}")
    private String clientSecret;
    @Value("${gitee.redirect_uri}")
    private String redirectUri;
    @Value("${gitee.grant_type}")

    private String grantType;

    @RequestMapping("callback")
    public String CallBack(@RequestParam(name = "code") String code,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setGrant_type(grantType);
        accessTokenDto.setCode(code);
        String giteeReback = giteeProvider.getAccessToken(accessTokenDto);
        RebackGitee rebackGitee = JSON.parseObject(giteeReback, RebackGitee.class);
        GiteeUser gituser = giteeProvider.getUser(rebackGitee.getAccess_token());

        if(gituser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gituser.getName());
            user.setAccount(String.valueOf(gituser.getId()));
            user.setGmt_created(new Timestamp(new Date().getTime()));
            user.setGmt_updated(new Timestamp(new Date().getTime()));
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
//            request.getSession().setAttribute("user",gituser);
//           System.out.println(user.getName());
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }



    }

}

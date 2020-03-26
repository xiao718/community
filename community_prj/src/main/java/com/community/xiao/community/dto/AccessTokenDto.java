package com.community.xiao.community.dto;

import lombok.Data;

@Data
public class AccessTokenDto {
    private String code;
    private String client_id;
    private String redirect_uri;
    private  String client_secret;
    private  String grant_type;
}

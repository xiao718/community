package com.community.xiao.community.dto;

import lombok.Data;

@Data
public class RebackGitee {
    private String access_token;
    private String token_type;
    private String expires_in;
    private String refresh_token;
    private String scope;
    private String created_at;
}

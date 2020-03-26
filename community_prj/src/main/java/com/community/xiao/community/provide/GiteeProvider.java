package com.community.xiao.community.provide;

import com.alibaba.fastjson.JSON;
import com.community.xiao.community.dto.AccessTokenDto;
import com.community.xiao.community.dto.GiteeUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GiteeProvider {

  public String  getAccessToken(AccessTokenDto accessTokenDto) {
      MediaType mediaType = MediaType.get("application/json; charset=utf-8");
      OkHttpClient client = new OkHttpClient();
      String s = JSON.toJSONString(accessTokenDto);
      RequestBody body = RequestBody.create(mediaType,s);
          Request request = new Request.Builder()
                  .url("https://gitee.com/oauth/token")
                  .post(body)
                  .build();
          try {
              Response response = client.newCall(request).execute();
              return response.body().string();
          } catch (IOException e) {
              e.printStackTrace();
          }
      return null;
  }

  public GiteeUser getUser(String accessToken){
      OkHttpClient client = new OkHttpClient();


          Request request = new Request.Builder()
                  .url("https://gitee.com/api/v5/user?access_token="+accessToken)
                  .build();

          try {
              Response response = client.newCall(request).execute();
              String string = response.body().string();
              GiteeUser giteeUser = JSON.parseObject(string, GiteeUser.class);
              return giteeUser;

          } catch (IOException e) {
              e.printStackTrace();
          }
        return null;
  }
}


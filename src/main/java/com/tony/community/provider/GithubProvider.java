package com.tony.community.provider;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.tony.community.domain.vo.AccessToken;
import com.tony.community.domain.vo.GithubUser;
import org.springframework.stereotype.Component;

/**
 * github登录接口
 */

@Component
public class GithubProvider {

    /**
     * 获取token
     */
    public String getAccessToken(AccessToken accessToken) {
        String result = null;

        String accessTokenStr = JSON.toJSONString(accessToken);
        HttpResponse response = HttpUtil.createPost("https://github.com/login/oauth/access_token")
                .contentType("application/json; charset=utf-8")
                .body(accessTokenStr)
                .execute();

        String body = response.body();

        if (ObjectUtil.isNotEmpty(body)) {
            result = body.split("&")[0].split("=")[1];
        }

        return result;
    }

    /**
     * 获取用户信息
     */
    public GithubUser getUser(String accessToken) {
        HttpResponse response = HttpUtil.createGet("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
                .execute();

        String body = response.body();

        if (ObjectUtil.isNotEmpty(body)) {
            return JSON.parseObject(body, GithubUser.class);
        }

        return null;
    }
}

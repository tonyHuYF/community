package com.tony.community.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tony.community.domain.User;
import com.tony.community.domain.vo.AccessToken;
import com.tony.community.domain.vo.GithubUser;
import com.tony.community.provider.GithubProvider;
import com.tony.community.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录验证
 */

@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Resource
    private GithubProvider githubProvider;

    @Resource
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {

        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirectUri);
        accessToken.setState(state);
        String accessTokenStr = githubProvider.getAccessToken(accessToken);
        GithubUser user = githubProvider.getUser(accessTokenStr);

        if (ObjectUtil.isNotEmpty(user)) {
            //登录成功，把user塞进session
            request.getSession().setAttribute("user", user);

            //插入user到user表
            User userData = new User();
            userData.setAccountId(user.getId());
            userData.setName(user.getName());
            userData.setToken(accessTokenStr);
            userData.setBio(user.getBio());
            userData.setAvatarUrl(user.getAvatarUrl());

            userService.insert(userData);

        } else {
            //登录失败，重新登录
        }

        return "redirect:/";
    }
}

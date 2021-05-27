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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,
                           HttpServletResponse response) {

        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirectUri);
        accessToken.setState(state);
        String accessTokenStr = githubProvider.getAccessToken(accessToken);
        GithubUser user = githubProvider.getUser(accessTokenStr);

        if (ObjectUtil.isNotEmpty(user)) {
            //先删除旧数据
            userService.delete(user.getId());

            //插入user到user表
            String token = UUID.randomUUID().toString();

            User userData = new User();
            userData.setAccountId(user.getId());
            userData.setName(user.getName());
            userData.setToken(token);
            userData.setBio(user.getBio());
            userData.setAvatarUrl(user.getAvatarUrl());

            userService.insert(userData);

            //将token插入到cookie中
            response.addCookie(new Cookie("token", token));

        } else {
            //登录失败，重新登录
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}

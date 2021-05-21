package com.tony.community.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tony.community.domain.User;
import com.tony.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录首页
 */

@Controller
public class IndexController {
    @Resource
    private UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (ObjectUtil.isNotEmpty(cookies)) {
            for (Cookie data : cookies) {
                if (StrUtil.equals(data.getName(), "token")) {
                    String token = data.getValue();
                    User user = userService.queryByToken(token);
                    if (ObjectUtil.isNotEmpty(user)) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}

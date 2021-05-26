package com.tony.community.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.community.domain.User;
import com.tony.community.domain.vo.PaginationVo;
import com.tony.community.domain.vo.QuestionVo;
import com.tony.community.service.QuestionService;
import com.tony.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Resource
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {
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
        PaginationVo<QuestionVo> pagination = questionService.list(new Page<>(page, size));
        model.addAttribute("pagination", pagination);

        return "index";
    }
}

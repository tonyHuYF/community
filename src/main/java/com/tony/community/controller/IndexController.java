package com.tony.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.community.domain.vo.PaginationVo;
import com.tony.community.domain.vo.QuestionVo;
import com.tony.community.service.QuestionService;
import com.tony.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationVo<QuestionVo> pagination = questionService.list(new Page<>(page, size));
        model.addAttribute("pagination", pagination);

        return "index";
    }
}

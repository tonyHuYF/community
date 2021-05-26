package com.tony.community.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.community.domain.User;
import com.tony.community.domain.vo.PaginationVo;
import com.tony.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Resource
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profie(HttpServletRequest request,
                         @PathVariable(name = "action") String action,
                         Model model,
                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                         @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            return "redirect:/";
        }

        String section = null;
        String sectionName = null;
        PaginationVo paginationVo = null;

        switch (action) {
            case "question":
                section = "question";
                sectionName = "我的提问";
                paginationVo = questionService.listByUser(new Page<>(page, size), user);
                break;
            case "replies":
                section = "replies";
                sectionName = "最新回复";
                break;
            default:
                break;
        }

        model.addAttribute("section", section);
        model.addAttribute("sectionName", sectionName);
        model.addAttribute("pagination", paginationVo);

        return "profile";
    }
}

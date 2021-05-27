package com.tony.community.controller;

import com.tony.community.domain.vo.QuestionVo;
import com.tony.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id, Model model) {
        QuestionVo questionVo = questionService.queryById(id);
        model.addAttribute("question", questionVo);
        return "question";
    }
}

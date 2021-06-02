package com.tony.community.controller;

import com.tony.community.domain.vo.CommentVo;
import com.tony.community.domain.vo.QuestionVo;
import com.tony.community.service.CommentService;
import com.tony.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class QuestionController {
    @Resource
    private QuestionService questionService;
    @Resource
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id, Model model) {
        QuestionVo questionVo = questionService.queryById(id);
        List<CommentVo> commentVos = commentService.queryByParentId(questionVo.getId());
        model.addAttribute("question", questionVo);
        model.addAttribute("comments",commentVos);

        //累加阅读数
        questionService.updateInc(id);
        return "question";
    }
}

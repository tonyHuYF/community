package com.tony.community.controller;

import cn.hutool.core.bean.BeanUtil;
import com.tony.community.domain.Comment;
import com.tony.community.domain.vo.CommentVo;
import com.tony.community.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody CommentVo commentVo) {
        Comment comment = new Comment();
        BeanUtil.copyProperties(commentVo, comment);
        commentService.insert(comment);
        return null;
    }
}

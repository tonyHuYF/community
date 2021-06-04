package com.tony.community.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.tony.community.domain.Comment;
import com.tony.community.domain.User;
import com.tony.community.domain.vo.CommentVo;
import com.tony.community.domain.vo.ResultBean;
import com.tony.community.enums.CommentTypeEnum;
import com.tony.community.exception.CustomizeErrorCode;
import com.tony.community.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody CommentVo commentVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultBean.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentVo == null || StrUtil.isBlank(commentVo.getContent())) {
            return ResultBean.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        BeanUtil.copyProperties(commentVo, comment);
        comment.setCommentator(user.getAccountId());
        commentService.insert(comment);
        return ResultBean.okOf();
    }

    @GetMapping("/comment/{id}")
    @ResponseBody
    public ResultBean<List<CommentVo>> comments(@PathVariable("id") String id) {
        List<CommentVo> commentVos = commentService.queryByParentId(id, CommentTypeEnum.COMMENT);
        return ResultBean.okOf(commentVos);

    }
}

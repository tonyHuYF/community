package com.tony.community.service;

import com.tony.community.domain.Comment;
import com.tony.community.mapper.CommentMapper;
import com.tony.community.mapper.QuestionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private QuestionMapper questionMapper;

    @Transactional
    public Comment insert(Comment comment) {
        commentMapper.insert(comment);
        //回复加一
        questionMapper.updateCommentCount(comment.getParentId());
        return comment;
    }
}

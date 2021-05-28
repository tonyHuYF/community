package com.tony.community.service;

import com.tony.community.domain.Comment;
import com.tony.community.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;

    public Comment insert(Comment comment) {
        commentMapper.insert(comment);
        return comment;
    }
}

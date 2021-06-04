package com.tony.community.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tony.community.domain.Comment;
import com.tony.community.domain.User;
import com.tony.community.domain.vo.CommentVo;
import com.tony.community.enums.CommentTypeEnum;
import com.tony.community.mapper.CommentMapper;
import com.tony.community.mapper.QuestionMapper;
import com.tony.community.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private UserMapper userMapper;

    @Transactional
    public Comment insert(Comment comment) {
        commentMapper.insert(comment);

        if (comment.getType() == 1) {
            //回复加一
            questionMapper.updateCommentCount(comment.getParentId());
        } else {
            //二级评论回复加一
            commentMapper.updateCommentCount(comment.getParentId());
        }

        return comment;
    }

    public List<CommentVo> queryByParentId(String id, CommentTypeEnum type) {
        List<CommentVo> result = new ArrayList<>();

        QueryWrapper<Comment> commentWrapper = new QueryWrapper<>();
        commentWrapper.lambda().eq(Comment::getParentId, id);
        commentWrapper.lambda().eq(Comment::getType, type.getType());
        commentWrapper.lambda().orderByDesc(Comment::getCreateTime);
        List<Comment> comments = commentMapper.selectList(commentWrapper);

        List<User> users = userMapper.selectList(new QueryWrapper<>());
        Map<String, User> userMaps = users.stream().collect(Collectors.toMap(k -> k.getAccountId(), v -> v));

        comments.forEach(p -> {
            CommentVo temp = new CommentVo();
            BeanUtil.copyProperties(p, temp);
            temp.setUser(userMaps.get(p.getCommentator()));
            result.add(temp);
        });

        return result;
    }

}

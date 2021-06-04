package com.tony.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.community.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 累加回复数
     */
    void updateCommentCount(@Param("id")String id);
}

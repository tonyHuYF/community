package com.tony.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.community.domain.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}

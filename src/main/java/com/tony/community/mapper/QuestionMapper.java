package com.tony.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tony.community.domain.Question;
import com.tony.community.domain.vo.QuestionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 分页查询
     */
    IPage<QuestionVo> queryList(IPage<Question> page);

    /**
     * 分页查询--根据用户id
     */
    IPage<QuestionVo> queryListByUser(IPage<Question> page, @Param("user") String user);

}

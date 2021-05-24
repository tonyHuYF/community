package com.tony.community.service;

import com.tony.community.domain.Question;
import com.tony.community.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    /**
     * 新增
     */
    public Question insert(Question question) {
        questionMapper.insert(question);
        return question;
    }
}

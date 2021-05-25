package com.tony.community.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tony.community.domain.Question;
import com.tony.community.domain.User;
import com.tony.community.domain.vo.QuestionVo;
import com.tony.community.mapper.QuestionMapper;
import com.tony.community.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增
     */
    public Question insert(Question question) {
        questionMapper.insert(question);
        return question;
    }

    /**
     * 列表查询
     */
    public List<QuestionVo> list() {
        List<QuestionVo> result = new ArrayList<>();
        QueryWrapper<Question> questionWrapper = new QueryWrapper<>();
        questionWrapper.lambda().orderByDesc(Question::getCreateTime);
        List<Question> questions = questionMapper.selectList(questionWrapper);

        List<User> users = userMapper.selectList(new QueryWrapper<>());
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(k -> k.getAccountId(), v -> v));

        questions.forEach(p -> {
            QuestionVo temp = new QuestionVo();
            BeanUtil.copyProperties(p, temp);
            temp.setUser(userMap.get(p.getCreator()));
            result.add(temp);
        });

        return result;
    }
}

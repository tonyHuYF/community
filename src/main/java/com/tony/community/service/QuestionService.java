package com.tony.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tony.community.domain.Question;
import com.tony.community.domain.User;
import com.tony.community.domain.vo.PaginationVo;
import com.tony.community.domain.vo.QuestionVo;
import com.tony.community.mapper.QuestionMapper;
import com.tony.community.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * 列表查询--分页
     */
    public PaginationVo<QuestionVo> list(IPage<Question> page) {
        IPage<QuestionVo> questionPage = questionMapper.queryList(page);

        List<User> users = userMapper.selectList(new QueryWrapper<>());
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(k -> k.getAccountId(), v -> v));

        questionPage.getRecords().forEach(p -> p.setUser(userMap.get(p.getCreator())));

        Integer total = (int) questionPage.getTotal();

        Integer totalPage = total % (int) page.getSize() == 0 ? total / (int) page.getSize() : total / (int) page.getSize() + 1;

        PaginationVo<QuestionVo> result = new PaginationVo();
        result.setPagination(totalPage, (int) page.getCurrent());
        result.setData(questionPage.getRecords());

        return result;
    }
}

package com.tony.community.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
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
     * 更新
     */
    public Question update(Question question) {
        question.setUpdateTime(DateUtil.date());
        questionMapper.updateById(question);
        return question;
    }

    /**
     * 列表查询--分页
     */
    public PaginationVo<QuestionVo> list(IPage<Question> page) {
        IPage<QuestionVo> questionPage = questionMapper.queryList(page);

        List<User> users = userMapper.selectList(new QueryWrapper<>());
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getAccountId, v -> v));

        questionPage.getRecords().forEach(p -> p.setUser(userMap.get(p.getCreator())));

        int total = (int) questionPage.getTotal();

        Integer totalPage = total % (int) page.getSize() == 0 ? total / (int) page.getSize() : total / (int) page.getSize() + 1;

        PaginationVo<QuestionVo> result = new PaginationVo<>();
        result.setPagination(totalPage, (int) page.getCurrent());
        result.setData(questionPage.getRecords());

        return result;
    }


    /**
     * 根据发布者列表查询--分页
     */
    public PaginationVo<QuestionVo> listByUser(IPage<Question> page, User user) {
        IPage<QuestionVo> questionPage = questionMapper.queryListByUser(page, user.getAccountId());

        List<User> users = userMapper.selectList(new QueryWrapper<>());
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getAccountId, v -> v));

        questionPage.getRecords().forEach(p -> p.setUser(userMap.get(p.getCreator())));

        int total = (int) questionPage.getTotal();

        Integer totalPage = total % (int) page.getSize() == 0 ? total / (int) page.getSize() : total / (int) page.getSize() + 1;

        PaginationVo<QuestionVo> result = new PaginationVo<>();
        result.setPagination(totalPage, (int) page.getCurrent());
        result.setData(questionPage.getRecords());

        return result;
    }

    /**
     * 根据id查询
     */
    public QuestionVo queryById(String id) {
        QuestionVo result = new QuestionVo();
        Question question = questionMapper.selectById(id);
        BeanUtil.copyProperties(question, result);
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getAccountId, v -> v));
        result.setUser(userMap.get(result.getCreator()));
        return result;
    }

    /**
     * 更新或者插入
     */
    public Question createOrUpdate(Question question) {
        if (ObjectUtil.isNotEmpty(question.getId())) {
            update(question);
        } else {
            insert(question);
        }
        return question;
    }


}

package com.tony.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.community.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

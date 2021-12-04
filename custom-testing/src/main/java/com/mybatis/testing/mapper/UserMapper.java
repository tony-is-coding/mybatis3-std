package com.mybatis.testing.mapper;

import com.mybatis.testing.entity.User;

public interface UserMapper {
    User queryById(Integer userId);

    Integer insert(User user);


}

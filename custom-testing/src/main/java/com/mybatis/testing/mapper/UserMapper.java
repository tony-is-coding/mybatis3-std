package com.mybatis.testing.mapper;

import com.mybatis.testing.entity.User;

import java.util.List;

public interface UserMapper {
    User queryById(Integer userId);

    // 使用$作为占位符
    User queryByName();

    // 使用 # 作为占位符
    User queryByName1();

    Integer insert(User user);

    Integer batchInsert(List<User> users);


}

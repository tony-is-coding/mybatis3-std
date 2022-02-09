package com.mybatis.testing.mapper;

import com.mybatis.testing.dto.UserQueryDTO;
import com.mybatis.testing.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User queryById(Integer userId);

    // 使用$作为占位符
    User queryByName(@Param("userName") String userName, @Param("addr") String addr);

    // 使用 # 作为占位符
    User queryByName1(@Param("userName") String userName, @Param("addr") String addr);

    User queryByDTO(UserQueryDTO dto);

    List<User> queryTestInCause(@Param("nameList") List<String> nameList);

    Integer insert(User user);

    Integer batchInsert(List<User> users);


}

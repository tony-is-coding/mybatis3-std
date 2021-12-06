package com.mybatis.testing.tests;


import com.mybatis.testing.entity.User;
import com.mybatis.testing.helpers.LoggerHelper;
import com.mybatis.testing.helpers.RandomHelper;
import com.mybatis.testing.helpers.XMLBasedTestingHelper;
import com.mybatis.testing.mapper.UserMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;

// insert && update && delete
public class TestBasicSqlCUD {

    @Test
    public void testInsertOneSuccess() {
        XMLBasedTestingHelper helper = new XMLBasedTestingHelper();
        SqlSession session = helper.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = RandomHelper.randomUser();
        Configuration configuration = session.getConfiguration();
        Integer userId = mapper.insert(user);
        user.setId(userId);

        System.out.println(user);

    }

    @Test
    public void testInsertBatchSuccess() {
        LoggerHelper.log("this is a helper test");
    }

}

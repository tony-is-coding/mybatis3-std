package com.mybatis.testing.tests;


import com.mybatis.testing.entity.User;
import com.mybatis.testing.helpers.RandomHelper;
import com.mybatis.testing.helpers.XMLBasedTestingHelper;
import com.mybatis.testing.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;


// insert && update && delete
public class TestBasicSqlCUD {

    @Test
    public void testInsertOneSuccess() {
        XMLBasedTestingHelper helper = new XMLBasedTestingHelper();
        try (SqlSession session = helper.autoCommittedSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = RandomHelper.randomUser();
            Integer effectRows = mapper.insert(user);
            assert effectRows != 0;
            System.out.println(effectRows);
            System.out.println("最新写入数据主键为: " + user.getId());
        }
    }

    @Test
    public void testInsertBatchSuccess() {
        XMLBasedTestingHelper helper = new XMLBasedTestingHelper();
        SqlSession session = helper.autoCommittedSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = new LinkedList<>();
        users.add(RandomHelper.randomUser());
        users.add(RandomHelper.randomUser());
        users.add(RandomHelper.randomUser());
        users.add(RandomHelper.randomUser());
        Integer effectRows = mapper.batchInsert(users);
        assert effectRows != 0;
        System.out.println(effectRows);
        System.out.println(users);
        session.commit();
        session.close();
    }

}

package com.mybatis.testing.tests;

import com.mybatis.testing.helpers.XMLBasedTestingHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;


public class TestTestingHelper {

    @Test
    public void testHelperValid() {
        XMLBasedTestingHelper helper = new XMLBasedTestingHelper();

        SqlSessionFactory sqlSessionFactory = helper.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();

        assert session != null;

        System.out.println(session);
    }
}

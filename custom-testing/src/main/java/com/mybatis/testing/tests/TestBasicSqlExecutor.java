package com.mybatis.testing.tests;

import com.mybatis.testing.entity.Account;
import com.mybatis.testing.helpers.XMLBasedTestingHelper;
import com.mybatis.testing.mapper.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestBasicSqlExecutor {

    @Test
    public void testQueryAccountSuccess() {
        XMLBasedTestingHelper helper = new XMLBasedTestingHelper();

        SqlSession session = helper.openSession();
        AccountMapper mapper = session.getMapper(AccountMapper.class); // 获取被代理的 mapper
        // 此时方法执行会进入到 MapperProxy.invoke方法内去, 再交由sqlsession 去
        Account account = mapper.findByAccountId(1);
        assert account != null;
        System.out.println(account);
    }
}

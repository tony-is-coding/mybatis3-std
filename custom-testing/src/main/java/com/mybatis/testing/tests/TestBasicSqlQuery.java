package com.mybatis.testing.tests;

import com.mybatis.testing.entity.Account;
import com.mybatis.testing.entity.User;
import com.mybatis.testing.helpers.XMLBasedTestingHelper;
import com.mybatis.testing.mapper.AccountMapper;
import com.mybatis.testing.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestBasicSqlQuery {
    // 执行顺序:
    //1. MapperProxy.invoke -> MapperMethodInvoker.invoke  -> mapperMethod.execute
    //2.sqlSession.selectOne -> selectList.get(0) ->
    // 2.1 获取mappedStatement, configuration.getMappedStatement 这里是关键, 这里会涉及到 mybatis内部如何通过代理方法获取到具体的xml中的sql以及执行配置的逻辑
    // 2.2 executor.query(mappedStatement), executor 就分化出 缓存与基础的 executor
    //  2.2.1 BaseExecutor 属于典型的模板方法模式, 下分出: batch,reuse, simple, closed 四类 执行器
    //  2.2.2 CachingExecutor 则是指缓存执行器
    //  2.2.3  mybatis中有所谓的一级缓存,二级缓存说法;
    //         一级缓存默认开启, 在 BaseExecutor的query中具体实现, 由PerpetualCache实现,一级缓存是 sqlSession绑定的, 在openSession
    //              创建session时会船舰对应的 executor, 每个executor持有单独的一个 本地cache
    //              在BaseExecutor的构造方法中默认初始了一个 PerpetualCache 实例
    //         二级缓存默认关闭, 在CachingExecutor中实现, 为了解决脏读问题, 分化出了 全局缓存Cache 与事务缓存 TransactionCache,
    //              Transaction是基于装饰模式对Cache接口实现包装以达到对事务支持的功能;
    //              二级缓存是 NameSpace级别共享的, 由全局的上下文Configuration对象持有的MappedStatement来持有 默认实现是 LruCache
    //              由configuration.addMapper -> MapperBuilderAssistant.useNewCache 创建


    @Test
    public void testQueryOneSuccess() {
        XMLBasedTestingHelper helper = new XMLBasedTestingHelper();

        SqlSession session = helper.openSession();
//        AccountMapper mapper = session.getMapper(AccountMapper.class); // 获取被代理的 mapper
//        // 此时方法执行会进入到 MapperProxy.invoke方法内去, 再交由sqlSession 去进行具体的执行代理
//        Account account = mapper.findByAccountId(1);
//        assert account != null;
//        System.out.println(account);
        UserMapper mapper = session.getMapper(UserMapper.class);
        String userName = "nkvMP";
        String addr = "ZYupZ";
        System.out.println("\n\n========================================== 开始动态sql $ 模式查询 ================================\n");
        User user = mapper.queryByName(userName, addr);
        System.out.println(user);

        System.out.println("\n\n========================================== 开始动态sql # 模式查询 ================================\n");
        User user1 = mapper.queryByName1(userName, addr);
        System.out.println(user1);
        session.close();
    }


    @Test
    public void testQueryBatchSuccess() {

    }

}

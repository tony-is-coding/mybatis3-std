package com.mybatis.testing.helpers;

import java.io.IOException;
import java.io.InputStream;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class XMLBasedTestingHelper {
    private final String resourcePath;

    public XMLBasedTestingHelper(String resourcePath) {
        this.resourcePath = resourcePath;
        init();
    }

    public XMLBasedTestingHelper() {
        this.resourcePath = "mapper-config.xml";
        init();
    }

    private SqlSessionFactory sqlSessionFactory;

    private void init() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(this.resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); // 创建一个 SqlSessionFactory
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return this.sqlSessionFactory;
    }

    public SqlSession openSession() {
        return this.sqlSessionFactory.openSession();
    }

}

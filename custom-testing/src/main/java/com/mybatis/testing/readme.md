## Mybatis 总结

### 一、SqlSession 与 SqlSessionFactory
1. SqlSession的生命周期，必须严格限制在方法内部或者request范围（也称之为Thread范围），线程不安全，线程之间不能共享
ps: 好像和 executor 有关， 具体又涉及到的 executor 上绑定的缓存问题

2. SqlSessionFactory 就是创建 sqlSession 的工厂

### 二、映射器 Mapper 加载与查找


### 三、Executor 

### 四、Plugins

### 五、动态SQL

### 

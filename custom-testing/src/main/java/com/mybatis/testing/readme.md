## Mybatis 总结

### 一、SqlSession 与 SqlSessionFactory

1. SqlSession的生命周期，必须严格限制在方法内部或者request范围（也称之为Thread范围），线程不安全，线程之间不能共享 ps: 好像和 executor 有关， 具体又涉及到的 executor 上绑定的缓存问题

2. SqlSessionFactory 就是创建 sqlSession 的工厂

### 二、映射器 Mapper 加载与查找

Mybatis 支持
##### 1. 基于 XML 配置的mapper加载

在XML中支持以下形式的mapper资源指定, 通过XML配置文件中的 `<mappers>` 下 `<mapper>` 标签指定
- 本地resource指定 
- url resource指定 
- interface指定 
- package指定


##### 2. 基于 Annotation 注解模式的mapper加载

最终会基于 `package.mapper.method` 形成唯一的全限定名称, 在XML中就是具体的 namespace +子节点id 组合成的全限定名, 二者没有区别
以这个全限定名为 key, 将每个mapper-method打包成 MapperMethod 作为值, 存储在 configuration.mappedStatement 映射中

1. 创建时会对mapperMethod 进行动态代理设置, 代理执行在 MapperProxy

> tip: 需要注意,因为没有考虑到 方法的签名 等因素, 只考虑到方法名称, 所以 mapper 内的方法是不支持重载操作的

### 三、Executor
#### 1. BaseExecutor
#### 2. SimpleExecutor
#### 3. BatchExecutor
#### 4. CachingExecutor
#### 5. ReuseExecutor


### 四、缓存原理: 一级缓存、二级缓存
#### 一级缓存
1. 默认开启

2. 一级缓存是 sqlSession 绑定; 每次创建一个新的 sqlSession, 都会基于对应的策略, 创建一个 `Executor` 对象
以及缓存就是附加在所有`Executor` 的最终委托 `BaseExecutor` 上; 
3. 一级缓存有缓存作用域,默认是 `LocalCacheScope.SESSION`即会话级别,同时支持到 Statement级别 

4.  

#### 二级缓存




如何解决缓存脏读问题？  
```java
/**
    前提:
    delegate 缓存时全局共享的, 因为时从Configuration中的MappedStatement下获取的;
    场景:Executor
    1. A 从 DB 查询 A1
    2. A 更新了 A1 -> A2 并写入 cache
    3. B 从 cache 读取 A2
    4. A 回滚了事务, 将 A2 -> A1
    5. B 此时持有了 A2 但是 缓存和 DB 都是A1 导致了脏数据
    解决:
    在 cache 之前引入额外一层的 事务缓存, 在事务commit 之前,不将 事务缓存同步到全局 缓存
*/
```


### 五、Plugins
正向思维
1. Mybatis 会对所有的 `Executor`、`ParameterHandler`、`ResultSetHandler`、`StatementHandler` 等接口的所有方法进行代理执行, 通过JDK 动态代理实现Plugin的切面接入
2. 在执行以上被代理的接口的子类方法时, 会进行Plugin代理判断， 基于Plugin 的 Signature 签名来过滤是否需要执行插件拦截, 一个Plugin 可以应用到多个 Signature 上
3. 插件 Plugin 可以指定拦截哪些 接口, 具体方法, 并基于方法参数来 过滤方法的重载

逆向思维：
1. 拦截谁(目标)？
2. 何时拦截(运行时)？
3. 怎么拦截(原理)？


### 六、动态SQL
1. `<sql>`、`<include>`
   
常用Sql片段复用, sql 标签定义可复用sql片段, 通过include-ref 将sql片段引入
参考示例: 
```xml
<sql id="reuseSqlText" >
    username,birthday,sex,address
</sql>

<select>
    select 
    <include refid="reuseSqlText">
    </include>
    from t_user_base
</select>
```
   
2. `<if>`

条件判断式,常用于基于某一个判断条件, 来决定是否继续后续的条件式 使用方式eg:
```xml
   <if test="username != null">
       username=#{username},
   </if>
```
3. `<where>`
> where 标签可以将后续条件语句中产生的第一个 and 去掉, 避免强制添加 1 = 1 的场景
   
4. `<set>`
> set标签就可以自动帮我们将非首个成立的if语句中对应的代码块的前缀逗号给剔除掉
或者非最后一个成立的if语句中对应的代码块的后缀逗号给剔除掉
> 
> 直接点说明就是: 可以去掉影响语句正确性的逗号 `,`

5. `<trim>`

- `prefix`：在trim标签内sql语句加上指定前缀
- `suffix`：在trim标签内sql语句加上指定后缀
- `prefixOverrides`：指定去除多余的前缀内容，如prefixOverrides=“and | or”，去除trim标签内sql语句多余的前缀"and"或者"or"。
- `suffixOverrides`：去除指定的多余的后缀内容。如suffixOverrides=“and | or”，去除trim标签内sql语句多余的后缀"and"或者"or"。
```xml
<update id="trimTest" parameterType="Object">
	update user set 
    <!-- 会去除多于的逗号后缀 -->
	<trim  suffixOverrides=",">
            <if test="username != null">
                username=#{username},
            </if>
            <if test="sex != null">
                sex11zhidin=#{sex},
            </if>
	</trim> 
	where id=#{id}
</update>
```

6. `<foreach>`
```xml
<select id="queryTestInCause" parameterType="java.util.List" resultType="com.mybatis.testing.entity.User">
    select * from t_user_base
    <if test=" nameList != null and nameList.size() > 0 ">
        where name in
        <foreach collection="nameList" index="index" item="na" separator="," open="(" close=")">
            #{na}
        </foreach>
    </if>

</select>
```   

7. `<choose>`、`<when>`
```xml
<select id="queryChooseWhen" parameterType="com.mybatis.testing.dto.UserQueryDTO"
        resultType="com.mybatis.testing.entity.User">
    select * from t_user_base
    where 1=1
    <choose>
        <when test="userName != null">
            and name = #{userName}
        </when>
        <when test="addr != null">
            and addr = #{addr}
        </when>
        <otherwise>
            and name = "xxx" and addr = "xxx"
        </otherwise>
    </choose>
</select>
```

### 七、配置支持

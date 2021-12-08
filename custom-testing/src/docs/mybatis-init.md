### What Happened On Mybatis Application Initializing 

1. SqlSessionFactoryBuilder.build() // 创建 build 
1-1. XmlConfigBuilder.parse() // 解析config，生成Configuration对象, 作为全局上下文
1-1-1. XmlConfigBuilder.parseConfiguration()  // 解析.xml配置文件的的 `/configuration` element




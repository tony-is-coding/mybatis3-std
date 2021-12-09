#### How Mybatis Plugins Work


1. 通过xml解析接口注解, 生成 signatureMap, 只支持接口级别方法拦截
2. 当通过创建session的 openSession方法创建 executor 的时候, 对executor 进行plugin代理, 是循环代理, 
    按照解析到的plugin的顺序进行由外到内的代理
3. statementHandler 拦截在statementHandler 创建时注入, resultHandler 与 parameterHandler 在 statementHandler内管理
4. 创建以上拦截的创建统一由Configuration管理, 由Interceptor接口的pluginAll进行拦截插件安装  

# 准备工作

## 引入jar
> **warning**: es需要jdk8的环境,而jdk8不兼容spring3,需要升级为spring4+,会导致一系列更新带来的问题:jar版本,配置文件等问题
```xml
    <!--elasticsearch-->
    <dependency>
        <groupId>org.elasticsearch.client</groupId>
        <artifactId>transport</artifactId>
        <version>6.2.2</version>
    </dependency>
```
# 创建客户端实例

## 通过java 代码

利用@Configuration ,和@Bean注解
```java
@Configuration
public class MyConfig {
    @Bean
    public TransportClient client() throws IOException {
        //读取配置文件
        Properties prop = new Properties();
        prop.load(this.getClass().getResourceAsStream("/elasticSearch/elasticSearch.properties"));
        //获取集群名称
        String clusterName = prop.getProperty("es.clusterName");
        //build 配置
        Settings settings =Settings.builder()
            .put("cluster.name",clusterName)
            .build();
        //创建TransportClient
        TransportClient client = new PreBuiltTransportClient(settings);
        //添加node-->分片
        client.addTransportAddresses(new TransportAddress(new InetSocketAddress("localhost",9300)));
        client.addTransportAddresses(new TransportAddress(new InetSocketAddress("localhost",8300)));
        return client;
    }
}
```

# CRUD


package top.linjt.java_learning.elasticSearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

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

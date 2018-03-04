package top.linjt.java_learning.elasticSearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyConfig {
    @Bean
    public TransportClient client()   {
        Settings settings =Settings.builder()
            .put("cluster.name","laowang")
            .build();
        TransportClient client = new PreBuiltTransportClient(settings);

        client.addTransportAddresses(new TransportAddress(new InetSocketAddress("localhost",9300)));
        client.addTransportAddresses(new TransportAddress(new InetSocketAddress("localhost",8300)));


        return client;

    }
}

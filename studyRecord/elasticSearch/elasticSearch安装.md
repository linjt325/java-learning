
## 入门

github: [这里](https://github.com/elastic/elasticsearch)

**步骤**:
1. 下载压缩包并解压*__*
2. 进入bin 目录启动elasticSearch.bat (windows)

## 修改配置文件
1. 进入config目录,修改elasticsearch.yml文件
- 跨域问题
```prop
# 支持跨域 ,在使用elasticsearch-head 插件时需要设置
    http.cors.enabled: true
    http.cors.allow-origin: "*"
```
- 配置集群
```
cluster.name: laowang  #集群名称,统一集群内该属性需要一致
node.name: slave2  # 节点名称

# 配置集群master时用到
# node.master: true 
network.host: 127.0.0.1   

http.port: 7200  # 节点端口号

discovery.zen.ping.unicast.hosts: ["127.0.0.1"]
```

## 可视化插件
> [elasticsearch-head](https://github.com/mobz/elasticsearch-head) :https://github.com/mobz/elasticsearch-head  
> kibana

## 基础知识

1. **集群和节点**
    > 一个集群由多个节点组成
2. **索引**: 含有相同属性给的文档集合
3. **类型**: 索引可以定义一个或多个类型,文档必须属于一个类型
4. **文档**: 文档是可以被索引的基本数据单位
5. **分片**: 每个索引都有多个分片,每个分片是一个Lucene索引
6. **备份**: 拷贝一份分片就完成了分片的备份


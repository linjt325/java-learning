package top.linjt.java_learning.elasticSearch.controller;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.linjt.java_learning.elasticSearch.Cast;
import top.linjt.java_learning.elasticSearch.entity.Book;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("book/novel")
public class ElasticSearchController {

    private final TransportClient client;

    @Autowired
    public ElasticSearchController(TransportClient client) {
        this.client = client;
    }

    /**
    * 根据id 获取文档
    */
    @RequestMapping("/get")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity get(@RequestParam(value="id") String id ){

        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse result = client.prepareGet("book","novel",id).get();

        if(!result.isExists()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getSource(),HttpStatus.OK);
    }


    /**
    * 新增文档
    */
    @RequestMapping("/add")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity add(@RequestBody Book book){
        XContentBuilder content;
        try {
            //构建文档对象
            content = XContentFactory.jsonBuilder()
                .startObject()
                .field("count", book.getCount())
                .field("author", book.getAuthor())
                .field("price",book.getPrice())
                .field("updateDate",book.getUpdateDate().getTime() )
                .field("title", book.getTitle())
                .endObject();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //
        /**
        * 指定 索引/类型/id
        * 指定文档的source
        */
        IndexRequestBuilder index = client.prepareIndex("book", "novel", book.getId()).setSource(content);
        //执行
        IndexResponse res = index.get();
        return new ResponseEntity(res.getId(),HttpStatus.OK);
    }


    @RequestMapping("delete")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity delete(String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        DeleteResponse result = client.prepareDelete("book", "novel", id).get();

        return  new ResponseEntity(result.getResult().toString(),HttpStatus.OK );

    }

    /**
    * 更新索引文档
    */
    @RequestMapping("update")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity update(@RequestBody Book book){
        if(book.getId().isEmpty()){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UpdateRequestBuilder update = client.prepareUpdate("book","novel", book.getId());
        XContentBuilder contentBuilder =null;
        try {
            contentBuilder = XContentFactory.jsonBuilder()
                .startObject();
            if((book.getAuthor() != null) && (!book.getAuthor().isEmpty())){
                contentBuilder.field("author", book.getAuthor());
            }
            if(book.getCount() != 0){
                contentBuilder.field("count", book.getCount());
            }
            if(book.getPrice() != 0){
                contentBuilder.field("price", book.getPrice());
            }
            if(book.getTitle()!=null&&!book.getTitle().isEmpty()){
                contentBuilder.field("title", book.getTitle());
            }
            if(book.getUpdateDate()!=null&&!book.getUpdateDate().equals(null)){
                contentBuilder.field("updateDate", book.getUpdateDate().getTime());
            }
            contentBuilder.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UpdateResponse result = update.setDoc(contentBuilder).get();
        return new ResponseEntity(result.getResult(),HttpStatus.OK);
    }


    /**
    * 复合查询
    */
    @RequestMapping("query")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity complexQuery(String author ,
                                       String title ,
                                       @RequestParam( name = "gt_count",defaultValue = "0") int gte_count,
                                       @RequestParam(required = false, name = "lt_count") Integer lte_count){

        //构建布尔查询的对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if(author != null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("author", author));
        }

        if(title != null ){
            boolQueryBuilder.must(QueryBuilders.matchQuery("title", title));
        }
        //构建范围查询的条件
        RangeQueryBuilder filter = QueryBuilders.rangeQuery("count").gte(gte_count);
        if ( lte_count != null && lte_count > 0 ) {
            filter.lte(lte_count);
        }
        //构建符合查询对象,并指定查询的索引/类型,
        //指定查询类型
        //指定查询的条件
        //指定返回结果的起始偏移量以及结果数量
        SearchRequestBuilder builder = client.prepareSearch("book")
            .setTypes("novel")
            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
            .setQuery(boolQueryBuilder)
            .setFrom(0)
            .setSize(10);

        System.out.println(builder);
        //执行
        SearchResponse result = builder.get();

        ArrayList<Map<String, Object>> resultList = new ArrayList<>();
        for (SearchHit hit : result.getHits()) {
            resultList.add(hit.getSourceAsMap());
        }
        System.out.println(resultList);
        return new ResponseEntity(resultList,HttpStatus.OK);
    }
    
    /**
    * 聚合查询测试
    */
    @RequestMapping("aggs")
    @ResponseBody
    public ResponseEntity aggregationQuery (){

        SearchRequestBuilder search = client.prepareSearch("book");


        TermsAggregationBuilder countTerm = AggregationBuilders.terms("group_by_count").field("count");

        TermsAggregationBuilder dateTerm = AggregationBuilders.terms("group_by_updateDate").field("updateDate")
            .order(BucketOrder.count(false));

        AvgAggregationBuilder price_avg = AggregationBuilders.avg("avg_price").field("price");

        //根据count 和 date 进行分组 ,对分组内价格求平均 ,按照updateDate 倒叙排序
        search.addAggregation(countTerm.subAggregation(dateTerm.subAggregation(price_avg)));

        SearchResponse response = search.get();

        SearchHits hits = response.getHits();

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }

        System.out.println(response.getAggregations());


        Map<String, Object> ss = solveAggs(response.getAggregations().asList());
        return new ResponseEntity(ss, HttpStatus.OK);
    }

    private Map<String,Object> solveAggs(List<Aggregation> aggs){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> result_f = new HashMap<>();

/*
if( aggs == null || aggs.getAsMap().size() == 0){
return null ;
}
*/

        Iterator<Aggregation> iterator = aggs.iterator();
        while (iterator.hasNext()) {
            Map<String,Object> map;
            List<Map<String,Object>> list;

            //不能这么转换 ,不光LongTerms 一个类型 还有一些统计数据的类型  比如 InternalAvg 等
            Aggregation next = iterator.next();
            if(next instanceof LongTerms){
                LongTerms terms = (LongTerms) next ;
                int count = terms.getBuckets().size();
                if(count > 0){
                    map = new HashMap<>(count);
                    list =  new ArrayList<>(count);
                    map.put("name", terms.getName());
                    for (LongTerms.Bucket bucket : terms.getBuckets()) {
                        Map<String, Object> bucket_aggs = solveAggs(bucket.getAggregations().asList());
                        if(bucket_aggs.size() > 0){
                           list.add(bucket_aggs);
                        }
                    }
                }else {
                    continue;
                }
                result.put("bucket", list);
                result_f.put(terms.getName(),result );
//                result.put("name", terms.getName());
            }else{
                System.out.println(next.getType());
                //类型不为longterms 时 暂时先不管
                result_f.put("name", next.getName());
                result_f.put("type", next.getType());
                result_f.put("class", next.getClass());



            }

        }

        return result_f;
    }


}

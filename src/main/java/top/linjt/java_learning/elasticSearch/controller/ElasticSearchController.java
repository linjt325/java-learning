package top.linjt.java_learning.elasticSearch.controller;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.linjt.java_learning.elasticSearch.entity.Book;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("book/novel")
public class ElasticSearchController {

    @Autowired
    private TransportClient client;

    @RequestMapping("/get")
    @ResponseBody
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


    @RequestMapping("/add")
    @ResponseBody
    public ResponseEntity add(@RequestBody Book book){
        XContentBuilder content = null;
        try {
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
        IndexRequestBuilder index = client.prepareIndex("book", "novel", book.getId()).setSource(content);
        IndexResponse res = index.get();
        return new ResponseEntity(res.getId(),HttpStatus.OK);
    }


    @RequestMapping("delete")
    @ResponseBody
    public ResponseEntity delete(String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        DeleteResponse result = client.prepareDelete("book", "novel", id).get();

        return  new ResponseEntity(result.getResult().toString(),HttpStatus.OK );

    }

    @RequestMapping("update")
    @ResponseBody
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
}

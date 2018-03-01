package top.linjt.java_learning.elasticSearch.controller;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ElasticSearchController {

//    @Autowired
//    private TransportClient client;

//    @RequestMapping("/get/book/novel")
//    @ResponseBody
//    public ResponseEntity get(@RequestParam(value="id") String id ){
//
//        GetResponse result = client.prepareGet("book","novel",id).get();
//
//        if(result.isExists()){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(result.getSource(),HttpStatus.OK);
//    }
}

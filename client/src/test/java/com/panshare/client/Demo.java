package com.panshare.client;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.panshare.client.mapper.UserMapper;
import com.panshare.client.pojo.Post;
import com.panshare.client.pojo.User;
import com.panshare.client.service.PostService;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * @Author Key&Value
 * @Date 2023/2/20 14:36
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo {
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostService postService;

    @Test//添加索引
    public void addIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("post");
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    @Test//添加文档
    public void addDoc() throws Exception {
        User user = userMapper.selectById(2);
        IndexRequest request = new IndexRequest("user").id(user.getUserId().toString());
        String json = JSONUtil.toJsonStr(user);
        request.source(json, XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    @Test//批量插入
    public void batchAddDoc() throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        List<Post> list = postService.list();
        for (Post post : list) {
            IndexRequest request = new IndexRequest("post").id(post.getPostId().toString());
            String json = JSONUtil.toJsonStr(post);
            request.source(json, XContentType.JSON);
            bulkRequest.add(request);
        }
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @Test//查询文档
    public void searchDoc() throws IOException {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //searchSourceBuilder.query(QueryBuilders.matchQuery("all", "狂飙"));
        searchSourceBuilder.query(QueryBuilders.matchQuery("postContent", "狂飙"))
                .query(QueryBuilders.matchQuery("postTitle", "狂飙"));
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            String json = hit.getSourceAsString();
            Post post = JSONUtil.toBean(json, Post.class);
            System.out.println(post);
        }
    }
}

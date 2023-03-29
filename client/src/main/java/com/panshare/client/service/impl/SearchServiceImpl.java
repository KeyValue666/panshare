package com.panshare.client.service.impl;

import cn.hutool.json.JSONUtil;
import com.panshare.client.pojo.Post;
import com.panshare.client.service.SearchService;
import com.panshare.client.utils.Html2Text;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Key&Value
 * @Date 2023/3/12 17:32
 * @Version 1.0
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestHighLevelClient client;

    @Override
    public Map<String,Object> searchPosts(String key, Integer page, Integer pageSize) {
        Map<String,Object> map=new HashMap<>();
        List<Post> res = new ArrayList<>();
        //1.构建查询请求
        SearchRequest search = new SearchRequest();
        try {
            SearchSourceBuilder builder = new SearchSourceBuilder();
            //1.构建查询对象
            builder.query(QueryBuilders.matchQuery("postContent", key))
                    .query(QueryBuilders.matchQuery("postTitle", key));
            builder.from(page)
                    .size(pageSize);
            search.source(builder);
            //3.发送es请求
            SearchResponse searchResponse = client.search(search, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            map.put("total",hits.getTotalHits().value);
            //4.装载数据
            for (SearchHit hit : hits) {
                Post post = JSONUtil.toBean(hit.getSourceAsString(), Post.class);
                String postContent = post.getPostContent();
                //html数据转义为纯文本
                String content = Html2Text.getContent(postContent);
                post.setPostContent(content);
                res.add(post);
            }
        } catch (IOException e) {
            log.error("elasticsearch查询出现异常！错误信息:{}", e.getMessage());
            e.printStackTrace();
        }
        map.put("posts",res);
        return map;
    }
}

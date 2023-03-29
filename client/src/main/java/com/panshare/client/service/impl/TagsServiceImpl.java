package com.panshare.client.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panshare.client.common.Cache;
import com.panshare.client.mapper.TagsMapper;
import com.panshare.client.pojo.Tags;
import com.panshare.client.service.TagsService;
import com.panshare.client.utils.RedisUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<Tags> listAllTags() {
        if (this.redisUtils.hasKey(Cache.TAG_CACHE)) {
            String s = this.redisUtils.get(Cache.TAG_CACHE);
            JSONArray jsonArray = JSONUtil.parseArray(s);
            return JSONUtil.toList(jsonArray, Tags.class);
        }
        List<Tags> tags = baseMapper.selectList(null);
        this.redisUtils.set(Cache.TAG_CACHE, tags, Cache.TAG_CACHE_TIME, TimeUnit.HOURS);
        return tags;
    }

    @Override // com.hjs.panshare.service.TagsService
    public List<Tags> listTagNotPublic() {
        if (this.redisUtils.hasKey(Cache.TAG_CACHE_NOT_PUBLIC)) {
            String s = this.redisUtils.get(Cache.TAG_CACHE_NOT_PUBLIC);
            JSONArray jsonArray = JSONUtil.parseArray(s);
            return JSONUtil.toList(jsonArray, Tags.class);
        }
        QueryWrapper<Tags> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_is_public", 0);
        List<Tags> tags = baseMapper.selectList(queryWrapper);
        this.redisUtils.set(Cache.TAG_CACHE_NOT_PUBLIC, tags, Cache.TAG_CACHE_TIME, TimeUnit.HOURS);
        return tags;
    }
}

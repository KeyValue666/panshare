package com.panshare.client.controller;

import com.panshare.client.common.Cache;
import com.panshare.client.common.R;
import com.panshare.client.pojo.Tags;
import com.panshare.client.service.TagsService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping({"/tags"})
@RestController
public class TagsController {
    @Autowired
    private TagsService tagsService;

    @GetMapping({"/"})
    public R tags() {
        List<Tags> list = this.tagsService.listAllTags();
        return R.ok().data(Cache.TAG_CACHE, list);
    }

    @GetMapping({"/nopublic"})
    public R publicTags() {
        List<Tags> list = this.tagsService.listTagNotPublic();
        return R.ok().data(Cache.TAG_CACHE, list);
    }
}

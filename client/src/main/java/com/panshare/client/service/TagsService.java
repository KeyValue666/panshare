package com.panshare.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panshare.client.pojo.Tags;
import java.util.List;

/* loaded from: TagsService.class */
public interface TagsService extends IService<Tags> {
    List<Tags> listAllTags();

    List<Tags> listTagNotPublic();
}

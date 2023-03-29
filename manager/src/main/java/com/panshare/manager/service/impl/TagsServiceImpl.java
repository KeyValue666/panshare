package com.panshare.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.panshare.manager.common.R;
import com.panshare.manager.dto.TagDTO;
import com.panshare.manager.pojo.Tags;
import com.panshare.manager.mapper.TagsMapper;
import com.panshare.manager.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {
    @Autowired
    private TagsMapper tagsMapper;

    @Override
    public R saveTag(TagDTO tagDTO) {
        Tags tags = new Tags();
        BeanUtil.copyProperties(tagDTO, tags);
        int insert = tagsMapper.insert(tags);
        return R.ok().data("flag", insert > 0);
    }
}

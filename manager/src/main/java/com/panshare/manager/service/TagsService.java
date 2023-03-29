package com.panshare.manager.service;

import com.panshare.manager.dto.TagDTO;
import com.panshare.manager.pojo.Tags;
import com.baomidou.mybatisplus.extension.service.IService;
import com.panshare.manager.common.R;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
public interface TagsService extends IService<Tags> {

    /**
     * 保存帖子
     * @param tagDTO
     * @return
     */
    R saveTag(TagDTO tagDTO);
}

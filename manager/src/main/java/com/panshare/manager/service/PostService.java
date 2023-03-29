package com.panshare.manager.service;

import com.panshare.manager.common.R;
import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.pojo.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
public interface PostService extends IService<Post> {

    /**
     * 根据条件查询post
     * @param queryCondition
     * @return
     */
    R queryByCondition(QueryCondition queryCondition);

    /**
     * 置顶帖子
     * @param postId
     * @param isTop
     * @return
     */
    R setTop(Integer postId, Boolean isTop);

    /**
     * 删除一个帖子
     * @param postId
     * @return
     */
    boolean deleteOne(Integer postId);

    /**
     * 删除多个帖子
     * @param ids
     * @return
     */
    R deleteMore(List<Integer> ids);
}

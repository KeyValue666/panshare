package com.panshare.manager.mapper;

import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.pojo.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panshare.manager.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Key&Value
 * @since 2023-02-20
 */
@Repository
public interface PostMapper extends BaseMapper<Post> {

    List<PostVO> queryByCondition(@Param("queryCondition") QueryCondition queryCondition);

    /**
     * 批量删除post
     * @param ids
     * @return
     */
    boolean deleteBatch(@Param("ids") List<Integer> ids);
}

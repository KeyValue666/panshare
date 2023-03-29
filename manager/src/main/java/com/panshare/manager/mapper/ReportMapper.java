package com.panshare.manager.mapper;

import com.panshare.manager.dto.QueryCondition;
import com.panshare.manager.pojo.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panshare.manager.vo.ReportVO;
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
public interface ReportMapper extends BaseMapper<Report> {

    /**
     * 分页查询举报
     * @param queryCondition
     * @return
     */
    List<ReportVO> queryReport(QueryCondition queryCondition);
}

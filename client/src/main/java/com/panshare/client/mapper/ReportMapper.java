package com.panshare.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panshare.client.pojo.Report;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReportMapper extends BaseMapper<Report> {
    boolean saveReport(Report report);
}

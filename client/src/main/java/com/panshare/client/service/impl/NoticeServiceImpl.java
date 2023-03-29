package com.panshare.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panshare.client.mapper.NoticeMapper;
import com.panshare.client.pojo.Notice;
import com.panshare.client.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
}

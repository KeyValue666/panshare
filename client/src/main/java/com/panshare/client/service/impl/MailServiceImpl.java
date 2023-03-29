package com.panshare.client.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.panshare.client.common.Cache;
import com.panshare.client.common.PanShareException;
import com.panshare.client.service.MailService;
import com.panshare.client.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private MailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public String sendMail(String to) {
        String remoteAddr = request.getRemoteAddr();
        //1.为了防止邮箱接口被刷，这里需要对请求的IP进行限制
        if (redisUtils.hasKey(remoteAddr)) {
            throw new PanShareException("你操作太过频繁，请稍后重试！");
        }
        //将此次访问的IP地址记录到redis中，限制一秒
        redisUtils.set(remoteAddr,1, Cache.IP_MAIL, TimeUnit.MINUTES);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(this.from);
        mailMessage.setTo(to);
        mailMessage.setSubject("欢迎注册网盘分享小站");
        String code = RandomUtil.randomNumbers(6);
        mailMessage.setText("你的验证码为" + code + "，5分钟时间内有效！");
        this.mailSender.send(mailMessage);
        return code;
    }
}

package com.panshare.client;

import com.panshare.client.pojo.Visitor;
import com.panshare.client.utils.IPUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @Author Key&Value
 * @Date 2023/3/17 21:48
 * @Version 1.0
 */
@SpringBootTest
public class IPTest {
    @Test
    public void test() throws IOException {
        System.out.println(IPUtils.visitor("47.113.220.25"));
    }
}

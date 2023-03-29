package com.panshare.util;

import cn.hutool.core.util.RandomUtil;
import com.panshare.common.URLProperties;
import com.panshare.pojo.Post;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Key&Value
 * @Date 2023/3/9 20:30
 * @Version 1.0
 */
@Slf4j
public class HttpUtil {
    private static final String COOKIE = "__51vcke__JWPJL2zENFklfOta=04c22abb-3868-5276-9596-a801b6982fbd; __51vuft__JWPJL2zENFklfOta=1664022121083; flarum_remember=KiQyQ2Ql4yYthT7KM9w5Er8Td9XCNGg0UwxdZdj0; flarum_session=aNFsTrKRVUAO8gOMoJ8i0cLi0eldfAKpzX0KU4J2; __vtins__JWPJL2zENFklfOta=%7B%22sid%22%3A%20%222da14fe1-a372-5661-bb7f-4a26de22737e%22%2C%20%22vd%22%3A%201%2C%20%22stt%22%3A%200%2C%20%22dr%22%3A%200%2C%20%22expires%22%3A%201678440560561%2C%20%22ct%22%3A%201678438760561%7D; __51uvsct__JWPJL2zENFklfOta=30; __cf_bm=B.klzhcRGv0_U4tJRQ4lTd9FDBfjjvfWmXdv1Bwdz48-1678438762-0-ARNogdllfzJxmJir9IS/YNNtoSIiIswco42sr2CqxKOgAx2psig4d8P4aLYDY1QJDrPv9V4rzv6sxZ5bUO4w1PQmZCST6cBXMuRGWC9Bk/kIXnfX3Smb7DBD9RfbjLIQ0w==";

    /**
     * 请求指定页面
     *
     * @param url 页面地址
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        log.info("请求链接为：{}", url);
        Connection.Response response = Jsoup.connect(url)
                .ignoreContentType(true)
                .timeout(20000)
                .header("User-Agent", URLProperties.randomUA())
                .header("cookie", COOKIE)
                .execute();
        return response.body();
    }

    /**
     * 解析复杂网站的种子页面
     *
     * @param seedDocument 种子文档
     * @return
     */
    public static List<String> parseSeed(String seedDocument) {
        List<String> seed = new ArrayList<>();
        Elements elements = Jsoup.parse(seedDocument).select("div.container>ul>li");
        for (Element element : elements) {
            String href = element.select("a").attr("href");
            seed.add(href);
        }
        return seed;
    }
    /**
     * 解析普通网站的种子页面
     *
     * @param seedDocument 种子文档
     * @return
     */
    public static List<String> parseSeedSiteCommon(String seedDocument) {
        List<String> seed = new ArrayList<>();
        Elements elements = Jsoup.parse(seedDocument).select("div.container>ul>li");
        for (Element element : elements) {
            String href = "https://pan666.net"+element.select("a").attr("href");
            seed.add(href);
        }
        return seed;
    }

    /**
     * 解析详情页面
     *
     * @param seed 种子链接
     * @return
     * @throws IOException
     */
    public static Post pageDetail(String seed) throws IOException {
        String body = get(seed);
        Document document = Jsoup.parse(body);
        String title = document.select("div.container>h2").text();
        String html = document.select("div.Post-body").eq(0).html();
        Post post = new Post();
        post.setPostUserId(RandomUtil.randomInt(1, 20));
        post.setPostContent(html);
        post.setPostTitle(title);
        return post;
    }
}

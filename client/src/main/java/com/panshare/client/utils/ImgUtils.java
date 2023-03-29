package com.panshare.client.utils;

import cn.hutool.core.util.IdUtil;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
/* loaded from: ImgUtils.class */
public class ImgUtils {
    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.accessSecretKey}")
    private String accessSecretKey;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.imageUrl}")
    private String url;
    @Value("${qiniu.style}")
    private String style;

    public Map<String, List<String>> uploadImages(MultipartFile[] multipartFiles) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            imageUrls.add(uploadImageQiniu(file, true));
        }
        map.put("imageUrl", imageUrls);
        return map;
    }

    public String uploadImageQiniu(MultipartFile multipartFile, boolean isDiv) {
        try {
            byte[] fileBytes = multipartFile.getBytes();
            Long file = IdUtil.getSnowflakeNextId();
            String originalFilename = multipartFile.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = file + suffix;
            Configuration cfg = new Configuration(Region.xinjiapo());
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = Auth.create(this.accessKey, this.accessSecretKey);
            String upToken = auth.uploadToken(this.bucket);
            uploadManager.put(fileBytes, filename, upToken);
            String imgUrl=url+filename;
            return isDiv ? imgUrl + this.style : imgUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

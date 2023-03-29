package com.panshare.client.controller;

import com.panshare.client.common.R;
import com.panshare.client.utils.ImgUtils;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping({"/upload"})
@RestController
public class UploadController {
    @Autowired
    private ImgUtils imgUtils;

    @PostMapping({"/single"})
    public R upload(@RequestBody MultipartFile file) {
        String imageQiniu = this.imgUtils.uploadImageQiniu(file, true);
        return R.ok().data("url", imageQiniu);
    }

    @PostMapping({"/uploadMore"})
    public R uploadMore(@RequestBody MultipartFile[] file) {
        Map<String, List<String>> map = this.imgUtils.uploadImages(file);
        return R.ok().data("list", map);
    }
}

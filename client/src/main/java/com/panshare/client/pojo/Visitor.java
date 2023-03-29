package com.panshare.client.pojo;

import lombok.Data;

/**
 * @Author Key&Value
 * @Date 2023/3/17 21:30
 * @Version 1.0
 */
@Data
public class Visitor {
    private String ip;//地址
    private String nation;//地区
    private String province;//所在省份
    private String city; //市
    private String district; //地区
}

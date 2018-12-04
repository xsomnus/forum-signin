package com.xsomnus.forumsignin.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xsomnus_xiawenye
 * @since 2018-12-03 23:33
 **/
@Data
public class Member implements Serializable {
    private String name;
    private String idCard;
    private Long signTime;
}

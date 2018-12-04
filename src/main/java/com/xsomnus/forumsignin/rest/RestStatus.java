package com.xsomnus.forumsignin.rest;

/**
 * @author somnus_xiawenye
 * @since 2018/7/10 17:41
 */
public interface RestStatus {

    /**
     * 返回码
     * 1. 0-代表成功，
     * 2. 负值代表系统错误，
     * 3. 正值代表业务错误
     */
    Integer getCode();

    /**
     * 返回码的描述
     */
    String getMsg();

    /**
     * @return status enum name
     */
    String name();


}

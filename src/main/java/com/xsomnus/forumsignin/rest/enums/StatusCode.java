package com.xsomnus.forumsignin.rest.enums;


import com.google.common.collect.ImmutableMap;
import com.xsomnus.forumsignin.rest.RestStatus;

import java.util.Arrays;

/**
 * @author somnus_xiawenye
 * @since 2018/7/10 17:42
 */
public enum StatusCode implements RestStatus {

    SUCCESS(0, "success"),
    SYSTEM_ERROR(-1, "系统繁忙，请稍后重试"),
    MEMBER_NOT_EXIST(101001, "用户不存在"),
    MEMBER_NAME_NOT_MATCH(101002, "身份证与姓名不匹配"),
    SIGNED(101003, "已签到");

    private static final ImmutableMap<Integer, StatusCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, StatusCode> b = ImmutableMap.builder();
        Arrays.stream(StatusCode.values()).forEach(
                statusCode -> b.put(statusCode.code, statusCode)
        );
        CACHE = b.build();
    }

    private final int code;
    private final String msg;


    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static StatusCode getByCode(int code) {
        final StatusCode statusCode = CACHE.get(code);
        if (statusCode == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        }
        return statusCode;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}

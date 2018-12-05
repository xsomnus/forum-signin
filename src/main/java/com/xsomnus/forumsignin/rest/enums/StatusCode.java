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
    REQUEST_ILLEGAL(-2, "请求非法"),
    MEMBER_NOT_EXIST(101001, "用户不存在，请前往一楼注册等级"),
    MEMBER_NAME_NOT_MATCH(101002, "身份证与姓名不匹配"),
    SIGNED(101003, "已签到"),
    SIGNED_INFO_EMPTY(101004, "签到信息不存在"),
    EVENT_NOT_STARTED(101005, "活动未开始"),
    EVENT_STOPPED(101006, "今日活动已结束")
    ;

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

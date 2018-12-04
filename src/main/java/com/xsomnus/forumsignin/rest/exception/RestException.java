package com.xsomnus.forumsignin.rest.exception;


import com.xsomnus.forumsignin.rest.RestStatus;

/**
 * @author somnus_xiawenye
 * @since 2018/7/10 17:45
 */
public class RestException extends RuntimeException {

    private final int code;

    public RestException(RestStatus restStatus) {
        super(restStatus.getMsg());
        this.code = restStatus.getCode();
    }

    /**
     * 自定义错误信息
     *
     * @param message 错误信息
     * @param code    错误码
     */
    public RestException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

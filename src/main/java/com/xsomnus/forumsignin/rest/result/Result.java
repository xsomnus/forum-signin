package com.xsomnus.forumsignin.rest.result;

import com.xsomnus.forumsignin.rest.RestStatus;
import lombok.Data;

/**
 * @author somnus_xiawenye
 * @since 2018/6/11 14:26
 */
@Data
public class Result {

    private Integer code;

    private String msg;

    private Object data;


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(RestStatus status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
    }
}

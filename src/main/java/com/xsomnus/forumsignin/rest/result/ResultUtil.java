package com.xsomnus.forumsignin.rest.result;


import com.xsomnus.forumsignin.rest.RestStatus;
import com.xsomnus.forumsignin.rest.enums.StatusCode;

/**
 * @author somnus_xiawenye
 * @since 2018/5/28 16:17
 */
public class ResultUtil {

    /**
     * @param o 要返回给前端的数据
     */
    public static Result success(Object o) {
        Result result = new Result(StatusCode.SUCCESS);
        result.setData(o);
        return result;
    }

    /**
     * 提供给部分不需要数据的接口
     */
    public static Result success() {
        return success(null);
    }


    /**
     * {@link StatusCode}
     *
     * @param restStatus 返回值接口类型
     */
    public static Result error(RestStatus restStatus) {
        return new Result(restStatus);
    }

    /**
     * @param code 自定义错误码
     * @param msg  自定义描述
     */
    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

}

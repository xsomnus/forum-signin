package com.xsomnus.forumsignin.config;

import com.xsomnus.forumsignin.rest.enums.StatusCode;
import com.xsomnus.forumsignin.rest.result.Result;
import com.xsomnus.forumsignin.rest.result.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xsomnus_xiawenye
 * @since 2018-12-04 21:00
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public Result exceptionGet(Exception e) {
        e.printStackTrace();
        return ResultUtil.error(StatusCode.SYSTEM_ERROR);
    }
}

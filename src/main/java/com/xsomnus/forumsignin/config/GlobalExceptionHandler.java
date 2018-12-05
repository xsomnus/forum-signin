package com.xsomnus.forumsignin.config;

import com.xsomnus.forumsignin.rest.enums.StatusCode;
import com.xsomnus.forumsignin.rest.exception.RestException;
import com.xsomnus.forumsignin.rest.result.Result;
import com.xsomnus.forumsignin.rest.result.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

/**
 * @author xsomnus_xiawenye
 * @since 2018-12-04 21:00
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = RestException.class)
    public Result exceptionGet(RestException e) {
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ServerWebInputException.class)
    public Result exceptionGet(ServerWebInputException e) {
        return ResultUtil.error(StatusCode.REQUEST_ILLEGAL);
    }

    @ExceptionHandler(value = Exception.class)
    public Result exceptionGet(Exception e) {
        e.printStackTrace();
        return ResultUtil.error(StatusCode.SYSTEM_ERROR);
    }
}

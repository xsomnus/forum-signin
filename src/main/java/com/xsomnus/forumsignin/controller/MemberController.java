package com.xsomnus.forumsignin.controller;

import com.xsomnus.forumsignin.constant.Constants;
import com.xsomnus.forumsignin.constant.SignInLuaConstant;
import com.xsomnus.forumsignin.pojo.requests.MemberSignInReq;
import com.xsomnus.forumsignin.rest.enums.StatusCode;
import com.xsomnus.forumsignin.rest.result.Result;
import com.xsomnus.forumsignin.rest.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisScriptingCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xsomnus_xiawenye
 * @since 2018-12-03 23:34
 **/
@RestController
@RequestMapping("/member")
public class MemberController {


    private RedisScriptingCommands scriptingCommands;

    @Autowired
    public MemberController(StringRedisTemplate stringRedisTemplate) {
        scriptingCommands = stringRedisTemplate.execute(RedisConnection::scriptingCommands);
    }

    @PostMapping("/signin")
    public Mono<Result> signIn(@RequestBody MemberSignInReq req) {
        OffsetDateTime now = OffsetDateTime.now();
        String idCardKey = String.format(Constants.BASE_MEMBERS, req.getIdCard());
        String serialNoKey = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String signKey = String.format(Constants.SIGN_USERS, serialNoKey, req.getIdCard());
        byte[] signTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).getBytes();
        assert scriptingCommands != null;
        Object result = scriptingCommands.eval(
                SignInLuaConstant.signInScript.getBytes(),
                ReturnType.INTEGER,
                3,
                idCardKey.getBytes(),
                serialNoKey.getBytes(),
                signKey.getBytes(),
                req.getName().getBytes(),
                req.getIdCard().getBytes(),
                signTime
        );
        assert result != null;
        return Mono.just(ResultUtil.error(StatusCode.getByCode(Integer.parseInt(result.toString()))));
    }


}

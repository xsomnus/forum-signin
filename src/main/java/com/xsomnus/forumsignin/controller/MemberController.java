package com.xsomnus.forumsignin.controller;

import com.xsomnus.forumsignin.config.ForumEventProperties;
import com.xsomnus.forumsignin.constant.Constants;
import com.xsomnus.forumsignin.constant.SignInLuaConstant;
import com.xsomnus.forumsignin.pojo.requests.MemberSignInReq;
import com.xsomnus.forumsignin.rest.enums.StatusCode;
import com.xsomnus.forumsignin.rest.exception.RestException;
import com.xsomnus.forumsignin.rest.result.Result;
import com.xsomnus.forumsignin.rest.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisScriptingCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author xsomnus_xiawenye
 * @since 2018-12-03 23:34
 **/
@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {


    private RedisScriptingCommands scriptingCommands;

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    ForumEventProperties eventProperties;

    @Autowired
    public MemberController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        scriptingCommands = stringRedisTemplate.execute(RedisConnection::scriptingCommands);
    }

    @PostMapping("/signin")
    public Mono<Result> signIn(@RequestBody MemberSignInReq req, @RequestParam String token) {

        ForumEventProperties.EventProperties eventProperties = this.eventProperties.getMaps().get(token);
        OffsetDateTime startTime = eventProperties.getStartTime();
        OffsetDateTime endTime = eventProperties.getEndTime();
        OffsetDateTime now = OffsetDateTime.now();

        if (now.isBefore(startTime)) {
            throw new RestException(StatusCode.EVENT_NOT_STARTED);
        }

        if (now.isAfter(endTime)) {
            throw new RestException(StatusCode.EVENT_STOPPED);
        }

        String idCardKey = String.format(Constants.BASE_MEMBERS, req.getIdCard());
        String serialNoKey = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String signKey = String.format(Constants.SIGN_USERS, serialNoKey, req.getIdCard());
        byte[] signTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).getBytes();
        assert scriptingCommands != null;
        Long result = scriptingCommands.eval(
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

        if (result.intValue() == StatusCode.SUCCESS.getCode()) {
            return Mono.just(ResultUtil.success(req.getIdCard()));
        } else {
            return Mono.just(ResultUtil.error(StatusCode.getByCode(result.intValue())));
        }
    }

    @GetMapping("/signin")
    public Mono<Result> getSignInDetail(@RequestParam String idCard) {
        log.info("lists:{}", eventProperties);
        OffsetDateTime now = OffsetDateTime.now();
        String serialNoKey = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String signKey = String.format(Constants.SIGN_USERS, serialNoKey, idCard);
        Map<Object, Object> objectMap = stringRedisTemplate.opsForHash().entries(signKey);
        if (objectMap.isEmpty()) {
            return Mono.just(ResultUtil.error(StatusCode.SIGNED_INFO_EMPTY));
        } else {
            return Mono.just(ResultUtil.success(objectMap));
        }
    }

    @PostMapping("/members/save")
    public Mono<Result> addMembers() {
        return Mono.just(ResultUtil.success());
    }


}

package com.xsomnus.forumsignin.controller;

import com.xsomnus.forumsignin.pojo.entity.Member;
import com.xsomnus.forumsignin.pojo.requests.MemberSignInReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author xsomnus_xiawenye
 * @since 2018-12-03 23:34
 **/
@RestController
@RequestMapping("/member")
public class MemberController {


    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/signin")
    public Mono<Long> signin(@RequestBody MemberSignInReq req) {
        Member member = new Member();
        member.setName(req.getName());
        member.setTelephone(req.getTelephone());
        //member.setSignTime(System.currentTimeMillis());
        Long add = redisTemplate
                .opsForSet()
                .add("12345678", member);
        return Mono.just(add);
    }


}

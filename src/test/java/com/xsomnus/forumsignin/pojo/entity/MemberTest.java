package com.xsomnus.forumsignin.pojo.entity;

import org.junit.Test;

/**
 * @author somnus_xiawenye
 * @since 2018/12/4 0004 10:13
 */
public class MemberTest {

    @Test
    public void string() {
        long i = 1;
        System.out.println(String.format("%04d", i));
    }
    @Test
    public void sub() {
        String key = "BaseMembers:411524199211105635";

        String substring = key.substring("BaseMembers:".length());
        System.out.println(substring);

    }
}
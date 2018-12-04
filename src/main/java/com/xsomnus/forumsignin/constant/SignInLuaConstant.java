package com.xsomnus.forumsignin.constant;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @author somnus_xiawenye
 * @since 2018/12/4 0004 10:49
 */
public final class SignInLuaConstant {

    public static String signInScript;

    static {
        signInScript = loadScript();
    }

    private static String loadScript() {
        DefaultRedisScript<Integer> r = new DefaultRedisScript<>();
        r.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/signIn.lua")));
        return r.getScriptAsString();
    }
}

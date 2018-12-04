package com.xsomnus.forumsignin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xsomnus_xiawenye
 * @since 2018-12-04 22:19
 **/
@Data
@Component
@ConfigurationProperties(prefix = "forum.events")
public class ForumEventProperties {

    private List<EventProperties> lists = new ArrayList<>();

    private Map<String, EventProperties> maps = new HashMap<>();

    @Data
    public static class EventProperties {
        String id;
        String startTime;
        String endTime;
        Integer rewardScores;
    }
}

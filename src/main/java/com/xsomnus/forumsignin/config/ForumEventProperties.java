package com.xsomnus.forumsignin.config;

import com.xsomnus.forumsignin.utils.OffsetDateTimeUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xsomnus_xiawenye
 * @since 2018-12-04 22:19
 **/
@Data
@Component
@ConfigurationProperties(prefix = "forum.events")
public class ForumEventProperties {
    private String key;
    private Map<String, EventProperties> maps = new HashMap<>();

    @Data
    public static class EventProperties {
        String start;
        String end;
        Integer rewardScores;

        public OffsetDateTime getStartTime() {
            return OffsetDateTimeUtils.parse(this.start);
        }

        public OffsetDateTime getEndTime() {
            return OffsetDateTimeUtils.parse(this.end);
        }
    }
}

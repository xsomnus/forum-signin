package com.xsomnus.forumsignin.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author somnus_xiawenye
 * @since 2018/12/5 0005 10:16
 */
public class OffsetDateTimeUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static OffsetDateTime parse(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(8));
    }
}

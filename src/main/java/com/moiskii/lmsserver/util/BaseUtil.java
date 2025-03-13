package com.moiskii.lmsserver.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class BaseUtil {
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime addDaysToLocalDateTime(int days, LocalDateTime localDateTime) {
        return localDateTime.plusDays(days);
    }
}

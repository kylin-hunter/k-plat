package com.kylinhunter.plat.commons.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.kylinhunter.plat.commons.exception.inner.ParamException;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-05 01:23
 **/
@Slf4j
public class DateUtils {
    public static final String FROMAT_DATE = "yyyy-MM-dd";
    public static final String FROMAT_DATE_NO_SEP = "yyyyMMdd";
    public static final String FROMAT_HOUR = "yyyy-MM-dd HH";
    public static final String FROMAT_HOUR_NO_SEP = "yyyyMMddHH";
    public static final String FROMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FROMAT_DATE_TIME_NO_SEP = "yyyyMMddHHmmss";
    public static final String FROMAT_DATE_TIME_MILLIS = "yyyy-MM-dd HH:mm:ss:SSS";

    private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern(FROMAT_DATE);
    private static final DateTimeFormatter FORMATTER_DATE_NO_SEP = DateTimeFormatter.ofPattern(FROMAT_DATE_NO_SEP);
    private static final DateTimeFormatter FORMATTER_HOUR = DateTimeFormatter.ofPattern(FROMAT_HOUR);
    private static final DateTimeFormatter FORMATTER_HOUR_NO_SEP = DateTimeFormatter.ofPattern(FROMAT_HOUR_NO_SEP);

    private static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern(FROMAT_DATE_TIME);
    private static final DateTimeFormatter FORMATTER_DATE_TIME_NO_SEP =
            DateTimeFormatter.ofPattern(FROMAT_DATE_TIME_NO_SEP);
    private static final DateTimeFormatter FORMATTER_DATE_TIME_MILLIS =
            DateTimeFormatter.ofPattern(FROMAT_DATE_TIME_MILLIS);

    public static String toStringDate() {
        return FORMATTER_DATE.format(LocalDate.now());
    }

    public static String toStringDateTime() {
        return FORMATTER_DATE_TIME.format(LocalDateTime.now());
    }

    /**
     * @param date 入参格式 "yyyyMMddHHmmss"
     * @return java.lang.String
     * @throws
     * @title 转换标准的时间日期格式   "yyyy-MM-dd HH:mm:ss"
     * @description
     * @author BiJi'an
     * @updateTime 2021/11/9 11:05 下午
     */
    public static String toStringDateTime(String date) {
        try {
            if (date != null) {
                return FORMATTER_DATE_TIME.format(LocalDateTime.parse(date, FORMATTER_DATE_TIME_NO_SEP));
            }
        } catch (Exception e) {
            log.warn("format error ", e);
        }
        return null;
    }

    public static String toStringDateTime(LocalDateTime localDateTime) {
        return FORMATTER_DATE_TIME.format(localDateTime);
    }

    public static String toString(LocalDateTime localDateTime, Format format) {
        try {
            switch (format) {
                case FROMAT_DATE: {
                    return FORMATTER_DATE.format(localDateTime);
                }
                case FROMAT_DATE_NO_SEP: {
                    return FORMATTER_DATE_NO_SEP.format(localDateTime);
                }
                case FROMAT_HOUR: {
                    return FORMATTER_HOUR.format(localDateTime);
                }
                case FROMAT_HOUR_NO_SEP: {
                    return FORMATTER_HOUR_NO_SEP.format(localDateTime);
                }

                case FROMAT_DATE_TIME: {
                    return FORMATTER_DATE_TIME.format(localDateTime);
                }

                case FROMAT_DATE_TIME_NO_SEP: {
                    return FORMATTER_DATE_TIME_NO_SEP.format(localDateTime);
                }

                case FROMAT_DATE_TIME_MILLIS: {
                    return FORMATTER_DATE_TIME_MILLIS.format(localDateTime);
                }
                default: {
                    throw new ParamException("invalid format" + format);
                }
            }
        } catch (ParamException e) {
            log.error("invalid localDateTime" + localDateTime, e);
        }
        return StringUtils.EMPTY;

    }

    public static String toStringDateTimeMillis() {
        return FORMATTER_DATE_TIME_MILLIS.format(LocalDateTime.now());
    }

    public static String toStringDateTimeMillis(long date) {
        LocalDateTime localDateTime = new Date(date).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return FORMATTER_DATE_TIME_MILLIS.format(localDateTime);
    }

    public static String toStringDateTimeMillis(LocalDateTime localDateTime) {
        return FORMATTER_DATE_TIME_MILLIS.format(localDateTime);
    }

    public static LocalDateTime toLocalDateTimeMillis(String date) {
        return LocalDateTime.parse(date, FORMATTER_DATE_TIME_MILLIS);
    }

    public static LocalDateTime toLocalDateTime(String date) {
        return toLocalDateTime(date, Format.FROMAT_DATE_TIME);
    }

    public static LocalDateTime toLocalDateTime(String date, Format format) {
        try {
            switch (format) {
                case FROMAT_DATE: {
                    return LocalDateTime.parse(date, FORMATTER_DATE);
                }
                case FROMAT_DATE_NO_SEP: {
                    return LocalDateTime.parse(date, FORMATTER_DATE_NO_SEP);
                }
                case FROMAT_HOUR: {
                    return LocalDateTime.parse(date, FORMATTER_HOUR);
                }
                case FROMAT_HOUR_NO_SEP: {
                    return LocalDateTime.parse(date, FORMATTER_HOUR_NO_SEP);
                }

                case FROMAT_DATE_TIME: {
                    return LocalDateTime.parse(date, FORMATTER_DATE_TIME);
                }

                case FROMAT_DATE_TIME_NO_SEP: {
                    return LocalDateTime.parse(date, FORMATTER_DATE_TIME_NO_SEP);
                }

                case FROMAT_DATE_TIME_MILLIS: {
                    return LocalDateTime.parse(date, FORMATTER_DATE_TIME_MILLIS);
                }
                default: {
                    throw new ParamException("invalid format" + format);
                }
            }
        } catch (ParamException e) {
            log.error("invalid date" + date, e);
        }
        return null;

    }

    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER_DATE);
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
    }

    public static LocalDateTime effectTime() {
        return LocalDateTime.parse("2000-01-01 00:00:00", FORMATTER_DATE_TIME);
    }

    public static LocalDateTime expireTime() {
        return LocalDateTime.parse("2099-12-31 23:59:59", FORMATTER_DATE_TIME);
    }

    public enum Format {
        FROMAT_DATE, /* "yyyy-MM-dd"*/
        FROMAT_DATE_NO_SEP, /* "yyyyMMdd" */
        FROMAT_HOUR, /* "yyyy-MM-dd HH"*/
        FROMAT_HOUR_NO_SEP, /* "yyyyMMddHH" */
        FROMAT_DATE_TIME, /* "yyyy-MM-dd HH:mm:ss"*/
        FROMAT_DATE_TIME_NO_SEP, /* "yyyyMMddHHmmss"*/
        FROMAT_DATE_TIME_MILLIS; /* "yyyy-MM-dd HH:mm:ss:SSS"*/
    }

}

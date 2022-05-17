package com.kylinhunter.plat.commons.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kylinhunter.plat.commons.exception.inner.FormatException;

import lombok.extern.slf4j.Slf4j;

/**
 * @description json工具
 * @author BiJi'an
 * @date   2021/7/29
 **/
@Slf4j
public class JsonUtils {

    public static ObjectMapper objectMapper;
    public static ObjectMapper objectMapperSnake;
    public static JavaType listMapDataType;
    public static final byte[] EMPTY_BYTES = new byte[0];

    static {
        objectMapper = createObjectMapper();
        listMapDataType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Map.class);
        objectMapperSnake = createObjectMapper();
        objectMapperSnake.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    }

    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //        objectMapper.setSerializationInclusion(Include.ALWAYS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 允许json中包含非引号控制字符
        objectMapper.configure(
                JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),
                true
        );
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 设置JSON时间格式
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(myDateFormat);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        String defaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";

        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(defaultDateTimeFormat)));
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(defaultDateTimeFormat)));

        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    public static JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        return objectMapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }

    /**
     * @param data
     * @param type
     * @param throwEx
     * @return T
     * @throws
     * @title 字节转对象
     * @description
     * @author BiJi'an
     * @updateTime 2021/7/29 4:25 下午
     */
    public static <T> T toObject(byte[] data, Class<T> type, boolean throwEx) throws FormatException {
        try {
            if (data == null || data.length == 0) {
                throw new FormatException("body is null or empty ");
            }
            return objectMapper.readValue(data, type);
        } catch (Exception e) {
            if (throwEx) {
                throw new FormatException("json toObject error", e);
            } else {
                log.error("json toObject error", e);
            }
        }
        return null;
    }

    /**
     * @param content
     * @param valueType
     * @param throwEx
     * @return T
     * @throws
     * @title 字符转对象
     * @description
     * @author BiJi'an
     * @updateTime 2021/7/29 4:28 下午
     */
    public static <T> T toObject(String content, Class<T> valueType, boolean throwEx) throws FormatException {

        return toObject(content, valueType, false, throwEx);
    }

    public static <T> T toObject(String content, Class<T> valueType, boolean snake, boolean throwEx)
            throws FormatException {

        try {
            if (snake) {
                return objectMapperSnake.readValue(content, valueType);
            } else {
                return objectMapper.readValue(content, valueType);
            }
        } catch (Exception e) {
            if (throwEx) {
                throw new FormatException("json toObject error", e);
            } else {
                log.error("json toObject error", e);
            }
        }
        return null;
    }

    /**
     * @param source
     * @param throwEx
     * @return byte[]
     * @throws
     * @title 对象转字节
     * @description
     * @author BiJi'an
     * @updateTime 2021/7/29 4:29 下午
     */
    public static byte[] toBytes(Object source, boolean throwEx) throws FormatException {

        try {
            return objectMapper.writeValueAsBytes(source);
        } catch (Exception e) {
            if (throwEx) {
                throw new FormatException("json toObject error", e);
            } else {
                log.error("json toBytes error", e);
            }
        }
        return EMPTY_BYTES;
    }

    /**
     * @param value
     * @param throwEx
     * @return java.lang.String
     * @throws
     * @title 对象转字符串
     * @description
     * @author BiJi'an
     * @updateTime 2021/7/29 4:29 下午
     */

    public static String toString(Object value, boolean throwEx) throws FormatException {
        return toString(value, false, throwEx);
    }

    public static String toString(Object value, boolean snake, boolean throwEx) throws FormatException {
        try {
            if (snake) {
                return objectMapperSnake.writeValueAsString(value);
            } else {
                return objectMapper.writeValueAsString(value);
            }

        } catch (Exception e) {
            if (throwEx) {
                throw new FormatException("json toString error", e);
            } else {
                log.error("json toString error", e);
            }
        }
        return "";
    }

    /**
     * @param content
     * @param throwEx
     * @return java.util.Map
     * @throws
     * @title json字符串转Map
     * @description
     * @author BiJi'an
     * @updateTime 2021/7/29 4:30 下午
     */
    public static Map toMap(String content, boolean throwEx) throws FormatException {

        try {
            return objectMapper.readValue(content, Map.class);
        } catch (Exception e) {
            if (throwEx) {
                throw new FormatException("json toMap error", e);
            } else {
                log.error("json toString error", e);
            }
        }
        return null;
    }

    /**
     * @param content
     * @param throwEx
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @throws
     * @title json字符串转List<Map>
     * @description
     * @author BiJi'an
     * @updateTime 2021/7/29 4:31 下午
     */
    public static List<Map<String, Object>> toListMap(String content, boolean throwEx) throws FormatException {

        try {
            return objectMapper.readValue(content, listMapDataType);
        } catch (Exception e) {
            if (throwEx) {
                throw new FormatException("json toListMap error", e);
            } else {
                log.error("json toListMap error", e);
            }
        }
        return null;
    }

    public static <T> T readValue(String content, JavaType valueType, boolean throwEx) throws FormatException {

        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            if (throwEx) {
                throw new FormatException("json readValue error", e);
            } else {
                log.error("json readValue error", e);
            }
        }
        return null;

    }

    public static <T> List<T> toList(String content, Class<T> tClass, boolean throwEx) {
        try {
            return objectMapper.readValue(content, new TypeReference<List<T>>() {
            });
        } catch (JsonProcessingException e) {
            if (throwEx) {
                throw new FormatException("json readValue error", e);
            } else {
                log.error("json readValue error", e);
            }
        }
        return null;
    }
}

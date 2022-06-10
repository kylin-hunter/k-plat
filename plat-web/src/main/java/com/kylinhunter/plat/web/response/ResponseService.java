package com.kylinhunter.plat.web.response;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Maps;
import com.kylinhunter.plat.commons.exception.ExceptionHelper;
import com.kylinhunter.plat.commons.exception.common.KRuntimeException;
import com.kylinhunter.plat.commons.util.date.DateUtils;
import com.kylinhunter.plat.web.i18n.I18nUtils;
import com.kylinhunter.plat.web.request.RequestContext;
import com.kylinhunter.plat.web.trace.Trace;
import com.kylinhunter.plat.web.trace.TraceHandler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2021/8/26
 **/
@Component
@Data
@Slf4j
public class ResponseService {
    private final TraceHandler traceHandler;
    private final RequestContext requestContext;

    /**
     * @param e
     * @return com.kylinhunter.plat.commons.web.response.DefaultResponse
     * @throws
     * @title toResponse
     * @description
     * @author BiJi'an
     * @date 2021/8/1 4:00 上午
     */
    public DefaultResponse toResponse(KRuntimeException e) {
        boolean debugMode = requestContext.isDebugMode();

        Trace trace = traceHandler.get();
        trace.setEndTime(System.currentTimeMillis());
        DefaultResponse response = new DefaultResponse();
        response.setCode(e.getErrInfo().getCode());
        String errMsg = I18nUtils.get(e.getErrInfo().getCode(), e.getExtra());
        if (errMsg != null) {
            response.setMsg(errMsg);
        } else {
            response.setMsg(ExceptionHelper.getMessage(e, debugMode, 1000));
        }
        response.setTime(trace.getStartTime());
        response.setDurationTime(trace.getDurationTime());
        response.setData(e.getExtra());
        if (debugMode) { // 更好地调试信息
            response.setStartTime(DateUtils.format(DateUtils.toLocalDateTime(trace.getStartTime())));
            response.setEndTime(DateUtils.format(DateUtils.toLocalDateTime(trace.getEndTime())));
        }
        response.setTraceId(trace.getId());
        if (!trace.getTraceExplain().isDummy()) {
            response.setTraceExplain(trace.getTraceExplain());
        }
        return response;

    }

    public void writeFile(String fileName, String content) {
        try {
            this.writeFile(fileName, content != null ? content.getBytes(StandardCharsets.UTF_8) : new byte[0], false);
        } catch (Exception e) {
            log.error("writeFile error ", e);
        }

    }

    public void writeFile(String fileName, byte[] content, boolean preview) throws UnsupportedEncodingException {

        Map<String, String> headers = Maps.newHashMap();
        String newFileName = URLEncoder.encode(fileName, "UTF-8");
        if (preview) {
            headers.put(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + newFileName);
            write(headers, MediaType.APPLICATION_OCTET_STREAM_VALUE, content);
        } else {
            headers.put(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + newFileName);
            write(headers, MediaType.APPLICATION_OCTET_STREAM_VALUE, content);
        }
    }

    public void writeFile(String fileName, InputStream inputStream) throws UnsupportedEncodingException {

        Map<String, String> headers = Maps.newHashMap();
        fileName = URLEncoder.encode(fileName, "UTF-8");
        headers.put(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
        write(headers, MediaType.APPLICATION_OCTET_STREAM_VALUE, inputStream);
    }

    public void writeJson(String jsonStr) {
        this.write(null, MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",
                jsonStr.getBytes(StandardCharsets.UTF_8));
    }

    public void write(Map<String, String> headers, String contentType, byte[] content) {

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        if (response != null) {
            try (OutputStream outputStream = response.getOutputStream();) {
                if (headers != null) {
                    headers.forEach((name, value) -> {
                        response.addHeader(name, value);
                    });
                }
                response.setContentType(contentType);
                outputStream.write(content);
                response.flushBuffer();
            } catch (Exception e) {
                log.error("write error", e);
            }
        }
    }

    public void write(Map<String, String> headers, String contentType, InputStream inputStream) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        if (response != null) {
            try (OutputStream outputStream = response.getOutputStream();) {
                if (headers != null) {
                    headers.forEach((name, value) -> {
                        response.addHeader(name, value);
                    });
                }
                response.setContentType(contentType);
                IOUtils.copy(inputStream, outputStream);
                response.flushBuffer();
            } catch (Exception e) {
                log.error("write error", e);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

}


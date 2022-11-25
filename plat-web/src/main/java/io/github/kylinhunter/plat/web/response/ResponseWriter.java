package io.github.kylinhunter.plat.web.response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Maps;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/26
 **/
@Component
@Data
@Slf4j
public class ResponseWriter {

    public void writeFile(String fileName, String content) {
        this.writeFile(fileName, content != null ? content.getBytes(StandardCharsets.UTF_8) : new byte[0], false);
    }

    public void writeFile(String fileName, byte[] content, boolean attachment) {
        this.writeFile(fileName, new ByteArrayInputStream(content), true);
    }

    public void writeFile(String fileName, InputStream inputStream, boolean attachment) {
        try {
            Map<String, String> headers = Maps.newHashMap();
            String newFileName = URLEncoder.encode(fileName, "UTF-8");
            if (attachment) {
                headers.put(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + newFileName);
            } else {
                headers.put(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + newFileName);
            }
            write(headers, inputStream);
        } catch (UnsupportedEncodingException e) {
            log.error("writeFile error ", e);
        }
    }

    public void writeJson(String json) {
        Map<String, String> headers = Maps.newHashMap();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        this.write(headers, json.getBytes(StandardCharsets.UTF_8));
    }

    public void write(Map<String, String> headers, byte[] content) {
        try (InputStream inputStream = new ByteArrayInputStream(content)) {
            this.write(headers, inputStream);
        } catch (IOException e) {
            log.error("write error", e);
        }
    }

    public void write(Map<String, String> headers, InputStream inputStream) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        if (response != null) {
            //            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            if (headers != null) {
                headers.forEach(response::addHeader);
            }
            try (OutputStream outputStream = response.getOutputStream()) {
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


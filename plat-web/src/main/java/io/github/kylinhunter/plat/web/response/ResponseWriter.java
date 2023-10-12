/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.web.response;

import com.google.common.collect.Maps;
import io.github.kylinhunter.commons.exception.ExceptionConvertor;
import io.github.kylinhunter.commons.exception.info.ErrInfo;
import io.github.kylinhunter.commons.utils.json.JsonOptions;
import io.github.kylinhunter.commons.utils.json.JsonUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/26
 */
@Data
@Slf4j
public class ResponseWriter {

  private final ResponseService responseService;

  public void write(Exception exception, boolean debug) {
    try {
      Response<?> response = responseService.toResponse(ExceptionConvertor.convert(exception),
          debug);
      this.writeJson(response);
    } catch (Exception e) {
      log.error("write error error", e);
    }
  }

  /**
   * @param fileName fileName
   * @param content  content
   * @return void
   * @title writeFile
   * @description writeFile
   * @author BiJi'an
   * @date 2023-10-02 00:57
   */
  public void writeFile(String fileName, String content) {
    this.writeFile(
        fileName, content != null ? content.getBytes(StandardCharsets.UTF_8) : new byte[0], false);
  }

  /**
   * @param fileName   fileName
   * @param content    content
   * @param attachment attachment
   * @return void
   * @title writeFile
   * @description writeFile
   * @author BiJi'an
   * @date 2023-10-02 00:57
   */
  public void writeFile(String fileName, byte[] content, boolean attachment) {
    this.writeFile(fileName, new ByteArrayInputStream(content), true);
  }

  /**
   * @param fileName    fileName
   * @param inputStream inputStream
   * @param attachment  attachment
   * @return void
   * @title writeFile
   * @description writeFile
   * @author BiJi'an
   * @date 2023-10-02 00:57
   */
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

  /**
   * @param json json
   * @return void
   * @title writeJson
   * @description writeJson
   * @author BiJi'an
   * @date 2023-10-02 00:57
   */
  public void writeJson(String json) {
    Map<String, String> headers = Maps.newHashMap();
    headers.put(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
    this.write(headers, json.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * @param obj obj
   * @return void
   * @title writeJson
   * @description writeJson
   * @author BiJi'an
   * @date 2023-10-02 01:17
   */
  public void writeJson(Object obj) {
    String json = JsonUtils.writeToString(obj, JsonOptions.NO_FAIL);
    this.writeJson(json);
    log.info("respone write json={}", json);

  }

  /**
   * @param code code
   * @param msg  msg
   * @return void
   * @title writeErr
   * @description writeErr
   * @author BiJi'an
   * @date 2023-10-02 01:01
   */
  public void writeErr(int code, String msg, Object data) {
    DefaultResponse<Object> defaultResponse = new DefaultResponse<>();
    defaultResponse.setCode(code);
    defaultResponse.setMsg(msg);
    defaultResponse.setData(data);
    String json = JsonUtils.writeToString(defaultResponse, JsonOptions.NO_FAIL);
    this.writeJson(json);
  }

  /**
   * @param errInfo errInfo
   * @param msg     msg
   * @return void
   * @title writeErr
   * @description writeErr
   * @author BiJi'an
   * @date 2023-10-02 01:02
   */
  public void writeErr(ErrInfo errInfo, String msg, Object data) {
    this.writeErr(errInfo.getCode(), msg, data);

  }


  /**
   * @param headers headers
   * @param content content
   * @return void
   * @title write
   * @description write
   * @author BiJi'an
   * @date 2023-10-02 00:57
   */
  public void write(Map<String, String> headers, byte[] content) {
    try (InputStream inputStream = new ByteArrayInputStream(content)) {
      this.write(headers, inputStream);
    } catch (Exception e) {
      log.error("write error", e);
    }
  }

  /**
   * @param headers     headers
   * @param inputStream inputStream
   * @return void
   * @title write
   * @description write
   * @author BiJi'an
   * @date 2023-10-02 00:57
   */
  public void write(Map<String, String> headers, InputStream inputStream) {
    HttpServletResponse response =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
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

package com.kylinhunter.plat.web.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kylinhunter.plat.web.response.DefaultResponse;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 404 500 异常处理
 * @author BiJi'an
 * @date   2021/7/30
 **/
@Controller
@Slf4j
@ApiIgnore
public class ErrorMessageController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Object error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NOT_FOUND) {
            return new DefaultResponse(404, "NOT_FOUND");
        } else if (status == HttpStatus.FORBIDDEN) {
            return new DefaultResponse(403, "FORBIDDEN");
        } else {
            return new DefaultResponse(500, "Internal Server Error");
        }

    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 获取servlet中的 错误码
     *
     * @param request
     * @return
     */
    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            log.error("INTERNAL_SERVER_ERROR", ex);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}

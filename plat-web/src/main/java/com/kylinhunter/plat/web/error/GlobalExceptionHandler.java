package com.kylinhunter.plat.web.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kylinhunter.plat.commons.util.JsonUtils;
import com.kylinhunter.plat.web.response.DefaultResponse;
import com.kylinhunter.plat.web.response.ResponseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2021/8/1
 **/
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseService responseService;

    /**
     * @param req
     * @param rsp
     * @param globalException
     * @param model
     * @return java.lang.Object
     * @throws
     * @title handler
     * @description
     * @author BiJi'an
     * @date 2021/8/1 4:00 上午
     */
    @ExceptionHandler(value = Exception.class)
    public Object handler(HttpServletRequest req, HttpServletResponse rsp, Exception globalException, Model model) {

        try {
            log.error("global error", globalException);
            DefaultResponse response = responseService.toResponse(WebExceptionConverter.convert(globalException));
            String responseJson = JsonUtils.toString(response, false);
            log.error(req.getRequestURI() + "'s response:" + responseJson);
            responseService.writeJson(responseJson);
        } catch (Exception e) {
            log.error("global_exception_handler_error", e);
        }
        return null;

    }

}
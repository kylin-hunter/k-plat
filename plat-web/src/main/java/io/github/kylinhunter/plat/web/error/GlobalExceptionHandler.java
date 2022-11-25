package io.github.kylinhunter.plat.web.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.kylinhunter.plat.web.response.DefaultResponse;
import io.github.kylinhunter.plat.web.response.ResponseService;
import io.github.kylinhunter.plat.web.response.ResponseWriter;

import io.github.kylinhunter.commons.exception.ExceptionConvertor;
import io.github.kylinhunter.commons.json.JsonOptions;
import io.github.kylinhunter.commons.json.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/1
 **/
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseService responseService;

    private final ResponseWriter responseWriter;


    /**
     * @param req             req
     * @param rsp             rsp
     * @param globalException globalException
     * @param model           model
     * @return java.lang.Object
     * @title handler
     * @description
     * @author BiJi'an
     * @date 2021/8/1 4:00 上午
     */
    @ExceptionHandler(value = Exception.class)
    public Object handler(HttpServletRequest req, HttpServletResponse rsp, Exception globalException, Model model) {

        try {
            log.error("global error", globalException);
            DefaultResponse<?> response = responseService.toResponse(ExceptionConvertor.convert(globalException));
            String responseJson = JsonUtils.writeToString(response, JsonOptions.NO_FAIL);
            log.error(req.getRequestURI() + "'s response:" + responseJson);
            responseWriter.writeJson(responseJson);
        } catch (Exception e) {
            log.error("global_exception_handler_error", e);
        }
        return null;

    }

}
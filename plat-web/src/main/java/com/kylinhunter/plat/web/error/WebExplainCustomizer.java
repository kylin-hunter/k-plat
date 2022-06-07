package com.kylinhunter.plat.web.error;

import java.util.Map;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.Maps;
import com.kylinhunter.plat.commons.exception.explain.ExplainCustomizer;
import com.kylinhunter.plat.commons.exception.explain.ExceptionExplainer;
import com.kylinhunter.plat.commons.exception.explain.ExplainInfo;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;
import com.kylinhunter.plat.web.exception.WebErrInfoCustomizer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/1
 **/
@Slf4j
public class WebExplainCustomizer implements ExplainCustomizer {

    @Override
    public void customize(ExceptionExplainer exceptionExplainer) {
        exceptionExplainer.register(BindException.class, (e) -> {
            Map<String, String> errMsgs = Maps.newHashMap();
            if (e.hasErrors()) {
                for (ObjectError objectError : e.getAllErrors()) {
                    if (objectError instanceof FieldError) {
                        errMsgs.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());

                    }
                }
            }
            return new ExplainInfo(ErrInfos.PARAM, errMsgs.toString());
        });
        exceptionExplainer.register(MethodArgumentNotValidException.class, (e) -> {
            Map<String, String> errMsgs = Maps.newHashMap();
            if (e.getBindingResult().hasErrors()) {
                for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
                    if (objectError instanceof FieldError) {
                        errMsgs.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
                    }
                }
            }
            return new ExplainInfo(ErrInfos.PARAM, errMsgs.toString());
        });

        exceptionExplainer.register(HttpRequestMethodNotSupportedException.class,
                (e) -> new ExplainInfo(WebErrInfoCustomizer.WEB_NOT_SUPPORTED, e.getMessage()));

        exceptionExplainer.register(NoHandlerFoundException.class,
                (e) -> new ExplainInfo(WebErrInfoCustomizer.WEB_NO_HANDLER_FOUND, e.getMessage()));

        exceptionExplainer.register(InvalidFormatException.class,
                (e) -> new ExplainInfo(ErrInfos.FORMAT, "json format" + e.getMessage()));

        exceptionExplainer.register(HttpMessageNotReadableException.class,
                (e) -> new ExplainInfo(ErrInfos.FORMAT, e.getMessage()));
    }
}

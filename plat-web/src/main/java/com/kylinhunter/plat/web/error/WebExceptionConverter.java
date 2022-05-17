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
import com.kylinhunter.plat.commons.exception.common.KRuntimeException;
import com.kylinhunter.plat.commons.exception.explain.ExceptionConverter;
import com.kylinhunter.plat.commons.exception.explain.ExceptionExplain;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;
import com.kylinhunter.plat.web.exception.WebErrInfos;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/1
 **/
@Slf4j
public class WebExceptionConverter {
    private static ExceptionConverter exceptionConverter = new ExceptionConverter();

    static {
        exceptionConverter.getExceptionExplainer().register(BindException.class, (e) -> {
            Map<String, String> errMsgs = Maps.newHashMap();
            if (e.hasErrors()) {
                for (ObjectError objectError : e.getAllErrors()) {
                    if (objectError instanceof FieldError) {
                        errMsgs.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());

                    }
                }
            }
            return new ExceptionExplain(ErrInfos.PARAM, errMsgs.toString());
        });
        exceptionConverter.getExceptionExplainer().register(MethodArgumentNotValidException.class, (e) -> {
            Map<String, String> errMsgs = Maps.newHashMap();
            if (e.getBindingResult().hasErrors()) {
                for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
                    if (objectError instanceof FieldError) {
                        errMsgs.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
                    }
                }
            }
            return new ExceptionExplain(ErrInfos.PARAM, errMsgs.toString());
        });

        exceptionConverter.getExceptionExplainer().register(HttpRequestMethodNotSupportedException.class, (e) -> {

            return new ExceptionExplain(WebErrInfos.WEB_NOT_SUPPORTED, e.getMessage());
        });

        exceptionConverter.getExceptionExplainer().register(NoHandlerFoundException.class, (e) -> {

            return new ExceptionExplain(WebErrInfos.WEB_NO_HANDLER_FOUND, e.getMessage());
        });

        exceptionConverter.getExceptionExplainer().register(InvalidFormatException.class, (e) -> {
            return new ExceptionExplain(ErrInfos.FORMAT, "json format" + e.getMessage());
        });

        exceptionConverter.getExceptionExplainer().register(HttpMessageNotReadableException.class, (e) -> {
            return new ExceptionExplain(WebErrInfos.FORMAT, e.getMessage());
        });

    }

    public static KRuntimeException convert(Throwable throwable) {
        return exceptionConverter.convert(throwable);
    }
}

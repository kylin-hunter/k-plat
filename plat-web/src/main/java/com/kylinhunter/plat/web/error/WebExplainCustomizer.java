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
import com.kylinhunter.plat.web.exception.WebErrInfoCustomizer;

import io.github.kylinhunter.commons.exception.explain.AbstractExplainerSupplier;
import io.github.kylinhunter.commons.exception.explain.Explainer;
import io.github.kylinhunter.commons.exception.info.ErrInfos;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/1
 **/
@Slf4j
public class WebExplainCustomizer extends AbstractExplainerSupplier {

    @Override
    public void customize() {
        this.createExplain(BindException.class)
                .setExplainer(e -> {
                    Map<String, String> errMsgs = Maps.newHashMap();
                    if (e.hasErrors()) {
                        for (ObjectError objectError : e.getAllErrors()) {
                            if (objectError instanceof FieldError) {
                                errMsgs.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());

                            }
                        }
                    }
                    return new Explainer.ExplainResult(ErrInfos.PARAM, errMsgs.toString());
                });

        this.createExplain(MethodArgumentNotValidException.class)
                .setExplainer((e) -> {
                    Map<String, String> errMsgs = Maps.newHashMap();
                    if (e.getBindingResult().hasErrors()) {
                        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
                            if (objectError instanceof FieldError) {
                                errMsgs.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
                            }
                        }
                    }
                    return new Explainer.ExplainResult(ErrInfos.PARAM, errMsgs.toString());
                });

        this.createExplain(HttpRequestMethodNotSupportedException.class)
                .setExplainer(
                        (e) -> new Explainer.ExplainResult(WebErrInfoCustomizer.WEB_NOT_SUPPORTED, e.getMessage()));

        this.createExplain(NoHandlerFoundException.class)
                .setExplainer(
                        (e) -> new Explainer.ExplainResult(WebErrInfoCustomizer.WEB_NO_HANDLER_FOUND, e.getMessage()));

        this.createExplain(InvalidFormatException.class)
                .setExplainer(
                        (e) -> new Explainer.ExplainResult(ErrInfos.FORMAT, e.getMessage()));

        this.createExplain(HttpMessageNotReadableException.class)
                .setExplainer(
                        (e) -> new Explainer.ExplainResult(ErrInfos.FORMAT, e.getMessage()));

    }
}

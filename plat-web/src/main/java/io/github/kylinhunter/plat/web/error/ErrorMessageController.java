package io.github.kylinhunter.plat.web.error;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.kylinhunter.plat.web.response.DefaultResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description 404 500 异常处理
 * @date 2021/7/30
 **/
@Slf4j
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorMessageController extends BasicErrorController {

    public ErrorMessageController(ErrorAttributes errorAttributes,
                                  ErrorProperties errorProperties,
                                  List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @RequestMapping
    @Override
    public ResponseEntity error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        DefaultResponse<?> defaultResponse;
        if (status == HttpStatus.NOT_FOUND) {
            defaultResponse = new DefaultResponse<>(404, "NOT_FOUND");
        } else if (status == HttpStatus.FORBIDDEN) {
            defaultResponse = new DefaultResponse<>(403, "FORBIDDEN");
        } else {
            defaultResponse = new DefaultResponse<>(500, "Internal Server Error");
        }
        return new ResponseEntity<>(defaultResponse, status);
    }

}

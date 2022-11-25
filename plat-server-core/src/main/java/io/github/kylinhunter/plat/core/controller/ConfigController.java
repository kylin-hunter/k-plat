package io.github.kylinhunter.plat.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

/**
 * @author BiJi'an
 * @description
 * curl -X POST 'http://127.0.0.1:8848/nacos/v1/cs/configs' \
 * -d "type=yaml&dataId=plat-server-core.yaml&group=DEFAULT_GROUP\
 * &content=test:
 *   test1: true
 *   test2: true"
 * @date 2022-06-03 23:14
 **/
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${test.test1:false}")
    private String test1;

    @Value("${test.test2:false}")
    private String test2;

    @RequestMapping("/get")
    @ResponseBody
    public List<String> get() {
        return Lists.newArrayList(test1, test2);
    }
}
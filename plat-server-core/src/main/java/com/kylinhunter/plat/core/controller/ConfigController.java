package com.kylinhunter.plat.core.controller;

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
 * @date 2022-06-03 23:14
 **/
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${test1:false}")
    private String test1;

    @Value("${test2:false}")
    private String test2;

    @RequestMapping("/get")
    @ResponseBody
    public List<String> get() {
        return Lists.newArrayList(test1, test2);
    }
}
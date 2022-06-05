package com.kylinhunter.plat.generator.auto.core.engine;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.kylinhunter.plat.generator.auto.core.configuration.CodeContext;

import jodd.util.StringPool;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description Velocity 模板引擎实现文件输出
 * @date 2021/8/4
 **/
@Slf4j
public class VelocityTemplateEngine extends AbstractTemplateEngine {

    private static final String DOT_VM = ".vm";
    private static final String VM_LOAD_PATH_KEY = "resource.loader.file.class";
    private static final String VM_LOAD_PATH_VALUE =
            "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    private VelocityEngine velocityEngine;

    @Override
    public VelocityTemplateEngine init(CodeContext CodeContext) {
        super.init(CodeContext);
        if (null == velocityEngine) {
            Properties p = new Properties();
            p.setProperty(VM_LOAD_PATH_KEY, VM_LOAD_PATH_VALUE);
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, StringPool.EMPTY);
            p.setProperty(Velocity.ENCODING_DEFAULT, StringPool.UTF_8);
            p.setProperty(Velocity.INPUT_ENCODING, StringPool.UTF_8);
            //            p.setProperty("resource.loader.file.unicode", StringPool.TRUE);
            velocityEngine = new VelocityEngine(p);
        }
        return this;
    }

    @Override
    public void writer(Map<String, Object> context, String templatePath, Path path) throws Exception {
        Template template = velocityEngine.getTemplate(templatePath, StringPool.UTF_8);
        try (FileOutputStream fos = new FileOutputStream(path.toFile());
             OutputStreamWriter ow = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(ow)) {
            template.merge(new VelocityContext(context), writer);
        }
        log.info("模板:" + templatePath + "==>  文件:" + path);
    }

    @Override
    public String pathTemplate(String path) {
        if (null == path || path.contains(DOT_VM)) {
            return path;
        }
        return path + DOT_VM;
    }
}

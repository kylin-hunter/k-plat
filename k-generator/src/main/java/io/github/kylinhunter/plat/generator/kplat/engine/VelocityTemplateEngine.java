/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.generator.kplat.engine;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import io.github.kylinhunter.plat.generator.kplat.configuration.CodeContext;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * @author BiJi'an
 * @description Velocity 模板引擎实现文件输出
 * @date 2021/8/4
 */
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

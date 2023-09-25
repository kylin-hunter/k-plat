package io.github.kylinhunter.plat.web.init;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;

/**
 * @author BiJi'an
 * @description
 * @date 2023-09-25 15:29
 */
public class EnvHelper {

  /**
   * @param configurableEnvironment configurableEnvironment
   * @return void
   * @title printEnv
   * @description printEnv
   * @author BiJi'an
   * @date 2023-09-23 15:32
   */
  public static void printEnv(ConfigurableEnvironment configurableEnvironment) {
    MutablePropertySources propSrcs = configurableEnvironment.getPropertySources();
    // 获取所有配置
    Map<String, String> props =
        StreamSupport.stream(propSrcs.spliterator(), false)
            .filter(ps -> ps instanceof EnumerablePropertySource)
            .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toMap(Function.identity(), configurableEnvironment::getProperty));

    // key 和 value 之间的最小间隙
    int interval = 20;
    int max =
        props.keySet().stream().max(Comparator.comparingInt(String::length)).orElse("").length();

    // 打印
    props.keySet().stream()
        .sorted()
        .forEach(
            k -> {
              int i = max - k.length() + interval;
              String join = String.join("", Collections.nCopies(i, " "));
              System.out.println(String.format("%s%s%s", k, join, props.get(k)));
            });
  }

}
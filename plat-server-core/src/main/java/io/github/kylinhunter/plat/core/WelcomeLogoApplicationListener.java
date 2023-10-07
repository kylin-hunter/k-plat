package io.github.kylinhunter.plat.core;

import io.github.kylinhunter.plat.web.config.AppConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.SpringVersion;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 启动logo
 * @author: BiJi'an
 * @create: 2021/7/14
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class WelcomeLogoApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final AtomicBoolean ATOMIC_BOOLEAN = new AtomicBoolean(false);

    private final AppConfig appConfig;

    @Override
    public void onApplicationEvent(@Nonnull ApplicationReadyEvent event) {
        if (ATOMIC_BOOLEAN.compareAndSet(false, true)) {
            String bannerText = null;
            try {
                bannerText = buildBannerText();
            } catch (Exception e) {
                log.error("build banner text error ", e);
            }

            if (log.isInfoEnabled()) {
                log.info(bannerText);
            } else {
                System.out.print(bannerText);
            }

        }
    }

    String buildBannerText() throws IOException {
        try (InputStream inputStream = new ClassPathResource("/app-banner.txt").getInputStream()) {
            StringBuilder bannerTextBuilder = new StringBuilder();
            bannerTextBuilder
                    .append(SystemUtils.LINE_SEPARATOR)
                    .append(SystemUtils.LINE_SEPARATOR);

            List<String> lines = IOUtils.readLines(inputStream, Charsets.UTF_8);
            lines.forEach(line -> bannerTextBuilder.append(line).append(SystemUtils.LINE_SEPARATOR));
            bannerTextBuilder
                    .append(SystemUtils.LINE_SEPARATOR)
                    .append(" :: Spring Version::")
                    .append("( ")
                    .append(SpringVersion.getVersion())
                    .append(" )")
                    .append(SystemUtils.LINE_SEPARATOR)
                    .append(" :: Spring Boot Version::")
                    .append("( ")
                    .append(SpringBootVersion.getVersion())
                    .append(" )")
                    .append(SystemUtils.LINE_SEPARATOR)
                    .append(" :: APP Dev Version::")
                    .append("( ")
                    .append(appConfig.getDevVersion())
                    .append(" )")
                    .append(SystemUtils.LINE_SEPARATOR)
                    .append(" :: APP Product Version::")
                    .append("( ")
                    .append(appConfig.getProductVersion())
                    .append(" )")
                    .append(SystemUtils.LINE_SEPARATOR)
                    .append(SystemUtils.LINE_SEPARATOR);

            return bannerTextBuilder.toString();
        }

    }
}

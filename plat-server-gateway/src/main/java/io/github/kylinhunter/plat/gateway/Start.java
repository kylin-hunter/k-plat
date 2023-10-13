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
package io.github.kylinhunter.plat.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 00:13
 *     <p>-Dcsp.sentinel.app.type=1 -Dproject.name=plat-server-gateway
 *     -Dcsp.sentinel.dashboard.server=localhost:8080
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"io.github.kylinhunter.plat"})
// @MapperScan("io.github.kylinhunter.plat.gateway.dao")
public class Start {

  public static void main(String[] args) {
    System.setProperty("csp.sentinel.app.type", "1");
    System.setProperty("project.name", "plat-server-gateway");
    SpringApplication.run(Start.class, args);
  }
}

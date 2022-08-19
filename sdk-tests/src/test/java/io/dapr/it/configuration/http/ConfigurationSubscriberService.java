/*
 * Copyright 2022 The Dapr Authors
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
limitations under the License.
*/

package io.dapr.it.configuration.http;

import org.springframework.boot.SpringApplication;

/**
 * Service for ConfigurationSubscriber.
 * dapr run --components-path ./components/configuration --app-id configsubscriber --app-port 3000 -- \
 * java -jar target/dapr-java-sdk-examples-exec.jar io.dapr.examples.configuration.http.ConfigurationSubscriber -p 3000
 */
public class ConfigurationSubscriberService {

  public static final String SUCCESS_MESSAGE = "dapr initialized. Status: Running";

  /**
   * This is entry point for Configuration Subscriber service.
   * @param args Arguments for main
   * @throws Exception Throws Exception
   */
  public static void main(String[] args) throws Exception {
    int port = Integer.parseInt(args[0]);

    System.out.printf("Service starting on port %d ...\n", port);

    // Start Dapr's callback endpoint.
    start(port);
  }

  /**
   * Starts Dapr's callback in a given port.
   *
   * @param port Port to listen to.
   */
  private static void start(int port) {
    SpringApplication app = new SpringApplication(ConfigurationSubscriberService.class);
    app.run(String.format("--server.port=%d", port));
  }
}

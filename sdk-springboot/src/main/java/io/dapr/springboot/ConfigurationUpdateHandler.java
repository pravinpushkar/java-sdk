/*
 * Copyright 2021 The Dapr Authors
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

package io.dapr.springboot;

import io.dapr.client.domain.SubscribeConfigurationResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ConfigurationUpdateHandler {
  /**
   * The singleton instance.
   */
  private static volatile ConfigurationUpdateHandler instance;

  /**
   * Map of Store name to BiConsumer of Store name and {@link SubscribeConfigurationResponse}.
   */
  private final Map<String, BiConsumer<String, SubscribeConfigurationResponse>>
      configurationChangeHandlers = Collections.synchronizedMap(new HashMap<>());

  /**
   * Private constructor to make this singleton.
   */
  private ConfigurationUpdateHandler() {
  }

  /**
   * Returns an DaprRuntime object.
   *
   * @return An DaprRuntime object.
   */
  public static ConfigurationUpdateHandler getInstance() {
    if (instance == null) {
      synchronized (ConfigurationUpdateHandler.class) {
        if (instance == null) {
          instance = new ConfigurationUpdateHandler();
        }
      }
    }

    return instance;
  }

  /**
   * Method to Register different configuration change handlers.
   * @param store Name of the configuration store
   * @param handler BiConsumer handler to be called when configurations are modified for this store.
   */
  public void registerConfigurationChangeHandler(
      String store, BiConsumer<String,
      SubscribeConfigurationResponse> handler) {
    this.configurationChangeHandlers.put(store, handler);
  }

  /**
   * Method to call the BiConsumer handler registered for teh given store name.
   * @param store Name of the configuration store
   * @param resp {@link SubscribeConfigurationResponse}
   */
  void handleConfigurationChange(String store, SubscribeConfigurationResponse resp) {
    BiConsumer<String, SubscribeConfigurationResponse> handler = this.configurationChangeHandlers.get(store);
    handler.accept(store, resp);
  }
}

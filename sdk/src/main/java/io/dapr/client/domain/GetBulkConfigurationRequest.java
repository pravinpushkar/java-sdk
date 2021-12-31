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

package io.dapr.client.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetBulkConfigurationRequest {
  private final String storeName;
  private final List<String> keys;
  private Map<String, String> metadata;

  public GetBulkConfigurationRequest(String storeName, List<String> keys) {
    this.storeName = storeName;
    this.keys = keys == null ? Collections.unmodifiableList(new ArrayList<>()) : Collections.unmodifiableList(keys);
  }

  public GetBulkConfigurationRequest setMetadata(Map<String, String> metadata) {
    this.metadata = metadata == null ? null : Collections.unmodifiableMap(metadata);
    return this;
  }

  public String getStoreName() {
    return storeName;
  }

  public List<String> getKeys() {
    return keys;
  }

  public Map<String, String> getMetadata() {
    return metadata;
  }
}

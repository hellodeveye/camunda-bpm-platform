/*
 * Copyright © 2013-2018 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.core.variable.mapping.value;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.camunda.bpm.engine.delegate.VariableScope;

/**
 * @author Daniel Meyer
 *
 */
public class MapValueProvider implements ParameterValueProvider {

  protected TreeMap<ParameterValueProvider, ParameterValueProvider> providerMap;

  public MapValueProvider(TreeMap<ParameterValueProvider, ParameterValueProvider> providerMap) {
    this.providerMap = providerMap;
  }

  public Object getValue(VariableScope variableScope) {
    Map<Object, Object> valueMap = new TreeMap<Object, Object>();
    for (Entry<ParameterValueProvider, ParameterValueProvider> entry : providerMap.entrySet()) {
      valueMap.put(entry.getKey().getValue(variableScope), entry.getValue().getValue(variableScope));
    }
    return valueMap;
  }

  public TreeMap<ParameterValueProvider, ParameterValueProvider> getProviderMap() {
    return providerMap;
  }

  public void setProviderMap(TreeMap<ParameterValueProvider, ParameterValueProvider> providerMap) {
    this.providerMap = providerMap;
  }

}
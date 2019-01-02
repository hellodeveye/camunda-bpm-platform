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
package org.camunda.bpm.container.impl.metadata.spi;

import java.util.List;

/**
 * <p>Java API representation of the bpm-platform.xml file.</p> 
 * 
 * @author Daniel Meyer
 *
 */
public interface BpmPlatformXml {
  
  /**
   * @return the {@link JobExecutorXml} configuration of the JobExecutor. 
   */
  public JobExecutorXml getJobExecutor();
  
  /**
   * @return A {@link List} of {@link ProcessEngineXml} Metadata Items representing process engine configurations. 
   */
  public List<ProcessEngineXml> getProcessEngines();
  
}
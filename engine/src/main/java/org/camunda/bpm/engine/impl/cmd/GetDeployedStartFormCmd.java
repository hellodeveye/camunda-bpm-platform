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
package org.camunda.bpm.engine.impl.cmd;

import java.util.concurrent.Callable;

import org.camunda.bpm.engine.BadUserRequestException;
import org.camunda.bpm.engine.form.FormData;
import org.camunda.bpm.engine.impl.cfg.CommandChecker;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.deploy.cache.DeploymentCache;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.util.EnsureUtil;

/**
 * 
 * @author Anna Pazola
 *
 */
public class GetDeployedStartFormCmd extends AbstractGetDeployedFormCmd {

  protected String processDefinitionId;

  public GetDeployedStartFormCmd(String processDefinitionId) {
    EnsureUtil.ensureNotNull(BadUserRequestException.class, "Process definition id cannot be null", "processDefinitionId", processDefinitionId);
    this.processDefinitionId = processDefinitionId;
  }

  @Override
  protected FormData getFormData(final CommandContext commandContext) {
    return commandContext.runWithoutAuthorization(new Callable<FormData>() {
      @Override
      public FormData call() throws Exception {
        return new GetStartFormCmd(processDefinitionId).execute(commandContext);
      }
    });
  }

  @Override
  protected void checkAuthorization(CommandContext commandContext) {
    ProcessEngineConfigurationImpl processEngineConfiguration = Context.getProcessEngineConfiguration();
    DeploymentCache deploymentCache = processEngineConfiguration.getDeploymentCache();
    ProcessDefinitionEntity processDefinition = deploymentCache.findDeployedProcessDefinitionById(processDefinitionId);
    for (CommandChecker checker : commandContext.getProcessEngineConfiguration().getCommandCheckers()) {
      checker.checkReadProcessDefinition(processDefinition);
    }
  }
}
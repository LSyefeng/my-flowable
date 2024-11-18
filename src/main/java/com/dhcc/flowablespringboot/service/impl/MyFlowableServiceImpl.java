package com.dhcc.flowablespringboot.service.impl;

import com.dhcc.flowablespringboot.common.R;
import com.dhcc.flowablespringboot.service.MyFlowableService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MyFlowableServiceImpl implements MyFlowableService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public R deploy(String processFileName) {
        R result = new R();
        try {
            Deployment deploy = repositoryService.createDeployment().addClasspathResource(processFileName).deploy();
            Map<String, Object> data = new HashMap<>();
            data.put("processId", deploy.getId());
            data.put("processName", deploy.getName());
            result.setCode(1);
            result.setMessage("部署流程成功");
            result.setData(data);
        } catch (Exception e) {
            result.setCode(-1);
            result.setMessage("部署流程异常：" + e);
        }
        return result;
    }

    @Override
    public R start(String processKey) {
        R result = new R();
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey);
            Map<String, Object> data = new HashMap<>();
            data.put("processInstId", processInstance.getId());
            result.setCode(1);
            result.setMessage("发起流程成功");
            result.setData(data);
        } catch (Exception e) {
            result.setCode(-1);
            result.setMessage("发起流程异常：" + e);
        }
        return result;
    }

    @Override
    public R executeTask(String taskId) {
        R result = new R();
        try {
            taskService.complete(taskId);
            result.setCode(1);
            result.setMessage("完成任务成功");
        } catch (Exception e) {
            result.setCode(-1);
            result.setMessage("完成任务异常：" + e);
        }
        return result;
    }
}

package com.dhcc.flowablespringboot.service.impl;

import com.dhcc.flowablespringboot.common.R;
import com.dhcc.flowablespringboot.service.MyFlowableService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MyFlowableServiceImpl implements MyFlowableService {

    @Autowired
    private RepositoryService repositoryService;

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
}

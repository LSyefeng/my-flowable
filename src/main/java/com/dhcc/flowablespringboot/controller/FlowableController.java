package com.dhcc.flowablespringboot.controller;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: leisheng
 * @date: 2023/2/13 21:31
 */
@RestController
public class FlowableController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/deploy/{processFileName}")
    public String deploy(@PathVariable("processFileName") String processFileName) {
        Deployment deploy = repositoryService.createDeployment().addClasspathResource(processFileName).deploy();
        System.out.println("deploy.getId() = " + deploy.getId());
        System.out.println("deploy.getName() = " + deploy.getName());
        return "部署流程成功";
    }
}

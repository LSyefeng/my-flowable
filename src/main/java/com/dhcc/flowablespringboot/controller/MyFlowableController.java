package com.dhcc.flowablespringboot.controller;

import com.dhcc.flowablespringboot.common.R;
import com.dhcc.flowablespringboot.service.MyFlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: leisheng
 * @date: 2023/2/13 21:31
 */
@RestController
@RequestMapping("/flow")
public class MyFlowableController {

    @Autowired
    private MyFlowableService myFlowableService;

    @GetMapping("/deploy/{processFileName}")
    public R deploy(@PathVariable("processFileName") String processFileName) {
        return myFlowableService.deploy(processFileName);
    }

    @GetMapping("/start/{processKey}")
    public R start(@PathVariable("processKey") String processKey) {
        return myFlowableService.start(processKey);
    }

    @GetMapping("/executeTask/{taskId}")
    public R executeTask(@PathVariable("taskId") String taskId) {
        return myFlowableService.executeTask(taskId);
    }
}

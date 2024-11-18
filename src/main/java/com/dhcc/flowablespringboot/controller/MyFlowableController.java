package com.dhcc.flowablespringboot.controller;

import com.dhcc.flowablespringboot.common.R;
import com.dhcc.flowablespringboot.service.MyFlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: leisheng
 * @date: 2023/2/13 21:31
 */
@RestController
public class MyFlowableController {

    @Autowired
    private MyFlowableService myFlowableService;

    @GetMapping("/deploy/{processFileName}")
    public R deploy(@PathVariable("processFileName") String processFileName) {
        return myFlowableService.deploy(processFileName);
    }
}
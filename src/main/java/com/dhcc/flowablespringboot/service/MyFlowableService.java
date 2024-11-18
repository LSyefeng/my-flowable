package com.dhcc.flowablespringboot.service;

import com.dhcc.flowablespringboot.common.R;

public interface MyFlowableService {

    R deploy(String processFileName);

    R start(String processKey);

    R executeTask(String taskId);
}

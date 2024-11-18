package com.dhcc.flowablespringboot.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Date;

/**
 * @projectName: FlowableSpringBoot
 * @package: com.dhcc.flowablespringboot.delegate
 * @className: MyOneJavaDelelate
 * @author: leisheng
 * @description: TODO
 * @date: 2023/2/13 21:24
 * @version: 1.0
 */
public class MyOneJavaDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("MyOneJavaDelegate --- 111 --- 执行了 --- " + new Date());
        // throw new BpmnError("abcd");
    }
}

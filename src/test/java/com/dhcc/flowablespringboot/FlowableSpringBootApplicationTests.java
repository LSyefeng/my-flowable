package com.dhcc.flowablespringboot;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FlowableSpringBootApplicationTests {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 流程部署
     */
    @Test
    void testDeploy() {
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("请假流程.bpmn20.xml").name("请假流程").deploy();
        System.out.println("deployment.getId() = " + deployment.getId());
        System.out.println("deployment.getName() = " + deployment.getName());
    }

    @Test
    void startFlow() throws InterruptedException {
        // runtimeService.startProcessInstanceByMessage("第二个消息");
        Map<String, Object> map = new HashMap<>();
        map.put("assignee1", "zhangsan");
        map.put("assignee2", "lisi");
        runtimeService.startProcessInstanceById("holiday:1:a36ed39a-f7d4-11ed-8db3-366f24106823", map);
    }

    @Test
    void signalGolbal() {
        runtimeService.signalEventReceived("signal01");
        Map<String, Object> map = new HashMap<>();
        map.put("assignee2", "lisi");
        runtimeService.setVariables("677c0885-f7d5-11ed-81e8-366f24106823", map);
    }

    @Test
    void recevedMsg() {
//        Execution execution = runtimeService.createExecutionQuery().processInstanceId("xiaoxi-zhongjian:1:742f2260-c0ab-11ed-83e4-366f24106823").singleResult();
//        String executionId = execution.getId();
        runtimeService.messageEventReceived("第三个消息", "a86241a4-c0ad-11ed-bf80-366f24106823");
    }

    @Test
    void completeTask() {
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionId("holiday:1:a36ed39a-f7d4-11ed-8db3-366f24106823")
                .taskAssignee("lisi")
                .list();
        if (taskList != null) {
            for (Task task : taskList) {
                taskService.complete(task.getId());
            }
        }
    }

    @Test
    void deleteProcess() {
        repositoryService.deleteDeployment("holiday:2:558f87bc-8a80-11ed-8c49-366f24106823", true);
    }

    @Test
    void backProcess() {
        List<String> newActivityIds = new ArrayList<>();
        newActivityIds.add("usertask2");
        newActivityIds.add("usertask3");
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId("35b1658c-ccad-11ed-bfe1-366f24106823")
                // .moveActivityIdsToSingleActivityId(newActivityIds, "usertask1")
                .moveSingleActivityIdToActivityIds("usertask5", newActivityIds)
                // .moveExecutionToActivityId("e2e2781c-ccab-11ed-897a-366f24106823", "usertask3")
                .changeState();
    }
}

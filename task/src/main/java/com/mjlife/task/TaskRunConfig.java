package com.mjlife.task;

import com.mjlife.common.util.Constans;
import com.mjlife.common.util.SpringContextUtil;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@EnableScheduling
public class TaskRunConfig implements SchedulingConfigurer {
    public static Map<String, String> crons = new ConcurrentHashMap<String, String>();
    public static Map<String, Class> tasks = new HashMap<String, Class>();
    public static List<String> taskNames = new ArrayList<String>();

    static {
        taskNames.add(Constans.TaskName.COVACS);
        crons.put(Constans.TaskName.COVACS, "0 0/5 0 1/1 * ?");
        tasks.put(Constans.TaskName.COVACS, CovacsSignTask.class);
    }

    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        for (String taskName : taskNames) {
            addTask(scheduledTaskRegistrar, taskName);
        }
    }

    private void addTask(ScheduledTaskRegistrar scheduledTaskRegistrar, final String taskName) {
        Runnable task = (Runnable) SpringContextUtil.getBean(tasks.get(taskName));
        scheduledTaskRegistrar.addTriggerTask(task, new Trigger() {
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(crons.get(taskName));
                return cronTrigger.nextExecutionTime(triggerContext);
            }
        });
    }
}

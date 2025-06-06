/*
 * Copyright (c) 2021 - present Jiahang Li All rights reserved.
 *
 *   https://ops.orionsec.cn
 *
 * Members:
 *   Jiahang Li - ljh1553488six@139.com - author
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.orionsec.ops.runner;

import cn.orionsec.ops.constant.Const;
import cn.orionsec.ops.constant.common.EnableType;
import cn.orionsec.ops.constant.scheduler.SchedulerTaskMachineStatus;
import cn.orionsec.ops.constant.scheduler.SchedulerTaskStatus;
import cn.orionsec.ops.constant.system.SystemEnvAttr;
import cn.orionsec.ops.dao.SchedulerTaskDAO;
import cn.orionsec.ops.dao.SchedulerTaskMachineRecordDAO;
import cn.orionsec.ops.dao.SchedulerTaskRecordDAO;
import cn.orionsec.ops.entity.domain.SchedulerTaskDO;
import cn.orionsec.ops.entity.domain.SchedulerTaskMachineRecordDO;
import cn.orionsec.ops.entity.domain.SchedulerTaskRecordDO;
import cn.orionsec.ops.task.TaskRegister;
import cn.orionsec.ops.task.TaskType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 调度任务初始化
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/2/24 17:58
 */
@Component
@Order(3300)
@Slf4j
public class SchedulerTaskRunner implements CommandLineRunner {

    @Resource
    private SchedulerTaskDAO schedulerTaskDAO;

    @Resource
    private SchedulerTaskRecordDAO schedulerTaskRecordDAO;

    @Resource
    private SchedulerTaskMachineRecordDAO schedulerTaskMachineRecordDAO;

    @Resource
    private TaskRegister taskRegister;

    @Override
    public void run(String... args) {
        log.info("调度任务初始化-开始");
        // 更新开始状态
        this.updateTaskStatus();
        // 更新执行记录状态
        this.updateTaskRecordStatus();
        // 自动恢复
        this.autoResumeTask();
        log.info("调度任务初始化-结束");
    }

    /**
     * 更新任务状态
     */
    private void updateTaskStatus() {
        Boolean autoResume = EnableType.of(SystemEnvAttr.RESUME_ENABLE_SCHEDULER_TASK.getValue()).getValue();
        List<SchedulerTaskDO> tasks = schedulerTaskDAO.selectList(null);
        for (SchedulerTaskDO task : tasks) {
            SchedulerTaskDO update = new SchedulerTaskDO();
            update.setId(task.getId());
            if (!autoResume || !Const.ENABLE.equals(task.getEnableStatus())) {
                update.setEnableStatus(Const.DISABLE);
            }
            update.setUpdateTime(new Date());
            // 最近状态
            SchedulerTaskStatus status = SchedulerTaskStatus.of(task.getLatelyStatus());
            switch (status) {
                case WAIT:
                case RUNNABLE:
                    update.setLatelyStatus(SchedulerTaskStatus.TERMINATED.getStatus());
                    break;
                default:
                    break;
            }
            // 更新
            schedulerTaskDAO.updateById(update);
        }
    }

    /**
     * 更新任务执行状态
     */
    private void updateTaskRecordStatus() {
        // 重置任务明细
        Wrapper<SchedulerTaskRecordDO> recordWrapper = new LambdaQueryWrapper<SchedulerTaskRecordDO>()
                .in(SchedulerTaskRecordDO::getTaskStatus, SchedulerTaskStatus.WAIT.getStatus(), SchedulerTaskStatus.RUNNABLE.getStatus());
        SchedulerTaskRecordDO updateRecord = new SchedulerTaskRecordDO();
        updateRecord.setTaskStatus(SchedulerTaskStatus.TERMINATED.getStatus());
        updateRecord.setUpdateTime(new Date());
        schedulerTaskRecordDAO.update(updateRecord, recordWrapper);

        // 重置机器明细
        Wrapper<SchedulerTaskMachineRecordDO> machineWrapper = new LambdaQueryWrapper<SchedulerTaskMachineRecordDO>()
                .in(SchedulerTaskMachineRecordDO::getExecStatus, SchedulerTaskMachineStatus.WAIT.getStatus(), SchedulerTaskMachineStatus.RUNNABLE.getStatus());
        SchedulerTaskMachineRecordDO updateMachine = new SchedulerTaskMachineRecordDO();
        updateMachine.setExecStatus(SchedulerTaskMachineStatus.TERMINATED.getStatus());
        updateMachine.setUpdateTime(new Date());
        schedulerTaskMachineRecordDAO.update(updateMachine, machineWrapper);

    }

    /**
     * 自动恢复任务
     */
    private void autoResumeTask() {
        Boolean autoResume = EnableType.of(SystemEnvAttr.RESUME_ENABLE_SCHEDULER_TASK.getValue()).getValue();
        if (!autoResume) {
            return;
        }
        // 查询启用的定时任务
        LambdaQueryWrapper<SchedulerTaskDO> wrapper = new LambdaQueryWrapper<SchedulerTaskDO>()
                .eq(SchedulerTaskDO::getEnableStatus, Const.ENABLE);
        List<SchedulerTaskDO> taskList = schedulerTaskDAO.selectList(wrapper);
        // 启用
        for (SchedulerTaskDO task : taskList) {
            Long id = task.getId();
            log.info("调度任务自动恢复 id: {}, name: {}", id, task.getTaskName());
            taskRegister.submit(TaskType.SCHEDULER_TASK, task.getExpression(), id);
        }
    }

}

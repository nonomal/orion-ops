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
package cn.orionsec.ops.service.impl;

import cn.orionsec.ops.dao.SchedulerTaskMachineDAO;
import cn.orionsec.ops.entity.domain.SchedulerTaskMachineDO;
import cn.orionsec.ops.service.api.SchedulerTaskMachineService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 调度任务机器 服务实现类
 * </p>
 *
 * @author Jiahang Li
 * @since 2022-02-22
 */
@Service("schedulerTaskMachineService")
public class SchedulerTaskMachineServiceImpl implements SchedulerTaskMachineService {

    @Resource
    private SchedulerTaskMachineDAO schedulerTaskMachineDAO;

    @Override
    public List<SchedulerTaskMachineDO> selectByTaskId(Long taskId) {
        LambdaQueryWrapper<SchedulerTaskMachineDO> wrapper = new LambdaQueryWrapper<SchedulerTaskMachineDO>()
                .eq(SchedulerTaskMachineDO::getTaskId, taskId);
        return schedulerTaskMachineDAO.selectList(wrapper);
    }

    @Override
    public Integer deleteByTaskId(Long taskId) {
        LambdaQueryWrapper<SchedulerTaskMachineDO> wrapper = new LambdaQueryWrapper<SchedulerTaskMachineDO>()
                .eq(SchedulerTaskMachineDO::getTaskId, taskId);
        return schedulerTaskMachineDAO.delete(wrapper);
    }

    @Override
    public Integer deleteByMachineIdList(List<Long> machineIdList) {
        LambdaQueryWrapper<SchedulerTaskMachineDO> wrapper = new LambdaQueryWrapper<SchedulerTaskMachineDO>()
                .in(SchedulerTaskMachineDO::getMachineId, machineIdList);
        return schedulerTaskMachineDAO.delete(wrapper);
    }

}

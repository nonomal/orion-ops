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
package cn.orionsec.ops.entity.vo.machine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 机器报警配置响应
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/8/29 15:31
 */
@Data
@ApiModel(value = "机器报警配置响应")
@SuppressWarnings("ALL")
public class MachineAlarmConfigVO {

    /**
     * @see cn.orionsec.ops.constant.machine.MachineAlarmType
     */
    @ApiModelProperty(value = "报警类型 10: cpu使用率 20: 内存使用率")
    private Integer type;

    @ApiModelProperty(value = "报警阈值")
    private Double alarmThreshold;

    @ApiModelProperty(value = "触发报警阈值 次")
    private Integer triggerThreshold;

    @ApiModelProperty(value = "报警通知沉默时间 分")
    private Integer notifySilence;

}

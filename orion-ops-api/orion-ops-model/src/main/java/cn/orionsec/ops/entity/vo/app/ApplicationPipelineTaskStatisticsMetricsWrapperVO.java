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
package cn.orionsec.ops.entity.vo.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 流水线执行统计指标响应
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/5/10 11:04
 */
@Data
@ApiModel(value = "流水线执行统计指标响应")
public class ApplicationPipelineTaskStatisticsMetricsWrapperVO {

    @ApiModelProperty(value = "最近执行指标")
    private ApplicationPipelineTaskStatisticsMetricsVO lately;

    @ApiModelProperty(value = "所有执行指标")
    private ApplicationPipelineTaskStatisticsMetricsVO all;

}
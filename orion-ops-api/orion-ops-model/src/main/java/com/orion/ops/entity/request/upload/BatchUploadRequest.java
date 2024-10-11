/*
 * Copyright (c) 2021 - present Jiahang Li (ops.orionsec.cn ljh1553488six@139.com).
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
package com.orion.ops.entity.request.upload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 批量上传请求
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/5/13 16:42
 */
@Data
@ApiModel(value = "批量上传请求")
public class BatchUploadRequest {

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "远程路径")
    private String remotePath;

    @ApiModelProperty(value = "机器id")
    private List<Long> machineIds;

    @ApiModelProperty(value = "文件名称")
    private List<String> names;

}

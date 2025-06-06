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
package cn.orionsec.ops.constant.app;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 流水香详情状态
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/4/8 9:52
 */
@AllArgsConstructor
@Getter
public enum PipelineDetailStatus {

    /**
     * 未开始
     */
    WAIT(10),

    /**
     * 进行中
     */
    RUNNABLE(20),

    /**
     * 已完成
     */
    FINISH(30),

    /**
     * 执行失败
     */
    FAILURE(40),

    /**
     * 已跳过
     */
    SKIPPED(50),

    /**
     * 已终止
     */
    TERMINATED(60),

    ;

    private final Integer status;

    public static PipelineDetailStatus of(Integer status) {
        if (status == null) {
            return null;
        }
        for (PipelineDetailStatus value : values()) {
            if (value.status.equals(status)) {
                return value;
            }
        }
        return null;
    }

}

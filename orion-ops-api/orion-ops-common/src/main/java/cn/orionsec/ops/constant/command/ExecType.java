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
package cn.orionsec.ops.constant.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 命令执行类型
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/6/4 18:14
 */
@AllArgsConstructor
@Getter
public enum ExecType {

    /**
     * 批量执行
     */
    BATCH_EXEC(10),

    ;

    /**
     * 类型
     */
    private final Integer type;

    public static ExecType of(Integer type) {
        for (ExecType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}

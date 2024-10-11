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
package com.orion.ops.handler.terminal.screen;

import com.alibaba.fastjson.annotation.JSONField;
import com.orion.net.remote.TerminalType;
import com.orion.ops.constant.Const;
import lombok.Data;

/**
 * terminal 录屏环境
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/7/26 18:27
 */
@Data
public class TerminalScreenEnv {

    /**
     * 终端类型
     */
    @JSONField(name = "TERM")
    private String term;

    /**
     * shell 类型
     */
    @JSONField(name = "SHELL")
    private String shell;

    public TerminalScreenEnv() {
        this(TerminalType.XTERM.getType(), Const.DEFAULT_SHELL);
    }

    public TerminalScreenEnv(String term) {
        this(term, Const.DEFAULT_SHELL);
    }

    public TerminalScreenEnv(String term, String shell) {
        this.term = term;
        this.shell = shell;
    }

}

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
package cn.orionsec.ops.utils;

import cn.orionsec.ops.constant.event.EventKeys;

import java.util.Map;

/**
 * 事件日志工具
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/1/22 21:05
 */
public class EventLogUtils {

    private EventLogUtils() {
    }

    /**
     * 移除内部key
     *
     * @param map map
     */
    public static void removeInnerKeys(Map<String, ?> map) {
        map.remove(EventKeys.INNER_USER_ID);
        map.remove(EventKeys.INNER_USER_NAME);
        map.remove(EventKeys.INNER_SAVE);
        map.remove(EventKeys.INNER_TEMPLATE);
    }

}

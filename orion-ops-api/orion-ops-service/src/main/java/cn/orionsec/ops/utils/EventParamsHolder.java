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

import cn.orionsec.kit.lang.define.collect.MutableMap;
import cn.orionsec.kit.lang.utils.collect.Maps;
import cn.orionsec.kit.lang.utils.reflect.BeanMap;
import cn.orionsec.kit.lang.utils.time.Dates;
import cn.orionsec.kit.web.servlet.web.Servlets;
import cn.orionsec.ops.constant.event.EventKeys;
import cn.orionsec.ops.entity.dto.user.UserDTO;
import cn.orionsec.ops.interceptor.LogPrintInterceptor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * 操作日志参数信息
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/1/23 17:24
 */
public class EventParamsHolder {

    /**
     * 参数
     */
    private static final ThreadLocal<MutableMap<String, Object>> PARAMS = ThreadLocal.withInitial(Maps::newMutableLinkedMap);

    public static MutableMap<String, Object> get() {
        return PARAMS.get();
    }

    public static void set(MutableMap<String, Object> user) {
        PARAMS.set(user);
    }

    public static void remove() {
        PARAMS.remove();
    }

    /**
     * 设置参数
     *
     * @param key   key
     * @param value value
     */
    public static void addParam(String key, Object value) {
        PARAMS.get().put(key, value);
    }

    /**
     * 设置参数
     *
     * @param value value
     */
    public static void addParams(Object value) {
        if (value == null) {
            return;
        }
        PARAMS.get().putAll(BeanMap.create(value));
    }

    /**
     * 设置是否保存
     *
     * @param save 是否保存
     */
    public static void setSave(boolean save) {
        PARAMS.get().put(EventKeys.INNER_SAVE, save);
    }

    /**
     * 设置默认参数
     */
    public static void setDefaultEventParams() {
        // 请求时间
        EventParamsHolder.addParam(EventKeys.INNER_REQUEST_TIME, Dates.current());
        // 登录接口为空
        UserDTO user = Currents.getUser();
        if (user != null) {
            EventParamsHolder.addParam(EventKeys.INNER_USER_ID, user.getId());
            EventParamsHolder.addParam(EventKeys.INNER_USER_NAME, user.getUsername());
        }
        // 请求序列
        EventParamsHolder.addParam(EventKeys.INNER_REQUEST_SEQ, LogPrintInterceptor.SEQ_HOLDER.get());
        // 请求信息
        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(s -> (ServletRequestAttributes) s)
                .map(ServletRequestAttributes::getRequest)
                .ifPresent(request -> {
                    EventParamsHolder.addParam(EventKeys.INNER_REQUEST_USER_AGENT, Servlets.getUserAgent(request));
                    EventParamsHolder.addParam(EventKeys.INNER_REQUEST_IP, Servlets.getRemoteAddr(request));
                });
    }

}

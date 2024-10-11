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
package com.orion.ops.interceptor;

import com.orion.lang.utils.Booleans;
import com.orion.lang.utils.Strings;
import com.orion.ops.constant.KeyConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.annotation.Resource;
import java.util.Map;

import static com.orion.ops.utils.WebSockets.getToken;

/**
 * sftp通知拦截器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/6/27 11:21
 */
@Slf4j
@Component
public class FileTransferNotifyInterceptor implements HandshakeInterceptor {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String token = getToken(request);
        String tokenKey = Strings.format(KeyConst.SFTP_SESSION_TOKEN, token);
        boolean access = Booleans.isTrue(redisTemplate.hasKey(tokenKey));
        log.info("sftp通知 尝试建立ws连接开始 token: {}, 结果: {}", token, access);
        return access;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }

}

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
package cn.orionsec.ops.mapping.machine;

import cn.orionsec.kit.lang.utils.convert.TypeStore;
import cn.orionsec.ops.entity.domain.MachineSecretKeyDO;
import cn.orionsec.ops.entity.vo.machine.MachineSecretKeyVO;

/**
 * 机器密钥 对象转换器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/8/10 18:13
 */
public class MachineSecretKeyConversion {

    static {
        TypeStore.STORE.register(MachineSecretKeyDO.class, MachineSecretKeyVO.class, p -> {
            MachineSecretKeyVO vo = new MachineSecretKeyVO();
            vo.setId(p.getId());
            vo.setName(p.getKeyName());
            vo.setPath(p.getSecretKeyPath());
            vo.setDescription(p.getDescription());
            vo.setCreateTime(p.getCreateTime());
            return vo;
        });
    }

}

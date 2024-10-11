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
package com.orion.ops.mapping.machine;

import com.orion.lang.utils.convert.TypeStore;
import com.orion.ops.entity.domain.MachineGroupDO;
import com.orion.ops.entity.vo.machine.MachineGroupTreeVO;

/**
 * 机器分组 对象转换器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/9/23 17:39
 */
public class MachineGroupConversion {

    static {
        TypeStore.STORE.register(MachineGroupDO.class, MachineGroupTreeVO.class, p -> {
            MachineGroupTreeVO vo = new MachineGroupTreeVO();
            vo.setId(p.getId());
            vo.setParentId(p.getParentId());
            vo.setTitle(p.getGroupName());
            vo.setSort(p.getSort());
            return vo;
        });
    }

}

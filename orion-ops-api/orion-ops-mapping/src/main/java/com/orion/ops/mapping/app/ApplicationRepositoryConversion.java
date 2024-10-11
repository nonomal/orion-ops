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
package com.orion.ops.mapping.app;

import com.orion.ext.vcs.git.info.BranchInfo;
import com.orion.ext.vcs.git.info.LogInfo;
import com.orion.lang.utils.convert.TypeStore;
import com.orion.lang.utils.time.Dates;
import com.orion.ops.entity.domain.ApplicationRepositoryDO;
import com.orion.ops.entity.vo.app.ApplicationRepositoryBranchVO;
import com.orion.ops.entity.vo.app.ApplicationRepositoryCommitVO;
import com.orion.ops.entity.vo.app.ApplicationRepositoryVO;

import java.util.Date;
import java.util.Optional;

/**
 * 应用仓库 对象转换器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/8/10 17:59
 */
public class ApplicationRepositoryConversion {

    static {
        TypeStore.STORE.register(ApplicationRepositoryDO.class, ApplicationRepositoryVO.class, p -> {
            ApplicationRepositoryVO vo = new ApplicationRepositoryVO();
            vo.setId(p.getId());
            vo.setName(p.getRepoName());
            vo.setDescription(p.getRepoDescription());
            vo.setType(p.getRepoType());
            vo.setUrl(p.getRepoUrl());
            vo.setUsername(p.getRepoUsername());
            vo.setStatus(p.getRepoStatus());
            vo.setAuthType(p.getRepoAuthType());
            vo.setTokenType(p.getRepoTokenType());
            vo.setCreateTime(p.getCreateTime());
            vo.setUpdateTime(p.getUpdateTime());
            return vo;
        });
    }

    static {
        TypeStore.STORE.register(BranchInfo.class, ApplicationRepositoryBranchVO.class, p -> {
            ApplicationRepositoryBranchVO vo = new ApplicationRepositoryBranchVO();
            vo.setName(p.toString());
            return vo;
        });
    }

    static {
        TypeStore.STORE.register(LogInfo.class, ApplicationRepositoryCommitVO.class, p -> {
            ApplicationRepositoryCommitVO vo = new ApplicationRepositoryCommitVO();
            vo.setId(p.getId());
            vo.setMessage(p.getMessage());
            vo.setName(p.getName());
            Date time = p.getTime();
            vo.setTime(time);
            Optional.ofNullable(time).map(Dates::ago).ifPresent(vo::setTimeAgo);
            return vo;
        });
    }

}

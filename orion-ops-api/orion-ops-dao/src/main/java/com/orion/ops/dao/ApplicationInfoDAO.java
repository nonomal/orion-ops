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
package com.orion.ops.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orion.ops.entity.domain.ApplicationInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 应用表 Mapper 接口
 * </p>
 *
 * @author Jiahang Li
 * @since 2021-07-02
 */
public interface ApplicationInfoDAO extends BaseMapper<ApplicationInfoDO> {

    /**
     * 获取仓库数量
     *
     * @param repoId repoId
     * @return count
     */
    Integer selectRepoCount(@Param("repoId") Long repoId);

    /**
     * 清空仓库
     *
     * @param repoId repoId
     * @return effect
     */
    Integer cleanRepoCount(@Param("repoId") Long repoId);

    /**
     * 查询名称
     *
     * @param id id
     * @return name
     */
    String selectNameById(@Param("id") Long id);

    /**
     * 查询名称
     *
     * @param idList idList
     * @return name
     */
    List<ApplicationInfoDO> selectNameByIdList(@Param("idList") List<Long> idList);

}

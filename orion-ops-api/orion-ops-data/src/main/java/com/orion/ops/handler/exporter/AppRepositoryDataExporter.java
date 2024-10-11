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
package com.orion.ops.handler.exporter;

import com.orion.lang.utils.convert.Converts;
import com.orion.ops.constant.Const;
import com.orion.ops.constant.ExportType;
import com.orion.ops.dao.ApplicationRepositoryDAO;
import com.orion.ops.entity.domain.ApplicationRepositoryDO;
import com.orion.ops.entity.exporter.ApplicationRepositoryExportDTO;
import com.orion.ops.entity.request.data.DataExportRequest;
import com.orion.spring.SpringHolder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 应用仓库 数据导出器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/9/8 18:50
 */
public class AppRepositoryDataExporter extends AbstractDataExporter<ApplicationRepositoryExportDTO> {

    private static final ApplicationRepositoryDAO applicationRepositoryDAO = SpringHolder.getBean(ApplicationRepositoryDAO.class);

    public AppRepositoryDataExporter(DataExportRequest request, HttpServletResponse response) {
        super(ExportType.APP_REPOSITORY, request, response);
    }

    @Override
    protected List<ApplicationRepositoryExportDTO> queryData() {
        // 查询数据
        List<ApplicationRepositoryDO> repoList = applicationRepositoryDAO.selectList(null);
        List<ApplicationRepositoryExportDTO> exportList = Converts.toList(repoList, ApplicationRepositoryExportDTO.class);
        if (!Const.ENABLE.equals(request.getExportPassword())) {
            exportList.forEach(s -> s.setEncryptAuthValue(null));
        }
        return exportList;
    }

}

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
package cn.orionsec.ops.handler.importer.checker;

import cn.orionsec.ops.constant.ImportType;
import cn.orionsec.ops.entity.domain.MachineProxyDO;
import cn.orionsec.ops.entity.importer.MachineProxyImportDTO;
import cn.orionsec.ops.entity.vo.data.DataImportCheckVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * 机器代理 数据检查器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/9/9 16:29
 */
public class MachineProxyDataChecker extends AbstractDataChecker<MachineProxyImportDTO, MachineProxyDO> {

    public MachineProxyDataChecker(Workbook workbook) {
        super(ImportType.MACHINE_PROXY, workbook);
    }

    @Override
    protected DataImportCheckVO checkImportData(List<MachineProxyImportDTO> rows) {
        // 检查数据合法性
        this.validImportRows(rows);
        // 设置导入检查数据
        return this.setImportCheckRows(rows);
    }

}
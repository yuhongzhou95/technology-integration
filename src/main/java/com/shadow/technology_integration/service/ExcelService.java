package com.shadow.technology_integration.service;

import com.shadow.technology_integration.domain.dto.UserQueryDTO;

/**
 * 类描述：Excel服务
 *
 * @author shadow
 * @date 2020/5/11
 */
public interface ExcelService {
    /**
     * 导出
     * @param userQueryDTO
     * @param filename
     */
    void export(UserQueryDTO userQueryDTO,String filename);

    /**
     * 异步导出
     * @param query
     * @param filename
     */
    void asyncExport(UserQueryDTO query, String filename);
}

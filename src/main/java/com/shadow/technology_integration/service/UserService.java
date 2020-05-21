package com.shadow.technology_integration.service;

import com.shadow.technology_integration.domain.common.PageQuery;
import com.shadow.technology_integration.domain.common.PageResult;
import com.shadow.technology_integration.domain.dto.UserDTO;
import com.shadow.technology_integration.domain.dto.UserQueryDTO;

import java.util.List;

/**
 * 类描述：
 *
 * @author shadow
 * @date 2020/4/27
 */
public interface UserService {
    /**
     * 新增
     *
     * @param userDTO
     * @return
     */
    int save(UserDTO userDTO);

    /**
     * 更新
     *
     * @param id
     * @param userDTO
     * @return
     */
    int update(Long id, UserDTO userDTO);

    /**
     * 条件查询列表
     *
     * @param pageQuery
     * @return
     */
    PageResult<List<UserDTO>> listByQuery(PageQuery<UserQueryDTO> pageQuery);
}

package com.shadow.technology_integration.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shadow.technology_integration.domain.common.PageQuery;
import com.shadow.technology_integration.domain.common.PageResult;
import com.shadow.technology_integration.domain.dto.UserDTO;
import com.shadow.technology_integration.domain.dto.UserQueryDTO;
import com.shadow.technology_integration.domain.entity.UserDO;
import com.shadow.technology_integration.domain.vo.UserVO;
import com.shadow.technology_integration.mapper.UserMapper;
import com.shadow.technology_integration.service.UserService;
import com.shadow.technology_integration.util.ValidatorUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类描述：
 *
 * @author shadow
 * @date 2020/4/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        // 浅拷贝：属性名相同才能拷贝
        BeanUtils.copyProperties(userDTO, userDO);
        return userMapper.insert(userDO);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {
        UserDO userDO = new UserDO();
        // 浅拷贝：属性名相同才能拷贝
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setId(id);
        return userMapper.updateById(userDO);
    }

    @Override
    public PageResult<List<UserDTO>> listByQuery(PageQuery<UserQueryDTO> pageQuery) {
        // 手动校验功能
        ValidatorUtils.validate(pageQuery);

        // 参数构造
        Page page = new Page(pageQuery.getPageNo(),
                pageQuery.getPageSize());
        UserDO query = new UserDO();
        BeanUtils.copyProperties(pageQuery.getQuery(), query);
        // TODO 如果属性不一致，需要做特殊处理
        QueryWrapper queryWrapper = new QueryWrapper(query);

        // 查询
        IPage<UserDO> userDOIPage = userMapper.selectPage(page, queryWrapper);

        // 结果解析
        PageResult pageResult = new PageResult();
        pageResult.setPage((int) userDOIPage.getCurrent());
        pageResult.setSize((int) userDOIPage.getSize());
        pageResult.setTotal(userDOIPage.getTotal());
        pageResult.setTotalPage(userDOIPage.getPages());

        // 对数据进行转换
        List<UserDTO> userDTOList = Optional
                .ofNullable(userDOIPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDO -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(userDO, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());

        pageResult.setData(userDTOList);

        return pageResult;
    }
}

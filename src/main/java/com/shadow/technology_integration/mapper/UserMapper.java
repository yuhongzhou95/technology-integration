package com.shadow.technology_integration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shadow.technology_integration.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shadow
 * @date 2020/4/17
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    // 如果需要自定义一些方法
}

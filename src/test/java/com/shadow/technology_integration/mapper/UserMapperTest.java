package com.shadow.technology_integration.mapper;

import com.alibaba.fastjson.JSONObject;
import com.shadow.technology_integration.domain.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectById() {
        UserDO userDO = userMapper.selectById("1220708537638920191");
        log.info("{}", JSONObject.toJSONString(userDO, true));
    }

    @Test
    public void testInsert() {
        UserDO userDO = new UserDO();
        userDO.setUsername("niujinyan");
        userDO.setPhone("13100000000");
        userMapper.insert(userDO);
    }

    @Test
    public void testUpdate() {
        UserDO userDO = new UserDO();
        userDO.setVersion(1L);
        userDO.setId(1252629091023450114L);
        userDO.setUsername("niujinyan");
        userDO.setPhone("15311111111");
        userDO.setPassword("123456");
        userMapper.updateById(userDO);
    }
}
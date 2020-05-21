package com.shadow.technology_integration.controller;

import com.alibaba.fastjson.JSONObject;
import com.shadow.technology_integration.domain.dto.UserDTO;
import org.apache.catalina.User;

/**
 * 类描述：
 *
 * @author shadow
 * @date 2020/4/27
 */
public class TestMain {
    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("shadow");
        userDTO.setPassword("123456");
        System.out.println(JSONObject.toJSONString(userDTO,true));
    }
}

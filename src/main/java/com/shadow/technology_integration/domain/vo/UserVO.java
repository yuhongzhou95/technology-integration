package com.shadow.technology_integration.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shadow
 * @date 2020/4/15
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 8856467496691416532L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String phone;
}

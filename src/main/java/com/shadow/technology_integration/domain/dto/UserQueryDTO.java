package com.shadow.technology_integration.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 类描述：
 *
 * @author shadow
 * @date 2020/4/27
 */
@Data
public class UserQueryDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6388963865596623280L;


    /**
     * 用户名
     */
    @NotEmpty(message = "用户姓名不能为空！")
    private String username;

}

package com.shadow.technology_integration.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.shadow.technology_integration.util.InsertValidationGroup;
import com.shadow.technology_integration.util.UpdateValidationGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author shadow
 * @date 2020/4/15
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -3755931921419573383L;

    /**
     * 用户名
     * groups：分组校验，一般定义一个空接口标识分组校验
     */
    @NotBlank(message = "用户名不能为空！",
            groups = {InsertValidationGroup.class})
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空！",
            groups = {InsertValidationGroup.class})
    @Length(min = 6, max = 18,
            message = "密码长度不能少于6位，不能多于18位！")
    private String password;

    /**
     * 邮箱
     */
//    @NotEmpty(message = "邮箱不能为空！",
//            groups = {InsertValidationGroup.class})
    @Email(message = "必须为有效邮箱！")
    private String email;

    /**
     * 年龄
     */
//    @NotNull(message = "年龄不能为空！",
//            groups = {InsertValidationGroup.class})
    @Max(value = 60, message = "年龄不能大于60岁！")
    @Min(value = 18, message = "年龄不能小于18岁！")
    private Integer age;

    /**
     * 手机号
     */
//    @NotBlank(message = "手机号不能为空！",
//            groups = {InsertValidationGroup.class})
    @Length(min = 11,max = 11,message = "不符合手机号的格式")
    private String phone;

    /**
     * 版本号
     */
//    @NotNull(message = "版本号不能为空！",
//            groups = {UpdateValidationGroup.class})
    private Long version;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}

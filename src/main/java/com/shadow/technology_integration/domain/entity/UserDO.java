package com.shadow.technology_integration.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author shadow
 * @date 2020/4/15
 */
@Data
@TableName("user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = -6424801891881459401L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
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

    /*** 系统主信息 ***/

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

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

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    /**
     * 操作者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String operator;

    /**
     * 逻辑删除 0：正常，1：删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    /**
     * 版本号
     * 乐观锁使用规则：
     * 1.如果更新数据中不带有version字段，则不使用乐观锁且version不会累加
     * 2.如果更新数据中带有version字段，并且与数据库中一致，更新成功，并且version会累加
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long version;

}

package com.shadow.technology_integration.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.shadow.technology_integration.util.LocalDateTime2StringConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类描述：excel导出User实体类
 *
 * @author shadow
 * @date 2020/5/11
 */
@Data
public class UserExcelExportDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4495418172991898248L;

    /**
     * 用户名
     */
    @ExcelProperty("用户名")
    private String username;

    /**
     * 年龄
     */
    @ExcelProperty("年龄")
    private Integer age;

    /**
     * 版本号
     */
    @ExcelProperty("版本号")
    private Long version;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间",converter = LocalDateTime2StringConverter.class)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}

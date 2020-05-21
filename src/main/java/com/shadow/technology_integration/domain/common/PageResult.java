package com.shadow.technology_integration.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 类描述：通用分页查询返回实体
 *
 * @author shadow
 * @date 2020/4/27
 */
@Data
public class PageResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5074000774435553698L;

    /**
     * 当前页号
     */
    private Integer page;

    /**
     * 每页行数
     */
    private Integer size;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 动态的内容
     */
    private T data;
}

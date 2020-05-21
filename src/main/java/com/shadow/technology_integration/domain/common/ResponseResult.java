package com.shadow.technology_integration.domain.common;

import com.shadow.technology_integration.domain.nums.ResponseCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shadow
 * @date 2020/4/17
 */
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -4302559673370729299L;

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public static <T> ResponseResult<T> success() {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(ResponseCodeEnum.SUCCESS.getCode());
        responseResult.setMsg(ResponseCodeEnum.SUCCESS.getMessage());
        return responseResult;
    }

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(ResponseCodeEnum.SUCCESS.getCode());
        responseResult.setMsg(ResponseCodeEnum.SUCCESS.getMessage());
        responseResult.setData(data);
        return responseResult;
    }

    public static <T> ResponseResult<T> failure(ResponseCodeEnum codeEnum) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(codeEnum.getCode());
        responseResult.setMsg(codeEnum.getMessage());
        return responseResult;
    }

    public static <T> ResponseResult<T> failure(Integer code, String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(code);
        responseResult.setMsg(message);
        return responseResult;
    }
}

package com.shadow.technology_integration.exception;

import com.shadow.technology_integration.domain.nums.ResponseCodeEnum;
import lombok.Getter;

/**
 * 类名称：BusinessException
 * ********************************
 * <p>
 * 类描述：业务类异常
 *
 * @author
 * @date 下午9:34
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常编号
     */
    @Getter
    private final int code;

    /**
     * 根据枚举构造业务类异常
     *
     * @param error
     */
    public BusinessException(ResponseCodeEnum error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    /**
     * 自定义消息体构造业务类异常
     *
     * @param error
     * @param message
     */
    public BusinessException(ResponseCodeEnum error, String message) {
        super(message);
        this.code = error.getCode();
    }

    /**
     * 根据异常构造业务类异常
     *
     * @param error
     * @param cause
     */
    public BusinessException(ResponseCodeEnum error, Throwable cause) {
        super(cause);
        this.code = error.getCode();
    }

}

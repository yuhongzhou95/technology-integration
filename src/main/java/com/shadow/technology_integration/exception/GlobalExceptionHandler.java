package com.shadow.technology_integration.exception;

import com.shadow.technology_integration.domain.common.ResponseResult;
import com.shadow.technology_integration.domain.nums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名称：GlobalExceptionHandler
 * ********************************
 * <p>
 * 类描述：全局异常捕获处理器
 *
 * @author
 * @date 下午9:21
 */
@ControllerAdvice // 会拦截@Controller和@RestController修饰的类抛出的异常
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 拦截业务类异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult businessExceptionHandle(BusinessException e) {
        log.error("捕捉到业务类异常：", e);
        return ResponseResult.failure(e.getCode(), e.getMessage());
    }


    /**
     * 拦截运行时异常
     *
     * @param e
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult runtimeExceptionHandle(RuntimeException e) {
        log.error("捕捉到运行时异常：", e);

        return ResponseResult.failure(
                ResponseCodeEnum.UNKNOWN_ERROR.getCode(),
                e.getMessage());
    }

    /**
     * 捕捉系统级异常
     *
     * @param th
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult throwableHandle(Throwable th) {
        log.error("捕捉Throwable异常：", th);

        return ResponseResult.failure(
                ResponseCodeEnum.SYSTEM_ERROR.getCode(),
                th.getMessage());
    }

}

package com.shadow.technology_integration.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.shadow.technology_integration.domain.nums.ResponseCodeEnum;
import com.shadow.technology_integration.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类描述：全局限流拦截器
 *
 * @author shadow
 * @date 2020/5/5
 */
@Component
@Slf4j
public class RateLimitInterceptor implements HandlerInterceptor {

    /**
     * 限流器实例(QPS限制为1000)
     */
    private static final RateLimiter rateLimiter = RateLimiter.create(1000);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 尝试获取令牌
        if (!rateLimiter.tryAcquire()) {
            log.error("系统已被限流...");
            throw new BusinessException(ResponseCodeEnum.RATE_LIMIT_ERROR);
        }
        return true;
    }
}

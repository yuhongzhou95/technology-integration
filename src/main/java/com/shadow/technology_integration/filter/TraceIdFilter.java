package com.shadow.technology_integration.filter;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 类描述：TraceId过滤器
 *
 * @author shadow
 * @date 2020/5/6
 */
@WebFilter(urlPatterns = "/*") // 拦截路径
@Order(1) // 优先级
public class TraceIdFilter implements Filter {

    /**
     * TraceId常量
     */
    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = servletRequest.getParameter(TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            traceId = RandomStringUtils.randomAlphanumeric(9);
        }
        // 在MDC中放入traceId
        MDC.put(TRACE_ID, traceId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

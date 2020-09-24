package com.snailwu.springboot.request;

import org.apache.catalina.connector.RequestFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author 吴庆龙
 * @date 2020/9/22 10:48 上午
 */
//@Component
//@Aspect
public class LogAspect {
    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(* com.snailwu.springboot.request.IndexController.*(..))")
    public Object around(ProceedingJoinPoint point) throws UnsupportedEncodingException {
        log.info("========================================== Start ==========================================");

        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return result;
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        ContentCachingRequestWrapper requestWrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (requestWrapper == null) {
            return result;
        }
        String characterEncoding = requestWrapper.getCharacterEncoding();
        Map<String, String[]> parameterMap = requestWrapper.getParameterMap();
        for (String key : parameterMap.keySet()) {
            log.info("{}", key);
        }
        byte[] contentAsByteArray = requestWrapper.getContentAsByteArray();
        log.info("{}", new String(contentAsByteArray, characterEncoding));

        log.info("=========================================== End ===========================================");
        return result;
    }

}

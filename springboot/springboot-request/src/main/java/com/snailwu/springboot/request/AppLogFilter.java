package com.snailwu.springboot.request;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 吴庆龙
 * @date 2020/9/22 1:15 下午
 */
public class AppLogFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppLogFilter.class);

    /**
     * 需要打印请求体的 ContentType
     */
    private static final Set<String> REQUEST_CONTENT_TYPE_SET = new HashSet<>();
    private static final Set<String> RESPONSE_CONTENT_TYPE_SET = new HashSet<>();

    public AppLogFilter() {
        REQUEST_CONTENT_TYPE_SET.add("application/json");
        REQUEST_CONTENT_TYPE_SET.add("application/javascript");
        REQUEST_CONTENT_TYPE_SET.add("application/xml");
        REQUEST_CONTENT_TYPE_SET.add("multipart/form-data");
        REQUEST_CONTENT_TYPE_SET.add("application/x-www-form-urlencoded");
        REQUEST_CONTENT_TYPE_SET.add("text/plain");
        REQUEST_CONTENT_TYPE_SET.add("text/html");
        LOGGER.info("初始化 AppLogFilter.INCLUDE_CONTENT_TYPE_SET 成功");

        RESPONSE_CONTENT_TYPE_SET.add("application/json");
        RESPONSE_CONTENT_TYPE_SET.add("text/plain");
        LOGGER.info("初始化 AppLogFilter.RESPONSE_CONTENT_TYPE_SET 成功");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long startTs = System.currentTimeMillis();

        // 将 REQUEST_ID 设置到请求参数中
        String requestId = request.getHeader("req-id");
        if (StringUtils.isEmpty(requestId)) {
            // 生成唯一请求号
            requestId = "";
        }
        request.setAttribute("req-id", requestId);

        // 在日志中打印出来便于查问题
        ThreadContext.put("LOG_ID", requestId);

        // 封装
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        try {
            super.doFilter(requestWrapper, responseWrapper, chain);
        } finally {
            // 结束计时，计算耗时
            long costTs = System.currentTimeMillis() - startTs;

            // 请求中编码
            String characterEncoding = requestWrapper.getCharacterEncoding();

            // URL参数 + form-data文本参数 + x-www-form-urlencoded参数
            Map<String, String> urlParamMap = new HashMap<>();
            Map<String, String[]> parameterMap = requestWrapper.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String name = entry.getKey();
                String[] valueArray = entry.getValue();
                if (valueArray == null || valueArray.length == 0) {
                    continue;
                }
                String value = StringUtils.arrayToDelimitedString(valueArray, ",");
                name = URLDecoder.decode(name, characterEncoding);
                value = URLDecoder.decode(value, characterEncoding);
                urlParamMap.put(name, value);
            }

            // 请求体文本内容
            String realRequestBody = null;
            String requestContentType = requestWrapper.getContentType();
            if (!StringUtils.isEmpty(requestContentType)) {
                for (String ct : REQUEST_CONTENT_TYPE_SET) {
                    if (requestContentType.startsWith(ct)) {
                        byte[] byteArray = requestWrapper.getContentAsByteArray();
                        realRequestBody = StringUtils.trimAllWhitespace(new String(byteArray));
                        break;
                    }
                }
            }

            // 响应内容
            String realResponseBody = null;
            String responseContentType = responseWrapper.getContentType();
            if (!StringUtils.isEmpty(responseContentType)) {
                for (String ct : RESPONSE_CONTENT_TYPE_SET) {
                    if (responseContentType.startsWith(ct)) {
                        byte[] byteArray = responseWrapper.getContentAsByteArray();
                        realResponseBody = StringUtils.trimAllWhitespace(new String(byteArray));
                        break;
                    }
                }
            }

            // 获取客户端的IP
            String ipAddr = IpUtil.getClientIpAddr(request);

            LOGGER.info("=================================== BEGIN ===================================");
            LOGGER.info("Req-URI           :{}", requestWrapper.getRequestURI());
            LOGGER.info("Req-Content-Type  :{}", requestWrapper.getContentType());
            LOGGER.info("Req-Method        :{}", requestWrapper.getMethod());
            LOGGER.info("Req-IpAddr        :{}", ipAddr);
            LOGGER.info("Req-Param         :{}", urlParamMap);
            LOGGER.info("Req-Body          :{}", realRequestBody);
            LOGGER.info("Resp-Content-Type :{}", responseContentType);
            LOGGER.info("Resp-Body         :{}", realResponseBody);
            LOGGER.info("Cost-Time         :{}毫秒", costTs);
            LOGGER.info("===================================  END  ===================================");

            // 清空 ThreadContext
            ThreadContext.clearAll();

            // 写入响应消息到客户端
            responseWrapper.copyBodyToResponse();
        }
    }

}

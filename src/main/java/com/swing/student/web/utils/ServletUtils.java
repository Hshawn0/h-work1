package com.swing.student.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 和servlet有关的工具类
 * 包括处理request和response相关的方法
 *
 * @author swing
 */
public class ServletUtils {
    private static final Logger log = LoggerFactory.getLogger(ServletUtils.class);

    /**
     * 获取当前servlet上下文
     *
     * @return ServletRequestAttributes
     */
    public static ServletRequestAttributes getRequestAttributes() {
        //关键类：RequestContextHolder(获取当前线程下的request和response)
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static String getBodyByRequest(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try (InputStream inputStream = request.getInputStream()) {
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.warn("getBodyString出现问题！");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断请求是否需要被AuthenticationFilter过滤
     *
     * @param request 请求
     * @return 是否需要被过滤
     */
    public static boolean isNeedAuthenticationFiltered(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return !(
                url.endsWith("/login") ||
                        url.endsWith("/captcha") ||
                        url.endsWith("/test") ||
                        url.endsWith("/test2")
        );
    }

    /**
     * 从Session中获取值
     *
     * @param key 键
     * @return 值
     */
    public static String getAttributeFromSession(String key) {
        HttpSession session = ServletUtils.getRequest().getSession();
        return (String) session.getAttribute(key);
    }
}

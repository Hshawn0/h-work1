package com.swing.student.web.filter;

import com.alibaba.fastjson.JSON;
import com.swing.student.web.SkyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * @author swing
 */
@Component
public class CaptchaValidateFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        //只过滤结尾为/login的请求
        if (request.getRequestURL().toString().endsWith("/login") && "post".equalsIgnoreCase(request.getMethod())) {
            String captchaCodeValue = (String) session.getAttribute("captcha_code");
            String captchaCodeValue1 = request.getParameter("captchaCodeValue");
            if (captchaCodeValue == null || captchaCodeValue1 == null) {
                response.getWriter().write(JSON.toJSONString(SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "验证码信息不完整")));
                return;
            }
            //验证码是否正确
            if (!captchaCodeValue.equalsIgnoreCase(captchaCodeValue1)) {
                //删掉session携带的验证码
                session.setAttribute("captcha_code", null);
                response.getWriter().write(JSON.toJSONString(SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "验证码错误")));
                return;
            }
        }
        //如果没有携带验证码或验证码正确则通过
        filterChain.doFilter(request, response);
    }
}

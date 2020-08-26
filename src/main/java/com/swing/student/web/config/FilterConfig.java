package com.swing.student.web.config;

import com.swing.student.web.filter.CaptchaValidateFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author swing
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean someFilterRegistration(CaptchaValidateFilter captchaValidateFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(captchaValidateFilter);
        //所有路径都有效
        registration.addUrlPatterns("/*");
        registration.setName("captchaValidateFilter");
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }
}

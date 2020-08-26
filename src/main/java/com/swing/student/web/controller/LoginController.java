package com.swing.student.web.controller;

import com.swing.student.web.SkyResponse;
import com.swing.student.web.service.LoginService;
import com.swing.student.web.utils.ServletUtils;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;

/**
 * 登录接口
 *
 * @author swing
 */
@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping("/captcha")
    public ModelAndView captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, FontFormatException {
        // 生成验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(111, 36, 3);
        //设置字体
        specCaptcha.setFont(Captcha.FONT_5);
        //获取验证码的值
        String captchaCodeValue = specCaptcha.text().toLowerCase();

        ServletOutputStream out = null;
        try {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            //将验证码结果放入session放回前端，登录请求时携带进行验证，登录后销毁
            session.setAttribute("captcha_code", captchaCodeValue);
            out = response.getOutputStream();
            specCaptcha.out(out);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取登录页面
     *
     * @return 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public SkyResponse ajaxLogin(String username, String password, Boolean rememberMe) {
        return loginService.login(username, password, rememberMe);
    }

    /**
     * 注销登录
     *
     * @return 结果
     */
    @GetMapping("/logout")
    public String logout() {
        HttpSession session = ServletUtils.getRequest().getSession();
        session.setAttribute("role", null);
        session.setAttribute("studentNum", null);
        session.setAttribute("username", null);
        return "login";
    }

}

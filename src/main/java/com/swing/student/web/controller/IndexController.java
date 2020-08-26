package com.swing.student.web.controller;

import com.swing.student.web.utils.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
public class IndexController {

    /**
     * 系统首页
     *
     * @param map 模型
     * @return 视图
     */
    @GetMapping("/index")
    public String index(ModelMap map) {
        String role = (String) ServletUtils.getRequest().getSession().getAttribute("role");
        String username = (String) ServletUtils.getRequest().getSession().getAttribute("username");
        map.put("role", role);
        map.put("username", username);
        return "index";
    }

    /**
     * 首页
     */
    @GetMapping("/main")
    public String main() {
        return "main";
    }
}

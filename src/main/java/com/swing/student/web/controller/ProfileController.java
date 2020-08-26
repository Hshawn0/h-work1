package com.swing.student.web.controller;

import com.swing.student.domain.Student;
import com.swing.student.service.AdminService;
import com.swing.student.service.StudentService;
import com.swing.student.web.SkyResponse;
import com.swing.student.web.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author swing
 */
@Controller
@RequestMapping("/student/profile")
public class ProfileController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    /**
     * 获取个人信息页面
     *
     * @return 个人信息页面
     */
    @GetMapping
    public String profile(ModelMap map) {
        String studentNum = ServletUtils.getAttributeFromSession("studentNum");
        map.put("student", studentService.getByStudentNum(studentNum));
        return "student/profile/profile";
    }

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap map) {
        String studentNum = ServletUtils.getAttributeFromSession("studentNum");
        map.put("id", studentService.getByStudentNum(studentNum).getId());
        return "/student/profile/resetPwd";
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    public SkyResponse resetPwd(String oldPassword, String newPassword) {
        Student student = studentService.getByStudentNum(ServletUtils.getAttributeFromSession("studentNum"));
        student.setPassword(newPassword);
        studentService.update(student);
        return SkyResponse.success("重置密码成功！");
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        if (password.equalsIgnoreCase(studentService.getByStudentNum(ServletUtils.getAttributeFromSession("studentNum")).getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @ResponseBody
    public SkyResponse update(Student student) {
        studentService.update(student);
        return SkyResponse.success("用户信息更新成功！");
    }


}

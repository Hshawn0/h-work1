package com.swing.student.web.service;

import com.swing.student.domain.Admin;
import com.swing.student.domain.Student;
import com.swing.student.service.AdminService;
import com.swing.student.service.StudentService;
import com.swing.student.web.SkyResponse;
import com.swing.student.web.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author swing
 */
@Service
public class LoginService {
    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    public SkyResponse login(String username, String password, Boolean rememberMe) {
        HttpSession session = ServletUtils.getRequest().getSession();
        //登录，确定是管理员还是学生
        Admin admin = adminService.getByUsername(username);
        Student student = studentService.getByStudentNum(username);
        if (admin != null && admin.getPassword().equals(password)) {
            //设置下次免登录的信息
            session.setAttribute("role", "admin");
            session.setAttribute("username", username);
            return SkyResponse.success("登录成功");
        } else if (student != null && student.getPassword().equals(password)) {
            //设置下次免登录的信息
            session.setAttribute("role", "student");
            session.setAttribute("studentNum", student.getStudentNum());
            session.setAttribute("username", student.getUsername());
            return SkyResponse.success("登录成功");
        }
        return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "用户名（学号）或密码错误");
    }
}

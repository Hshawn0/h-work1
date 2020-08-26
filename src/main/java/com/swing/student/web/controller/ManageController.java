package com.swing.student.web.controller;

import com.swing.student.domain.Student;
import com.swing.student.service.StudentService;
import com.swing.student.web.SkyResponse;
import com.swing.student.web.dto.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author swing
 */
@Controller
@RequestMapping("/student/manage")
public class ManageController {
    @Autowired
    private StudentService studentService;

    /**
     * 获取学生管理页面
     *
     * @return 页面
     */
    @GetMapping
    public String getManage() {
        return "student/manage/manage";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Student student) {
        List<Student> list = studentService.listAll(student);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setTotal(list.size());
        rspData.setCode(200);
        rspData.setRows(list);
        return rspData;
    }

    /**
     * 新增视图
     */
    @GetMapping("/add")
    public String add() {
        return "student/manage/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    public SkyResponse addSave(@Validated Student student) {
        //判断学号是否冲突
        if (studentService.getByStudentNum(student.getStudentNum()) != null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "该学号已存在");
        }
        studentService.insert(student);
        return SkyResponse.success("新增成功");
    }

    /**
     * 修改视图
     */
    @GetMapping("/edit/{studentId}")
    public String edit(@PathVariable("studentId") Long studentId, ModelMap map) {
        map.put("student", studentService.getById(studentId));
        return "student/manage/edit";
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    @ResponseBody
    public SkyResponse editSave(Student student) {
        //判断学号是否冲突
        studentService.update(student);
        return SkyResponse.success("更新成功");
    }

    /**
     * 批量删除接口
     */
    @PostMapping("/remove")
    @ResponseBody
    public SkyResponse remove(String ids) {
        studentService.deleteByIds(ids);
        return SkyResponse.success("成功删除！");
    }
}

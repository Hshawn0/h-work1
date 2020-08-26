package com.swing.student.web.controller;

import com.swing.student.domain.Course;
import com.swing.student.service.CourseService;
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
@RequestMapping("/student/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 获取学生管理页面
     *
     * @return 页面
     */
    @GetMapping
    public String getManage() {
        return "student/course/course";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Course course) {
        List<Course> list = courseService.listAll(course);
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
        return "student/course/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    public SkyResponse addSave(@Validated Course course) {
        //判断课程名是否冲突
        if (courseService.getByCourseName(course.getCourseName()) != null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "该课程名已存在");
        }
        courseService.insert(course);
        return SkyResponse.success("新增成功");
    }

    /**
     * 修改视图
     */
    @GetMapping("/edit/{studentId}")
    public String edit(@PathVariable("studentId") Long courseId, ModelMap map) {
        map.put("course", courseService.getById(courseId));
        return "student/course/edit";
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    @ResponseBody
    public SkyResponse editSave(Course course) {
        //判断学号是否冲突
        courseService.update(course);
        return SkyResponse.success("更新成功");
    }

    /**
     * 批量删除接口
     */
    @PostMapping("/remove")
    @ResponseBody
    public SkyResponse remove(String ids) {
        courseService.deleteByIds(ids);
        return SkyResponse.success("成功删除！");
    }
}

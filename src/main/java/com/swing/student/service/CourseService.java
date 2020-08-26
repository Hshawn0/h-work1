package com.swing.student.service;

import com.swing.student.basic.BasicService;
import com.swing.student.domain.Course;

/**
 * @author swing
 */
public interface CourseService extends BasicService<Course> {
    /**
     * 根据课程名获取课程
     *
     * @param courseName 课程名
     * @return 课程信息
     */
    Course getByCourseName(String courseName);

    /**
     * 批量删除
     *
     * @param ids id字符串
     * @return 结果
     * @throws Exception 异常
     */
    int deleteByIds(String ids);
}

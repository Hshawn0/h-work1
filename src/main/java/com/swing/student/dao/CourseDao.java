package com.swing.student.dao;

import com.swing.student.basic.BasicDao;
import com.swing.student.domain.Course;

/**
 * @author swing
 */
public interface CourseDao extends BasicDao<Course> {
    /**
     * 根据课程名获取课程
     *
     * @param courseName 课程名
     * @return 课程信息
     */
    Course getByCourseName(String courseName);
}
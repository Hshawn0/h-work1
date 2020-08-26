package com.swing.student.dao;

import com.swing.student.basic.BasicDao;
import com.swing.student.domain.Student;

/**
 * @author swing
 */
public interface StudentDao extends BasicDao<Student> {
    /**
     * 学号查询学生信息
     *
     * @param studentNum 学号
     * @return 信息
     */
    Student getByStudentNum(String studentNum);

}
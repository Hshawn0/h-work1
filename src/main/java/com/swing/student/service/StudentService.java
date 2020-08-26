package com.swing.student.service;

import com.swing.student.basic.BasicService;
import com.swing.student.domain.Student;

/**
 * @author swing
 */
public interface StudentService extends BasicService<Student> {
    /**
     * 学号查询学生信息
     *
     * @param studentNum 学号
     * @return 信息
     */
    Student getByStudentNum(String studentNum);

    /**
     * 批量删除
     *
     * @param ids id字符串
     * @return 结果
     * @throws Exception 异常
     */
    int deleteByIds(String ids);
}

package com.swing.student.dao;

import com.swing.student.basic.BasicDao;
import com.swing.student.domain.Score;
import com.swing.student.web.dto.ScoreDTO;

import java.util.List;

/**
 * @author swing
 */
public interface ScoreDao extends BasicDao<Score> {
    /**
     * 获取成绩列表
     *
     * @param scoreDTO 条件
     * @return 结果
     */
    List<ScoreDTO> listAll(ScoreDTO scoreDTO);

    /**
     * 删除某学生所有的成绩记录
     *
     * @param studentId 学生Id
     * @return 结果
     */
    int deleteByStudentId(Long studentId);

    /**
     * 删除某课程的所有成绩记录
     *
     * @param courseId 课程Id
     * @return 结果
     */
    int deleteByCourseId(Long courseId);
}
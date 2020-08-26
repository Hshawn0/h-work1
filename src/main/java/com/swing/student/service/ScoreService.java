package com.swing.student.service;

import com.swing.student.basic.BasicService;
import com.swing.student.domain.Score;
import com.swing.student.web.dto.ScoreDTO;

import java.util.List;

/**
 * @author swing
 */
public interface ScoreService extends BasicService<Score> {

    /**
     * 获取成绩列表
     *
     * @param scoreDTO 条件
     * @return 结果
     */
    List<ScoreDTO> listAll(ScoreDTO scoreDTO);

    /**
     * 批量删除
     *
     * @param ids id字符串
     * @return 结果
     */
    int deleteByIds(String ids);

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

    /**
     * 当新增一个学生信息，将他和现有的所有课程关联插入成绩当中，默认成绩为0分
     *
     * @param studentId 学生id
     * @return 结果
     */
    int insertAllScoreByStudentId(Long studentId);

    /**
     * 当新增一个课程信息，将他和现有的所有课程关联插入成绩当中，默认成绩为0分
     *
     * @param courseId 课程id
     * @return 结果
     */
    int insertAllScoreByCourseId(Long courseId);
}

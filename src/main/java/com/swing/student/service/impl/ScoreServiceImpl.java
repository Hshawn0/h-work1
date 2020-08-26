package com.swing.student.service.impl;

import com.swing.student.dao.CourseDao;
import com.swing.student.dao.ScoreDao;
import com.swing.student.dao.StudentDao;
import com.swing.student.domain.Course;
import com.swing.student.domain.Score;
import com.swing.student.domain.Student;
import com.swing.student.service.ScoreService;
import com.swing.student.web.dto.ScoreDTO;
import com.swing.student.web.utils.Convert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoreDao scoreDao;

    @Resource
    private CourseDao courseDao;

    @Resource
    private StudentDao studentDao;

    @Override
    public List<ScoreDTO> listAll(ScoreDTO scoreDTO) {
        return scoreDao.listAll(scoreDTO);
    }

    @Override
    public int deleteById(Long id) {
        return scoreDao.deleteById(id);
    }

    @Override
    public int insert(Score score) {
        return scoreDao.insert(score);
    }

    @Override
    public Score getById(Long id) {
        return scoreDao.getById(id);
    }

    @Override
    public int update(Score score) {
        return scoreDao.update(score);
    }

    @Override
    public List<Score> listAll(Score score) {
        return scoreDao.listAll(score);
    }

    @Override
    public int deleteByIds(String ids) {
        Long[] studentIds = Convert.toLongArray(ids);
        for (Long id : studentIds) {
            scoreDao.deleteById(id);
        }
        return studentIds.length;
    }

    @Override
    public int deleteByStudentId(Long studentId) {
        return scoreDao.deleteByStudentId(studentId);
    }

    @Override
    public int deleteByCourseId(Long courseId) {
        return scoreDao.deleteByCourseId(courseId);
    }

    @Override
    public int insertAllScoreByStudentId(Long studentId) {
        //获取所有课程列表
        List<Course> courses = courseDao.listAll(new Course());
        for (Course course : courses) {
            Score score = new Score();
            score.setStudentId(studentId);
            score.setCourseId(course.getId());
            score.setScore(0);
            scoreDao.insert(score);
        }
        return courses.size();
    }

    @Override
    public int insertAllScoreByCourseId(Long courseId) {
        //获取所有学生信息
        List<Student> students = studentDao.listAll(new Student());
        for (Student student : students) {
            Score score = new Score();
            score.setStudentId(student.getId());
            score.setCourseId(courseId);
            score.setScore(0);
            scoreDao.insert(score);
        }
        return students.size();
    }

}

package com.swing.student.service.impl;

import com.swing.student.dao.CourseDao;
import com.swing.student.domain.Course;
import com.swing.student.service.CourseService;
import com.swing.student.service.ScoreService;
import com.swing.student.web.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

    @Autowired
    private ScoreService scoreService;

    @Override
    public int deleteById(Long id) {
        return courseDao.deleteById(id);
    }

    @Override
    public int insert(Course course) {
        int insert = courseDao.insert(course);
        //插入关联信息
        scoreService.insertAllScoreByCourseId(course.getId());
        return insert;
    }

    @Override
    public Course getById(Long id) {
        return courseDao.getById(id);
    }

    @Override
    public int update(Course course) {
        return courseDao.update(course);
    }

    @Override
    public List<Course> listAll(Course course) {
        return courseDao.listAll(course);
    }

    @Override
    public Course getByCourseName(String courseName) {
        return courseDao.getByCourseName(courseName);
    }

    @Override
    public int deleteByIds(String ids) {
        Long[] studentIds = Convert.toLongArray(ids);
        for (Long id : studentIds) {
            courseDao.deleteById(id);
            //删除成绩中的关联信息
            scoreService.deleteByCourseId(id);
        }
        return studentIds.length;
    }
}

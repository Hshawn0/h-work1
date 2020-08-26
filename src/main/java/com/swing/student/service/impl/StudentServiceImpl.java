package com.swing.student.service.impl;

import com.swing.student.dao.StudentDao;
import com.swing.student.domain.Student;
import com.swing.student.service.ScoreService;
import com.swing.student.service.StudentService;
import com.swing.student.web.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Autowired
    private ScoreService scoreService;

    @Override
    public int deleteById(Long id) {
        return studentDao.deleteById(id);
    }

    @Override
    public int insert(Student student) {
        int insert = studentDao.insert(student);
        //插入关联信息
        scoreService.insertAllScoreByStudentId(student.getId());
        return insert;
    }

    @Override
    public Student getById(Long id) {
        return studentDao.getById(id);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public List<Student> listAll(Student student) {
        return studentDao.listAll(student);
    }

    @Override
    public Student getByStudentNum(String studentNum) {
        return studentDao.getByStudentNum(studentNum);
    }


    @Override
    public int deleteByIds(String ids) {
        Long[] studentIds = Convert.toLongArray(ids);
        for (Long id : studentIds) {
            studentDao.deleteById(id);
            //删除成绩中的关联信息
            scoreService.deleteByStudentId(id);
        }
        return studentIds.length;
    }
}

package com.swing.student.dao;

import com.swing.student.StudentApplication;
import com.swing.student.web.dto.ScoreDTO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentApplication.class)
public class StudentDaoTest extends TestCase {
    @Resource
    private StudentDao studentDao;

    @Resource
    private ScoreDao scoreDao;

    @Test
    public void test1() {
        System.out.println(studentDao.getById(9L));
    }

    @Test
    public void test2() {
        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setUsername("猪猪侠");
        scoreDao.listAll(scoreDTO).forEach(System.out::println);
    }
}
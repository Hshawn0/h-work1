package com.swing.student.service.impl;

import com.swing.student.dao.AdminDao;
import com.swing.student.domain.Admin;
import com.swing.student.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    @Override
    public int deleteById(Long id) {
        return adminDao.deleteById(id);
    }

    @Override
    public int insert(Admin admin) {
        return adminDao.insert(admin);
    }

    @Override
    public Admin getById(Long id) {
        return adminDao.getById(id);
    }

    @Override
    public int update(Admin admin) {
        return adminDao.update(admin);
    }

    @Override
    public List<Admin> listAll(Admin admin) {
        return null;
    }

    @Override
    public Admin getByUsername(String username) {
        return adminDao.getByUsername(username);
    }
}

package com.swing.student.dao;

import com.swing.student.basic.BasicDao;
import com.swing.student.domain.Admin;

/**
 * @author swing
 */
public interface AdminDao extends BasicDao<Admin> {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    Admin getByUsername(String username);
}
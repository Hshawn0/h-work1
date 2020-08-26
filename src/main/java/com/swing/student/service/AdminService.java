package com.swing.student.service;

import com.swing.student.basic.BasicService;
import com.swing.student.domain.Admin;

/**
 * @author swing
 */
public interface AdminService extends BasicService<Admin> {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    Admin getByUsername(String username);
}

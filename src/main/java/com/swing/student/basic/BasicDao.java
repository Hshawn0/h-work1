package com.swing.student.basic;

import java.util.List;

/**
 * @author swing
 */
public interface BasicDao<T> {
    /**
     * 删除
     *
     * @param id id
     * @return 结果
     */
    int deleteById(Long id);

    /**
     * 增加
     *
     * @param t 信息
     * @return 结果
     */
    int insert(T t);

    /**
     * 获取
     *
     * @param id id
     * @return 结果
     */
    T getById(Long id);

    /**
     * 更新
     *
     * @param t 信息
     * @return 结果
     */
    int update(T t);

    /**
     * 列出所有的学生信息
     *
     * @return 学生信息列表
     */
    List<T> listAll(T t);
}

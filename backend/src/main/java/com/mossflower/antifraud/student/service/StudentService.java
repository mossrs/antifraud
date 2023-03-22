package com.mossflower.antifraud.student.service;

import com.mossflower.antifraud.entity.Portrait;
import com.mossflower.antifraud.entity.Student;

/**
 * @author z's'b
 */


public interface StudentService {
    /**
     * 学生根据学号登录登录
     *
     * @param studentCard 学号
     * @param password    密码
     * @return 用户token
     */
    String getOne(String studentCard, String password);

    /**
     * 根据token中的学号获取用户信息
     *
     * @return 用户信息
     */
    Student getStudentByStudentCard();

}
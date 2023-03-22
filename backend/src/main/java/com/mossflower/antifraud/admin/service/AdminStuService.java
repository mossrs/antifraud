package com.mossflower.antifraud.admin.service;

import com.mossflower.antifraud.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/15 13:56
 */
public interface AdminStuService {

    /**
     * 根据班级名查询所有学生信息
     * @param className 班级名
     * @return 学生信息列表
     */
    List<Student> getAllStu(String className);

}

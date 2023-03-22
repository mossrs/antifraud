package com.mossflower.antifraud.admin.service;

import com.mossflower.antifraud.entity.StuClazz;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/13 17:41
 */
public interface AdminStuClazzService {

    /**
     * 查询所有班级
     * @return 班级列表
     */
    List<StuClazz> getStuClazzList();
}

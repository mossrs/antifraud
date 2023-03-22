package com.mossflower.antifraud.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mossflower.antifraud.admin.service.AdminStuService;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.entity.Student;
import com.mossflower.antifraud.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/15 13:54
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class AdminStuServiceImpl implements AdminStuService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAllStu(String className) {
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<Student>()
                .eq(Student::getClassName, className);
        G.removeDecodeJwt();
        return studentMapper.selectList(lqw);
    }
}

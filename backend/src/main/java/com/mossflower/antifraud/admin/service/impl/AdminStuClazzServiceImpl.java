package com.mossflower.antifraud.admin.service.impl;

import com.mossflower.antifraud.admin.service.AdminStuClazzService;
import com.mossflower.antifraud.entity.StuClazz;
import com.mossflower.antifraud.mapper.StuClazzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/13 17:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminStuClazzServiceImpl implements AdminStuClazzService {

    @Autowired
    private StuClazzMapper stuClazzMapper;

    @Override
    public List<StuClazz> getStuClazzList() {
        return stuClazzMapper.selectList(null);
    }
}

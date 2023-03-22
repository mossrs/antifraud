package com.mossflower.antifraud.wx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mossflower.antifraud.entity.FraudSubject;
import com.mossflower.antifraud.mapper.FraudSubjectMapper;
import com.mossflower.antifraud.wx.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/9/21 22:47
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PushServiceImpl implements PushService {

    @Autowired
    private FraudSubjectMapper fraudSubjectMapper;

    @Override
    public List<FraudSubject> getRandomFraudSubject() {
        QueryWrapper<FraudSubject> qw = new QueryWrapper<>();
        qw.orderByDesc("id").last("limit 15");
        return fraudSubjectMapper.selectList(qw);
    }
}

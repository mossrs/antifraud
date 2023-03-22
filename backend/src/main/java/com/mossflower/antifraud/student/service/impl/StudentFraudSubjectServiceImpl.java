package com.mossflower.antifraud.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.common.exception.BusinessException;
import com.mossflower.antifraud.entity.FraudSubject;
import com.mossflower.antifraud.mapper.FraudSubjectMapper;
import com.mossflower.antifraud.student.service.StudentFraudSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/27 11:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentFraudSubjectServiceImpl implements StudentFraudSubjectService {

    @Autowired
    private FraudSubjectMapper fraudSubjectMapper;

    @Override
    public List<FraudSubject> getFraudSubjectList(String fraudType) {
        LambdaQueryWrapper<FraudSubject> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FraudSubject::getFraudType, fraudType)
                .orderByDesc(FraudSubject::getId)
                .last("limit 3");
        List<FraudSubject> subjects = fraudSubjectMapper.selectList(lqw);
        if (subjects.size() == 0) {
            throw new BusinessException("类型不对 请重新输入");
        }
        if (subjects.size() < 3) {
            lqw.eq(FraudSubject::getFraudType, "防范诈骗")
                    .orderByDesc(FraudSubject::getId)
                    .last("limit 3");
            subjects.addAll(fraudSubjectMapper.selectList(lqw));
        }
        G.removeDecodeJwt();
        return subjects.subList(0, 3);
    }
}
